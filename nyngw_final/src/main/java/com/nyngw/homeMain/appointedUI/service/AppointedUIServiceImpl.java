package com.nyngw.homeMain.appointedUI.service;

import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.BirthdayVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.CommonApproval_TOTALVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DocumentViewVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MainUserUiSelectSetting;
import com.nyngw.dto.MainUserUiSelectVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.dto.ScheduleVO;
import com.nyngw.dto.UserInterfaceVO;
import com.nyngw.dto.UserUiVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;
import com.nyngw.electronicApproval.individualDocumentBox.dao.IndividualDocumentBoxDaoImpl;
import com.nyngw.electronicApproval.individualDocumentBox.service.IndividualDocumentBoxServiceImpl;
import com.nyngw.electronicApproval.theRestDocumentBox.dao.TheRestDocumentBoxDaoImpl;
import com.nyngw.homeMain.appointedUI.dao.AppointedUIDaoImpl;
import com.nyngw.sharingInformation.scheduleManagement.dao.ScheduleManagementDaoImpl;

@Service
public class AppointedUIServiceImpl implements AppointedUIService {
	
	@Autowired
	private AppointedUIDaoImpl appointedUIDao;
	
	@Autowired
	private CommonDaoImpl CommonDao;
	
	@Autowired
	private ScheduleManagementDaoImpl scheduleManagementDao; 

	@Autowired
	private IndividualDocumentBoxDaoImpl individualDocumentBoxDao;
	
	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;

	@Autowired
	private TheRestDocumentBoxDaoImpl theRestDocumentBoxDao;
	
	@Override
	public CompanyVO checkCompany() throws SQLException {
		CompanyVO companyList = appointedUIDao.selectCompany();
		return companyList;
	}

	@Override
	public MemberVO checkMember(String mem_id) throws SQLException {
		MemberVO memberList = appointedUIDao.selectMember(mem_id);
		return memberList;
	}

	///////////////////////////UI설정
	//화면1용
	@Override
	public List<Map> selectBigMenu() {
		List<BigMenuVO> list = appointedUIDao.selectBigMenu();
		List<Map> bigList = new ArrayList<Map>();
		for(int i = 0; i < list.size(); i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("big_number", list.get(i).getBig_number());
			map.put("big_name", list.get(i).getBig_name());
			bigList.add(map);
		}
		return bigList;
	}

	@Override
	public List<Map> selectMiddleMenu(String big_num) {
		List<MiddleMenuVO> list = appointedUIDao.selectMiddleMenu(big_num);
		List<Map> middleList = new ArrayList<Map>();
		for(int i = 0; i < list.size(); i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("mid_number", list.get(i).getMid_number());
			map.put("mid_name", list.get(i).getMid_name());
			map.put("mid_big_number", list.get(i).getMid_big_number());
			middleList.add(map);
		}
		return middleList;
	}

	@Override
	public void userUiInsert_UI(UserUiVO userUi, Principal principal) {
		MemberVO member = CommonDao.common_selectMemberByMemID(principal.getName());
		int result = appointedUIDao.userUiSelect_UI(member.getMem_number());
		UserInterfaceVO ui = new UserInterfaceVO();
		if(result == 0){
			//여기는 등록하는 부분
			ui.setUi_mem_number(member.getMem_number());
			ui.setUi_user1(userUi.getMiddleMenu());
			ui.setUi_user2(userUi.getMiddleMenu1());
			ui.setUi_user3(userUi.getMiddleMenu2());
			ui.setUi_autouse("n");
			appointedUIDao.userUiInsert_UI(ui);
		}else{
			//이미 등록한적이 있으면 열로 들어와서 수정이된다.
			ui.setUi_mem_number(member.getMem_number());
			ui.setUi_user1(userUi.getMiddleMenu());
			ui.setUi_user2(userUi.getMiddleMenu1());
			ui.setUi_user3(userUi.getMiddleMenu2());
			ui.setUi_autouse("n");
			appointedUIDao.userUiUpdate_UI(ui);
		}
	}

	/**
	 * 여기에서 로그인한 유저의 UI를 확인하여 Model에 심는다.
	 */
	@Override
	public void userMainUiSelectService(Model model, Principal principal) {
		String loginUser = principal.getName();
		MemberVO member = CommonDao.common_selectMemberByMemID(loginUser);
		int eaListCount = eaListCount(member.getMem_number()); //미결재
		model.addAttribute("eaListCount",eaListCount);
		int userUiApprovalCount = userUiApprovalCount(member.getMem_number()); //상신
		model.addAttribute("userUiApprovalCount", userUiApprovalCount);
		int theRestDocumentBoxCount = theRestDocumentBoxCount(member.getMem_number()); //참조
		model.addAttribute("theRestDocumentBoxCount", theRestDocumentBoxCount);
		UserInterfaceVO userInterface =  appointedUIDao.userSelectInterface(member.getMem_number());
		List<String> uiCodeName = new ArrayList<String>();
		if(userInterface == null){
			userInterface = new UserInterfaceVO();
			userInterface.setUi_autouse("n");
			userInterface.setUi_user1("mid_2");
			userInterface.setUi_user2("mid_7");
			userInterface.setUi_user3("mid_6");
		}
		if(userInterface.getUi_autouse()!=null && userInterface.getUi_autouse().equals("n")){
			uiCodeName.add(userInterface.getUi_user1());
			uiCodeName.add(userInterface.getUi_user2());
			uiCodeName.add(userInterface.getUi_user3());
		}
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String one = userInterface.getUi_user1();
		String two = userInterface.getUi_user2();
		String three = userInterface.getUi_user3();
		List<BoardVO> noticeMatter = null;//공지사항
		List<BoardVO> boardList = null;//자유게시판
		List<AddressBookVO> memberAddressList = null;//주소록
		List<BirthdayVO> memberBirthdayList = null; //생일자
		List<Duty_DocumentVO> departmentList = null;//부서업무일지
		List<Duty_DocumentVO> personalBusiness = null;//개인업무일지
		List<DocumentViewVO> documentManagerList = null; //문서조회
		List<ScheduleVO> scheduleList = null;//일정관리 
		List<Duty_ReportVO> dutyReportList = null; //받은업무보고
		List<CommonApproval_TOTALVO> EARefusedList = null;//반려문서함
		List<Electronic_ApprovalVO> eaList = null;//미완료문서
		List<String> ea_numberList = null;//접속자의 사원번호로 결재라인에 올라가있는 결재번호 검색
		MainUserUiSelectSetting mUUSS = new MainUserUiSelectSetting();//제목리스트, 내용리스트, 테이블명, More주소
		List<MainUserUiSelectVO> list = null; //내용을 담을 리스트
		MainUserUiSelectVO mUUSVO = null;//내용이들어가는곳
		List<String> titleList = null;//제목이 들어가는 곳
		int count = 5;// 총5까지만 넣는데 만약 list의 사이즈가 5보다 작을 경우 size로 한다.
		if(userInterface.getUi_autouse()!=null && userInterface.getUi_autouse().equals("n")){
			for(int i = 0; i < uiCodeName.size(); i++){
				switch (uiCodeName.get(i)) {
				case "mid_1": //문서조회
					documentManagerList = appointedUIDao.userUiDocumentManagerList_UI();//문서조회
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					titleList = new ArrayList<String>();//제목이 들어가는 곳
//					if(uiCodeName.get(i).equals(userInterface.getUi_user1())){//화면1용
						titleList.add("번호");
						titleList.add("문서명");
						titleList.add("문서종류");
						titleList.add("등록자");
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setTitle1(titleList);
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setTitle2(titleList);
						}else{
							mUUSS.setTitle3(titleList);
						}
						if(documentManagerList.size()>count){
							for(int j = 0; j < count; j++){
								mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
								mUUSVO.setContent1(documentManagerList.get(j).getDv_doc_number().substring(3));
								mUUSVO.setContent2(documentManagerList.get(j).getDv_doc_name());
								mUUSVO.setContent3(documentManagerList.get(j).getDv_code_name());
								mUUSVO.setContent4(documentManagerList.get(j).getDv_mem_name());//등록자
								mUUSVO.setDetailUri("/documentManagement/documentManager/documentDetail?dv_doc_number="+documentManagerList.get(j).getDv_doc_number());
								list.add(mUUSVO);
							}
							if(one.equals(uiCodeName.get(i))){
								mUUSS.setContent1(list);
								mUUSS.setMenu1("문서조회");
								mUUSS.setUriAddr1("/documentManagement/documentManager/documentSelect");
							}else if(two.equals(uiCodeName.get(i))){
								mUUSS.setContent2(list);
								mUUSS.setMenu2("문서조회");
								mUUSS.setUriAddr2("/documentManagement/documentManager/documentSelect");
							}else{
								mUUSS.setContent3(list);
								mUUSS.setMenu3("문서조회");
								mUUSS.setUriAddr3("/documentManagement/documentManager/documentSelect");
							}
						}else{
							for(int j = 0; j < documentManagerList.size(); j++){
								mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
								mUUSVO.setContent1(documentManagerList.get(j).getDv_doc_number().substring(3));
								mUUSVO.setContent2(documentManagerList.get(j).getDv_code_name());
								mUUSVO.setContent3(documentManagerList.get(j).getDv_doc_name());
								mUUSVO.setContent4(documentManagerList.get(j).getDv_mem_name());//등록자
								mUUSVO.setDetailUri("/documentManagement/documentManager/documentDetail?dv_doc_number="+documentManagerList.get(j).getDv_doc_number());
								list.add(mUUSVO);
							}
//							mUUSS.setContent1(list);
//							mUUSS.setMenu1("문서조회");
//							mUUSS.setUriAddr1("/documentManagement/documentManager/documentSelect");
							if(one.equals(uiCodeName.get(i))){
								mUUSS.setContent1(list);
								mUUSS.setMenu1("문서조회");
								mUUSS.setUriAddr1("/documentManagement/documentManager/documentSelect");
							}else if(two.equals(uiCodeName.get(i))){
								mUUSS.setContent2(list);
								mUUSS.setMenu2("문서조회");
								mUUSS.setUriAddr2("/documentManagement/documentManager/documentSelect");
							}else{
								mUUSS.setContent3(list);
								mUUSS.setMenu3("문서조회");
								mUUSS.setUriAddr3("/documentManagement/documentManager/documentSelect");
							}
						}
//					}
					
					break;
				case "mid_2": //공지사항
					noticeMatter = appointedUIDao.userUiNoticeMatter_UI();//공지사항
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("글번호");
					titleList.add("제목");
					titleList.add("등록일");
					titleList.add("작성자");
//					mUUSS.setTitle2(titleList);
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(noticeMatter.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(noticeMatter.get(j).getBoard_number().substring(5));
							mUUSVO.setContent2(noticeMatter.get(j).getBoard_title());
							mUUSVO.setContent3(transFormat.format(noticeMatter.get(j).getBoard_date()));
							mUUSVO.setContent4(userNameSetting(noticeMatter.get(j).getBoard_mem_number()));
							mUUSVO.setDetailUri("/sharingInformation/noticeMatter/nmDetail?board_number="+noticeMatter.get(j).getBoard_number());
							list.add(mUUSVO);
						}
//						mUUSS.setContent2(list);
//						mUUSS.setMenu2("공지사항");
//						mUUSS.setUriAddr2("/sharingInformation/noticeMatter/nmList");
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("공지사항");
							mUUSS.setUriAddr1("/sharingInformation/noticeMatter/nmList");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("공지사항");
							mUUSS.setUriAddr2("/sharingInformation/noticeMatter/nmList");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("공지사항");
							mUUSS.setUriAddr3("/sharingInformation/noticeMatter/nmList");
						}
					}else{
						for(int j = 0; j < noticeMatter.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(noticeMatter.get(j).getBoard_number().substring(5));
							mUUSVO.setContent2(noticeMatter.get(j).getBoard_title());
							mUUSVO.setContent3(transFormat.format(noticeMatter.get(j).getBoard_date()));
							mUUSVO.setContent4(userNameSetting(noticeMatter.get(j).getBoard_mem_number()));
							mUUSVO.setDetailUri("/sharingInformation/noticeMatter/nmDetail?board_number="+noticeMatter.get(j).getBoard_number());
							list.add(mUUSVO);
						}
//						mUUSS.setContent2(list);
//						mUUSS.setMenu2("공지사항");
//						mUUSS.setUriAddr2("/sharingInformation/noticeMatter/nmList");
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("공지사항");
							mUUSS.setUriAddr1("/sharingInformation/noticeMatter/nmList");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("공지사항");
							mUUSS.setUriAddr2("/sharingInformation/noticeMatter/nmList");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("공지사항");
							mUUSS.setUriAddr3("/sharingInformation/noticeMatter/nmList");
						}
					}
					
					
					break;
				case "mid_3": //일정관리
					scheduleList = scheduleManagementDao.SI_selectMemberSchedule(member.getMem_number());//일정관리
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("날짜");
					titleList.add("일정제목");
					titleList.add("일정종류");
					titleList.add("일정시간");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(scheduleList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(scheduleList.get(j).getSc_date());
							mUUSVO.setContent2(scheduleList.get(j).getSc_title());
							if(scheduleList.get(j).getSc_code_number().equals("code4")){
								mUUSVO.setContent3("부서");
							}else{
								mUUSVO.setContent3("개인");
							}
							mUUSVO.setContent4(scheduleList.get(j).getSc_time());
							mUUSVO.setDetailUri("/sharingInformation/scheduleManagement/scheduleDetail?sc_number="+scheduleList.get(j).getSc_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("일정관리");
							mUUSS.setUriAddr1("/sharingInformation/scheduleManagement/schedule");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("일정관리");
							mUUSS.setUriAddr2("/sharingInformation/scheduleManagement/schedule");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("일정관리");
							mUUSS.setUriAddr3("/sharingInformation/scheduleManagement/schedule");
						}
					}else{
						for (int j = 0; j < scheduleList.size(); j++) {
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(scheduleList.get(j).getSc_date());
							mUUSVO.setContent2(scheduleList.get(j).getSc_title());
							if(scheduleList.get(j).getSc_code_number().equals("code4")){
								mUUSVO.setContent3("부서");
							}else{
								mUUSVO.setContent3("개인");
							}
							mUUSVO.setContent4(scheduleList.get(j).getSc_time());
							mUUSVO.setDetailUri("/sharingInformation/scheduleManagement/scheduleDetail?sc_number="+scheduleList.get(j).getSc_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("일정관리");
							mUUSS.setUriAddr1("/sharingInformation/scheduleManagement/schedule");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("일정관리");
							mUUSS.setUriAddr2("/sharingInformation/scheduleManagement/schedule");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("일정관리");
							mUUSS.setUriAddr3("/sharingInformation/scheduleManagement/schedule");
						}
					}
					break;
				case "mid_4": //게시판
					boardList = appointedUIDao.userUiBoardList_UI();//자유게시판
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("글번호");
					titleList.add("제목");
					titleList.add("등록일");
					titleList.add("작성자");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(boardList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(boardList.get(j).getBoard_number().substring(5));
							mUUSVO.setContent2(boardList.get(j).getBoard_title());
							mUUSVO.setContent3(transFormat.format(boardList.get(j).getBoard_date()));
							mUUSVO.setContent4(userNameSetting(boardList.get(j).getBoard_mem_number()));
							mUUSVO.setDetailUri("/sharingInformation/board/detail?board_number="+boardList.get(j).getBoard_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("게시판");
							mUUSS.setUriAddr1("/sharingInformation/board/list");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("게시판");
							mUUSS.setUriAddr2("/sharingInformation/board/list");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("게시판");
							mUUSS.setUriAddr3("/sharingInformation/board/list");
						}
					}else{
						for(int j = 0; j < boardList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(boardList.get(j).getBoard_number().substring(5));
							mUUSVO.setContent2(boardList.get(j).getBoard_title());
							mUUSVO.setContent3(transFormat.format(boardList.get(j).getBoard_date()));
							mUUSVO.setContent4(userNameSetting(boardList.get(j).getBoard_mem_number()));
							mUUSVO.setDetailUri("/sharingInformation/board/detail?board_number="+boardList.get(j).getBoard_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("게시판");
							mUUSS.setUriAddr1("/sharingInformation/board/list");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("게시판");
							mUUSS.setUriAddr2("/sharingInformation/board/list");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("게시판");
							mUUSS.setUriAddr3("/sharingInformation/board/list");
						}
					}
					break;
				case "mid_5": //주소록
					memberAddressList = appointedUIDao.userUiMemberAddressList_UI();//주소록
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("직위");
					titleList.add("이름");
					titleList.add("부서");
					titleList.add("연락처");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(memberAddressList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(memberAddressList.get(j).getMem_dept_name());
							mUUSVO.setContent2(memberAddressList.get(j).getMem_name());
							mUUSVO.setContent3(memberAddressList.get(j).getMem_position_name());
							mUUSVO.setContent4(memberAddressList.get(j).getMem_tel());
							mUUSVO.setDetailUri("/sharingInformation/memberInformation/addressBook");
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("주소록");
							mUUSS.setUriAddr1("/sharingInformation/memberInformation/addressBook");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("주소록");
							mUUSS.setUriAddr2("/sharingInformation/memberInformation/addressBook");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("주소록");
							mUUSS.setUriAddr3("/sharingInformation/memberInformation/addressBook");
						}
					}else{
						for(int j = 0; j < memberAddressList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(memberAddressList.get(j).getMem_dept_name());
							mUUSVO.setContent2(memberAddressList.get(j).getMem_name());
							mUUSVO.setContent3(memberAddressList.get(j).getMem_position_name());
							mUUSVO.setContent4(memberAddressList.get(j).getMem_tel());
							mUUSVO.setDetailUri("/sharingInformation/memberInformation/addressBook");
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("주소록");
							mUUSS.setUriAddr1("/sharingInformation/memberInformation/addressBook");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("주소록");
							mUUSS.setUriAddr2("/sharingInformation/memberInformation/addressBook");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("주소록");
							mUUSS.setUriAddr3("/sharingInformation/memberInformation/addressBook");
						}
					}
					break;
				case "mid_6": //생일자
					memberBirthdayList = appointedUIDao.userUiMemberBirthdayList_UI();//생일자
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("날짜");
					titleList.add("이름");
					titleList.add("부서");
					titleList.add("직위");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}					
					if(memberBirthdayList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(memberBirthdayList.get(j).getMem_birthday().substring(0, 10));
							mUUSVO.setContent2(memberBirthdayList.get(j).getMem_name());
							mUUSVO.setContent3(memberBirthdayList.get(j).getMem_position_name());
							mUUSVO.setContent4(memberBirthdayList.get(j).getMem_dept_name());
							mUUSVO.setDetailUri("/sharingInformation/memberInformation/birthdayCheck");
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("생일자");
							mUUSS.setUriAddr1("/sharingInformation/memberInformation/birthdayCheck");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("생일자");
							mUUSS.setUriAddr2("/sharingInformation/memberInformation/birthdayCheck");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("생일자");
							mUUSS.setUriAddr3("/sharingInformation/memberInformation/birthdayCheck");
						}
					}else{
						for(int j = 0; j < memberBirthdayList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(memberBirthdayList.get(j).getMem_birthday().substring(0, 10));
							mUUSVO.setContent2(memberBirthdayList.get(j).getMem_name());
							mUUSVO.setContent3(memberBirthdayList.get(j).getMem_position_name());
							mUUSVO.setContent4(memberBirthdayList.get(j).getMem_dept_name());
							mUUSVO.setDetailUri("/sharingInformation/memberInformation/birthdayCheck");
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("생일자");
							mUUSS.setUriAddr1("/sharingInformation/memberInformation/birthdayCheck");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("생일자");
							mUUSS.setUriAddr2("/sharingInformation/memberInformation/birthdayCheck");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("생일자");
							mUUSS.setUriAddr3("/sharingInformation/memberInformation/birthdayCheck");
						}
					}
					break;
				case "mid_7": //개인업무
					personalBusiness = appointedUIDao.userUiPersonalBusinessList_UI(member.getMem_number());//개인업무일지
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("번호");
					titleList.add("제목");
					titleList.add("등록일");
					titleList.add("보고유형");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}	
					if(personalBusiness.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(personalBusiness.get(j).getDd_number().substring(2));
							mUUSVO.setContent2(personalBusiness.get(j).getDd_title());
							mUUSVO.setContent3(transFormat.format(personalBusiness.get(j).getDd_date()));
							if(personalBusiness.get(j).getDd_code_number().equals("code1")){
								mUUSVO.setContent4("일일업무일지");
							}else if(personalBusiness.get(j).getDd_code_number().equals("code2")){
								mUUSVO.setContent4("주간업무일지");
							}else{
								mUUSVO.setContent4("월간업무일지");
							}
							mUUSVO.setDetailUri("/businessSupport/dutyDocument/personalDetail?dd_number="+personalBusiness.get(j).getDd_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("개인업무");
							mUUSS.setUriAddr1("/businessSupport/dutyDocument/personal");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("개인업무");
							mUUSS.setUriAddr2("/businessSupport/dutyDocument/personal");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("개인업무");
							mUUSS.setUriAddr3("/businessSupport/dutyDocument/personal");
						}
					}else{
						for(int j = 0; j < personalBusiness.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(personalBusiness.get(j).getDd_number().substring(2));
							mUUSVO.setContent2(personalBusiness.get(j).getDd_title());
							mUUSVO.setContent3(transFormat.format(personalBusiness.get(j).getDd_date()));
							if(personalBusiness.get(j).getDd_code_number().equals("code1")){
								mUUSVO.setContent4("일일업무일지");
							}else if(personalBusiness.get(j).getDd_code_number().equals("code2")){
								mUUSVO.setContent4("주간업무일지");
							}else{
								mUUSVO.setContent4("월간업무일지");
							}
							mUUSVO.setDetailUri("/businessSupport/dutyDocument/personalDetail?dd_number="+personalBusiness.get(j).getDd_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("개인업무");
							mUUSS.setUriAddr1("/businessSupport/dutyDocument/personal");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("개인업무");
							mUUSS.setUriAddr2("/businessSupport/dutyDocument/personal");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("개인업무");
							mUUSS.setUriAddr3("/businessSupport/dutyDocument/personal");
						}
					}
					break;
				case "mid_8": //부서업무
					//여기부터 끝까지 한개임
					departmentList = appointedUIDao.userUiDepartmentList_UI(loginUser);//부서업무일지
					for(int j = 0; j < departmentList.size(); j++){
						departmentList.get(j).setDd_name(userNameSetting(departmentList.get(j).getDd_mem_number()));
					}
					//끝
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("번호");
					titleList.add("제목");
					titleList.add("등록일");
					titleList.add("보고유형");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}	
					if(departmentList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(departmentList.get(j).getDd_number().substring(2));
							mUUSVO.setContent2(departmentList.get(j).getDd_title());
							mUUSVO.setContent3(transFormat.format(departmentList.get(j).getDd_date()));
							if(departmentList.get(j).getDd_code_number().equals("code1")){
								mUUSVO.setContent4("일일업무일지");
							}else if(personalBusiness.get(j).getDd_code_number().equals("code2")){
								mUUSVO.setContent4("주간업무일지");
							}else{
								mUUSVO.setContent4("월간업무일지");
							}
							mUUSVO.setDetailUri("/businessSupport/dutyDocument/departmentDetail?dd_number="+departmentList.get(j).getDd_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("부서업무");
							mUUSS.setUriAddr1("/businessSupport/dutyDocument/department");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("부서업무");
							mUUSS.setUriAddr2("/businessSupport/dutyDocument/department");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("부서업무");
							mUUSS.setUriAddr3("/businessSupport/dutyDocument/department");
						}
					}else{
						for(int j = 0; j < departmentList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(departmentList.get(j).getDd_number().substring(2));
							mUUSVO.setContent2(departmentList.get(j).getDd_title());
							mUUSVO.setContent3(transFormat.format(departmentList.get(j).getDd_date()));
							if(departmentList.get(j).getDd_code_number().equals("code1")){
								mUUSVO.setContent4("일일업무일지");
							}else if(personalBusiness.get(j).getDd_code_number().equals("code2")){
								mUUSVO.setContent4("주간업무일지");
							}else{
								mUUSVO.setContent4("월간업무일지");
							}
							mUUSVO.setDetailUri("/businessSupport/dutyDocument/departmentDetail?dd_number="+departmentList.get(j).getDd_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("부서업무");
							mUUSS.setUriAddr1("/businessSupport/dutyDocument/department");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("부서업무");
							mUUSS.setUriAddr2("/businessSupport/dutyDocument/department");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("부서업무");
							mUUSS.setUriAddr3("/businessSupport/dutyDocument/department");
						}
					}
					
					break;
				case "mid_9": //받은업무보고
					dutyReportList = appointedUIDao.userUiDutyReportList_UI(loginUser);
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("번호");
					titleList.add("제목");
					titleList.add("보고유형");
					titleList.add("보고자");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(dutyReportList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(dutyReportList.get(j).getDr_number().substring(2));
							mUUSVO.setContent2(dutyReportList.get(j).getDr_title());
							mUUSVO.setContent3(userNameSetting(dutyReportList.get(j).getDr_mem_number()));
							if(dutyReportList.get(j).getDr_code_number().equals("code1")){
								mUUSVO.setContent4("일일보고");
							}else if(dutyReportList.get(j).getDr_code_number().equals("code2")){
								mUUSVO.setContent4("주간보고");
							}else{
								mUUSVO.setContent4("월간보고");
							}
							mUUSVO.setDetailUri("/businessSupport/dutyReport/dutyReportDetail?dr_number="+dutyReportList.get(j).getDr_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("받은업무보고");
							mUUSS.setUriAddr1("/businessSupport/dutyReport/dutyReport");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("받은업무보고");
							mUUSS.setUriAddr2("/businessSupport/dutyReport/dutyReport");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("받은업무보고");
							mUUSS.setUriAddr3("/businessSupport/dutyReport/dutyReport");
						}
					}else{
						for(int j = 0; j < dutyReportList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(dutyReportList.get(j).getDr_number().substring(2));
							mUUSVO.setContent2(dutyReportList.get(j).getDr_title());
							mUUSVO.setContent3(userNameSetting(dutyReportList.get(j).getDr_mem_number()));
							if(dutyReportList.get(j).getDr_code_number().equals("code1")){
								mUUSVO.setContent4("일일보고");
							}else if(dutyReportList.get(j).getDr_code_number().equals("code2")){
								mUUSVO.setContent4("주간보고");
							}else{
								mUUSVO.setContent4("월간보고");
							}
							mUUSVO.setDetailUri("/businessSupport/dutyReport/dutyReportDetail?dr_number="+dutyReportList.get(j).getDr_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("받은업무보고");
							mUUSS.setUriAddr1("/businessSupport/dutyReport/dutyReport");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("받은업무보고");
							mUUSS.setUriAddr2("/businessSupport/dutyReport/dutyReport");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("받은업무보고");
							mUUSS.setUriAddr3("/businessSupport/dutyReport/dutyReport");
						}
					}
					break;
				case "mid_10": //미결재문서
					eaList = new ArrayList<Electronic_ApprovalVO>();//미완료문서
					ea_numberList = approvalProgressDao.ap_selectEaNumberByMemId(member.getMem_number());//접속자의 사원번호로 결재라인에 올라가있는 결재번호 검색
					List<MemberVO> memberList = new ArrayList<MemberVO>();
					for (String ea_number: ea_numberList) {
						//한 결재의 마지막 결재우선순위 검색
						int lastAstPriority = approvalProgressDao.selectLastAstPriority(ea_number);
						//한 사원의 한 결재의 우선순위 검색 
						Map<String, String> paramMap = new HashMap<String,String>();
						paramMap.put("ast_ea_number", ea_number);
						paramMap.put("ast_mem_number", member.getMem_number());
						int memberAstPriority = approvalProgressDao.selectOneAstPriority(paramMap);
						//한 결재의 마지막 결재이력의 우선순위 검색
						int lastAhHistory = approvalProgressDao.selectLastApprovalHistory(ea_number);
						//미결재문서 (자신의 우선순위 차례이면)
						if(lastAhHistory+1==memberAstPriority){
							Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
							eaList.add(eaVO);
						}else if(lastAhHistory==lastAstPriority+1){
							Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
							eaList.add(eaVO);
						}
					}
					for(int j = 0; j < eaList.size(); j++){
						MemberVO nameVO = CommonDao.common_selectMemberByMemNumber(eaList.get(j).getEa_mem_number());
						eaList.get(j).setWrite_name(nameVO.getMem_name());
					}
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("품의번호");
					titleList.add("제목");
					titleList.add("기안자");
					titleList.add("기안일");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(eaList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(eaList.get(j).getEa_number());
							mUUSVO.setContent2(eaList.get(j).getEa_title());
							mUUSVO.setContent3(eaList.get(j).getWrite_name());
							mUUSVO.setContent4(eaList.get(j).getEa_writedate());
							mUUSVO.setDetailUri("/electronicApproval/approvalProgress/waitingApprovalDetail?ea_number="+eaList.get(j).getEa_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("미결재문서");
							mUUSS.setUriAddr1("/electronicApproval/approvalProgress/waitingApproval");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("미결재문서");
							mUUSS.setUriAddr2("/electronicApproval/approvalProgress/waitingApproval");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("미결재문서");
							mUUSS.setUriAddr3("/electronicApproval/approvalProgress/waitingApproval");
						}
					}else{
						for(int j = 0; j < eaList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(eaList.get(j).getEa_number());
							mUUSVO.setContent2(eaList.get(j).getEa_title());
							mUUSVO.setContent3(eaList.get(j).getWrite_name());
							mUUSVO.setContent4(eaList.get(j).getEa_writedate());
							mUUSVO.setDetailUri("/electronicApproval/approvalProgress/waitingApprovalDetail?ea_number="+eaList.get(j).getEa_number());
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("미결재문서");
							mUUSS.setUriAddr1("/electronicApproval/approvalProgress/waitingApproval");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("미결재문서");
							mUUSS.setUriAddr2("/electronicApproval/approvalProgress/waitingApproval");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("미결재문서");
							mUUSS.setUriAddr3("/electronicApproval/approvalProgress/waitingApproval");
						}
					}
					break;
				case "mid_11": //반려문서
					CommonApproval_TOTALVO vo = new CommonApproval_TOTALVO();
					vo.setEa_mem_number(member.getMem_number());
					EARefusedList = individualDocumentBoxDao.getRefusedApprovalList_ID(vo);
					list = new ArrayList<MainUserUiSelectVO>(); //내용을 담을 리스트
					mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
					titleList = new ArrayList<String>();//제목이 들어가는 곳
					titleList.add("품의번호");
					titleList.add("제목");
					titleList.add("문서분류");
					titleList.add("기안자");
					if(one.equals(uiCodeName.get(i))){
						mUUSS.setTitle1(titleList);
					}else if(two.equals(uiCodeName.get(i))){
						mUUSS.setTitle2(titleList);
					}else{
						mUUSS.setTitle3(titleList);
					}
					if(EARefusedList.size()>count){
						for(int j = 0; j < count; j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(EARefusedList.get(j).getEa_number());
							mUUSVO.setContent2(EARefusedList.get(j).getEa_title());
							mUUSVO.setContent3(EARefusedList.get(j).getDoc_name());
							mUUSVO.setContent4(EARefusedList.get(j).getMem_name());
							mUUSVO.setDetailUri("/electronicApproval/individualDocumentBox/refusedApprovalBox");
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("반려문서함");
							mUUSS.setUriAddr1("/electronicApproval/individualDocumentBox/refusedApprovalBox");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("반려문서함");
							mUUSS.setUriAddr2("/electronicApproval/individualDocumentBox/refusedApprovalBox");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("반려문서함");
							mUUSS.setUriAddr3("/electronicApproval/individualDocumentBox/refusedApprovalBox");
						}
					}else{
						for(int j = 0; j < EARefusedList.size(); j++){
							mUUSVO = new MainUserUiSelectVO();//내용이들어가는곳
							mUUSVO.setContent1(EARefusedList.get(j).getEa_number());
							mUUSVO.setContent2(EARefusedList.get(j).getEa_title());
							mUUSVO.setContent3(EARefusedList.get(j).getDoc_name());
							mUUSVO.setContent4(EARefusedList.get(j).getMem_name());
							mUUSVO.setDetailUri("/electronicApproval/individualDocumentBox/refusedApprovalBox");
							list.add(mUUSVO);
						}
						if(one.equals(uiCodeName.get(i))){
							mUUSS.setContent1(list);
							mUUSS.setMenu1("반려문서함");
							mUUSS.setUriAddr1("/electronicApproval/individualDocumentBox/refusedApprovalBox");
						}else if(two.equals(uiCodeName.get(i))){
							mUUSS.setContent2(list);
							mUUSS.setMenu2("반려문서함");
							mUUSS.setUriAddr2("/electronicApproval/individualDocumentBox/refusedApprovalBox");
						}else{
							mUUSS.setContent3(list);
							mUUSS.setMenu3("반려문서함");
							mUUSS.setUriAddr3("/electronicApproval/individualDocumentBox/refusedApprovalBox");
						}
					}
					break;
				default:
					break;
				}
				count = 5;
			}
		}
		model.addAttribute("mUUSS", mUUSS);
	}

	
	/**
	 * 유저이름을 넣어주는 메서드
	 * @return
	 */
	public String userNameSetting (String mem_number){
		MemberVO mem = CommonDao.common_selectMemberByMemNumber(mem_number);
		return mem.getMem_name();
	}

	@Override
	public MiddleMenuVO selectMiddleMenuFind_UI(String mid_name) {
		MiddleMenuVO mid = appointedUIDao.selectMiddleMenuFind_UI(mid_name);
		return mid;
	}

	@Override
	public MiddleMenuVO selectBigMiddleMenuFind_UI(String mid_number) {
		MiddleMenuVO mid = appointedUIDao.selectBigMiddleMenuFind_UI(mid_number);
		return mid;
	}
	
	public int eaListCount(String mem_number){
		List<Electronic_ApprovalVO> eaList = new ArrayList<Electronic_ApprovalVO>();//미완료문서
		List<String> ea_numberList = approvalProgressDao.ap_selectEaNumberByMemId(mem_number);//접속자의 사원번호로 결재라인에 올라가있는 결재번호 검색
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for (String ea_number: ea_numberList) {
			//최고 우선순위 (반려면 +2)
			int priority = approvalProgressDao.selectMaxPriority(ea_number);
			
			//한 결재의 마지막 결재우선순위 검색
			int lastAstPriority = approvalProgressDao.selectLastAstPriority(ea_number);
			//한 결재의 마지막 결재스탭번호
			int lastAstNumber = approvalProgressDao.selectLastApprovalStep(ea_number);
			
			//한 사원의 한 결재의 우선순위 검색 
			Map<String, String> paramMap = new HashMap<String,String>();
			paramMap.put("ast_ea_number", ea_number);
			paramMap.put("ast_mem_number", mem_number);
			int memberAstPriority = approvalProgressDao.selectOneAstPriority(paramMap);
			//한 결재의 마지막 결재이력의 우선순위 검색
			int lastAhHistory = approvalProgressDao.selectLastApprovalHistory(ea_number);
			
			//미결재문서 (자신의 우선순위 차례이면)
			if(priority==lastAstPriority+2){
			}else{
				if(lastAhHistory+1==memberAstPriority){
					Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
					eaList.add(eaVO);
				}else if(lastAhHistory==lastAstPriority+1){
					Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
					eaList.add(eaVO);
				}
				
			}
		}
		for(int j = 0; j < eaList.size(); j++){
			MemberVO nameVO = CommonDao.common_selectMemberByMemNumber(eaList.get(j).getEa_mem_number());
			eaList.get(j).setWrite_name(nameVO.getMem_name());
		}
		int eaListCount = 0;
		for(int i = 0; i < eaList.size(); i++){
			eaListCount++;
		}
		return eaListCount;
	}
	
	public int userUiApprovalCount(String mem_number){
		int count = appointedUIDao.userUiApprovalCount(mem_number);
		return count;
	}
	
	public int theRestDocumentBoxCount(String mem_number){
		CommonApproval_TOTALVO vo = new CommonApproval_TOTALVO();
		vo.setEa_mem_number(mem_number);
		int count = 0;
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxDao.getApprovalREList(vo);
		for(int i = 0; i < appList.size(); i++){
			count++;
		}
		return count;
	}
}
