package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.security.Principal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import oracle.net.aso.p;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.ApprovalParamVO;
import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Approval_StepVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.CommonApproval_TOTALVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.Electronic_ApprovalViewVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;
import com.nyngw.electronicApproval.draft.dao.DraftDaoImpl;
import com.nyngw.electronicApproval.individualDocumentBox.dao.IndividualDocumentBoxDaoImpl;
import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;

@Service
public class IndividualDocumentBoxServiceImpl implements IndividualDocumentBoxService {
	
	@Autowired
	private IndividualDocumentBoxDaoImpl individualDocumentBoxDao;
	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;
	@Autowired
	private CommonServiceImpl commonServiceImpl;
	@Autowired
	private DraftDaoImpl draftDao;
	@Autowired
	private AppointedUIServiceImpl appointedUIService;
	
	@Override
	public Electronic_ApprovalViewVO sangsinList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int boardTotalCount = individualDocumentBoxDao.selectsangsinCount(select.getMem_number());
			List<Electronic_ApprovalVO> sangsinList = null;
			int firstRow = 0;
			int endRow = 0;
			if (boardTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				sangsinList = individualDocumentBoxDao.selectSAB(firstRow, endRow, select);
				if(select.getVal()!=null && !select.getVal().equals("")){
					boardTotalCount = individualDocumentBoxDao.boardsangsinCount(select);
				}
			} else {
				currentPageNumber = 0;
				sangsinList = Collections.emptyList();
			}
			return  new Electronic_ApprovalViewVO(sangsinList, boardTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	
	

	@Override
	public List<Electronic_ApprovalVO> defaultCAB(String mem_id) {
		return individualDocumentBoxDao.selectCAB(mem_id);
	}

	@Override
	public List<Electronic_ApprovalVO> defaultRAB(String mem_id) {
		return individualDocumentBoxDao.selectRAB(mem_id);
	}
//-----------------------------------------------------------------------
	@Override
	public List<String> ap_selectEaNumberByMemId(String mem_number){
		return approvalProgressDao.ap_selectEaNumberByMemId(mem_number);
	}

	@Override
	public int selectLastAstPriority(String ast_ea_number) {
		return approvalProgressDao.selectLastAstPriority(ast_ea_number);
	}

	@Override
	public int selectOneAstPriority(Map paramMap) {
		return approvalProgressDao.selectOneAstPriority(paramMap);
	}

	@Override
	public int selectLastApprovalHistory(String ah_ea_number) {
		return approvalProgressDao.selectLastApprovalHistory(ah_ea_number);
	}

	@Override
	public Approval_HistoryVO selectAhMember(Map param) {
		return individualDocumentBoxDao.selectAhMember(param);
	}

	@Override
	public List<Approval_HistoryVO> selectAhAll(String ea_number) {
		return individualDocumentBoxDao.selectAhAll(ea_number);
	}

	@Override
	public String selectMemberDept(String mem_number) {
		return individualDocumentBoxDao.selectMemberDept(mem_number);
	}
	
	@Override
	public String selectEaStepMember(Map map){
		return individualDocumentBoxDao.selectEaStepMember(map);
	}
	
	@Override
	public String selectDeptName(String dept_number){
		return individualDocumentBoxDao.selectDeptName(dept_number);
	}

	@Override
	public String selectMemberPosition(String mem_number) {
		return individualDocumentBoxDao.selectMemberPosition(mem_number);
	}

	@Override
	public String selectPositionName(String position_number) {
		return individualDocumentBoxDao.selectPositionName(position_number);
	}

	//미결제문서 자세히보기
	public void waDetail(Model model, String ea_number,Principal principal,int check) {

		//결재정보 setting
		Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
		model.addAttribute("eaVO",eaVO);
		//작성자 이름
		MemberVO member = commonServiceImpl.findMemberByMemId(principal.getName());
		model.addAttribute("mem_name",member.getMem_name());
		
		//결재스탭 정보 setting 
		Approval_StepVO asVO = new Approval_StepVO();
		asVO.setAst_ea_number(ea_number);
		asVO.setAst_al_number("A");
		List<Approval_StepVO> approvalMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("B");
		List<Approval_StepVO> agreementMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("C");
		List<Approval_StepVO> implementMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("D");
		List<Approval_StepVO> referenceMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		
		List<MemberVO> approvalMember= new ArrayList<MemberVO>();
		List<MemberVO> agreementMember= new ArrayList<MemberVO>();
		String implementMemberName = "";
		String referenceMemberName = "";
		MemberVO member2 =null;
		
		//결재 이력 정보
		List<String> approvalMem_sign = new ArrayList<String>();
		List<String> agreementMem_sign = new ArrayList<String>();
		int lastAhHistory = approvalProgressDao.selectLastApprovalHistory(ea_number);	//결재 이력에 등록된 마지막 우선순위를 검색
		int index=1;	//결재자와 합의자의 수를 저장하기 위한 변수
		int indexA = 0;	//결재한 결재자의 수를 저장하기 위한 변수
		int indexB = 0;	//합의한 합의자의 수를 저장하기 위한 변수
		
		//합의자의 싸인을 담기 위함
		for (Approval_StepVO approval_StepVO : agreementMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			agreementMember.add(member2);
			if(lastAhHistory>=index){	//마지막 결재자의 우선순위가 인덱스보다 크다는 것은 for문에 들어온 결재가 완료되었다는 것
//					if()
				agreementMem_sign.add(member2.getMem_sign());
				indexB++;
			}
			index++;
		}
		//결재자의 싸인을 담기 위함
		for (Approval_StepVO approval_StepVO : approvalMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			approvalMember.add(member2);
			if(lastAhHistory>=index){
				approvalMem_sign.add(member2.getMem_sign());
				indexA++;
			}
			index++;
		}
		model.addAttribute("approvalMem_sign",approvalMem_sign);
		model.addAttribute("agreementMem_sign",agreementMem_sign);
		
		model.addAttribute("indexA",indexA);
		model.addAttribute("indexB",indexB);
		
		
		index = 0;
		for (Approval_StepVO approval_StepVO : implementMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			if(index!=0){
				implementMemberName += ", ";
			}
			implementMemberName += (member2.getMem_name());
		}
		for (Approval_StepVO approval_StepVO : referenceMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			if(index!=0){
				referenceMemberName += ", ";
			}
			referenceMemberName += (member2.getMem_name());
		}
		model.addAttribute("approvalMember",approvalMember);
		model.addAttribute("agreementMember",agreementMember);
		model.addAttribute("implementMemberName",implementMemberName);
		model.addAttribute("referenceMemberName",referenceMemberName);
		
		//자신의 결재우선순위
		//작성자 이름
		MemberVO member3 = commonServiceImpl.findMemberByMemId(principal.getName());
		
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("ast_ea_number", ea_number);
		paramMap.put("ast_mem_number",member3.getMem_number());
		
		//int memberAstPriority = approvalProgressDao.selectOneAstPriority(paramMap);
		//model.addAttribute("ast_number","ast"+memberAstPriority);
		
		//자신의 결재 종류 (합의인지 결재인지)
		paramMap = new HashMap<String,String>();
		paramMap.put("ea_number", ea_number);
		paramMap.put("mem_id", principal.getName());
		
		String mem_al_number = approvalProgressDao.EA_selectAstALNumber(paramMap);
		model.addAttribute("mem_al_number",mem_al_number);
		
		//전체 결재선의 크기
		int lastAstPriorityOfA = approvalProgressDao.selectLastAstPriorityOfA(ea_number);
		int lastAstPriorityOfB = approvalProgressDao.selectLastAstPriorityOfB(ea_number);
		model.addAttribute("lastAstPriorityOfA",5-lastAstPriorityOfA);	//lastAstPriorityOfA = 5에서 A의 전체 결재선을 뺀 수 = 빈칸의 수 
		model.addAttribute("lastAstPriorityOfB",5-lastAstPriorityOfB);	//lastAstPriorityOfB = 5에서 B의 전체 결재선을 뺀 수 = 빈칸의 수
		
		//결재 이력중 A와 B의 갯수
		List<String> ahAstNumberList = approvalProgressDao.selectAhAstNumberByEaNumber(ea_number);
		int countA = 0;
		int countB = 0;
		for (String ast_number : ahAstNumberList) {
			paramMap = new HashMap<String, String>();
			paramMap.put("ast_number", ast_number);
			paramMap.put("ah_ea_number", ea_number);
			if(approvalProgressDao.selectAstAllNumberByAstNumber(paramMap).equals('A')){
				countA++;
			}else{
				countB++;
			}
		}

		model.addAttribute("emptyStartA",lastAstPriorityOfA+1);	//3
		model.addAttribute("emptyStartB",lastAstPriorityOfB+1);
		model.addAttribute("noStartA",indexA+1);
		model.addAttribute("noStartB",indexB+1);
		model.addAttribute("noEndA",lastAstPriorityOfA);
		model.addAttribute("noEndB",lastAstPriorityOfB);
		
		
		//A
		//총 5
		//A 결재선 2 lastAstPriorityOfA
		
		//A 2중 1 결재했으면 1	1부터  // indexA = 1 까지 
		//A 2중 결재 안한 거 1	indexA+1 = 2부터 // lastAstPriorityOfA =1개		2-1 = 1
		
		//A 빈칸 3- 시작lastAstPriorityOfA+1=3부터 // 5까지
		
	}
	
	
	public void searchMemberByMemberId(String mem_id,Model model,String doc_number, String ea_number) {
		MemberVO member = draftDao.draft_selectMemberByMemberId(mem_id);
		
		//품의번호 setting
		model.addAttribute("ea_number",ea_number);
		
		//작성일자 setting
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(new Date());
		model.addAttribute("ea_writedate",today);

		//기안자 정보 (기안부서, 기안자) setting
		model.addAttribute("member",member);
		
		//문서 content setting
		String doc_content = draftDao.ea_selectDocContent(doc_number);
		model.addAttribute("doc_content", doc_content);
		model.addAttribute("doc_number", doc_number);
	}
	
	public void submitApproval(Model model, Electronic_ApprovalVO eaVO,
			String ea_content, ApprovalParamVO apVO,String param_ea_number) {
		//eaVO 등록
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date ea_startdate=null;
		Date ea_enddate=null;
		Date ea_writedate=null;
		try {
			ea_startdate = transFormat.parse(apVO.getParam_ea_startdate());
			ea_enddate = transFormat.parse(apVO.getParam_ea_enddate());
			ea_writedate = transFormat.parse(apVO.getParam_ea_writedate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		eaVO.setEa_content(ea_content);
		eaVO.setEa_startdate(ea_startdate);
		eaVO.setEa_enddate(ea_enddate);
		eaVO.setEa_writedate(ea_writedate);
		
		draftDao.draft_deleteApproval(eaVO);
		
		draftDao.draft_insertApproval(eaVO);
		
		//스텝등록
		String approvalMember1 = "";
		String approvalMember2 = "";
		String approvalMember3 = "";
		String approvalMember4 = "";
		String approvalMember5 = "";
		String agreementMember1 = "";
		String agreementMember2 = "";
		String agreementMember3 = "";
		String agreementMember4 = "";
		String agreementMember5 = "";
		String implementMembers ="";
		String referenceMembers ="";
		Map<String, String> paramMap = null;
		//합의자
		if(apVO.getAgreementMember1()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember1 =apVO.getAgreementMember1();
			paramMap.put("ast_mem_number", agreementMember1);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast1");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "1");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember2()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember2 =apVO.getAgreementMember2(); 
			paramMap.put("ast_mem_number", agreementMember2);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast2");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "2");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember3()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember3 =apVO.getAgreementMember3();
			paramMap.put("ast_mem_number", agreementMember3);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast3");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "3");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember4()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember4 =apVO.getAgreementMember4();
			paramMap.put("ast_mem_number", agreementMember4);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast4");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "4");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember5()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember5 =apVO.getAgreementMember5();
			paramMap.put("ast_mem_number", agreementMember5);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast5");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "5");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		int priority = 0; 
		if(paramMap!=null ){
			priority = Integer.parseInt(paramMap.get("ast_priority"));
		}
		//결재자
		if(apVO.getApprovalMember1()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember1 =apVO.getApprovalMember1();
			paramMap.put("ast_mem_number", approvalMember1);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+1));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+1)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember2()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember2 =apVO.getApprovalMember2();
			paramMap.put("ast_mem_number", approvalMember2);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+2));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+2)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember3()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember3 =apVO.getApprovalMember3();
			paramMap.put("ast_mem_number", approvalMember3);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+3));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+3)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember4()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember4 =apVO.getApprovalMember4();
			paramMap.put("ast_mem_number", approvalMember4);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+4));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+4)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember5()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember5 =apVO.getApprovalMember5();
			paramMap.put("ast_mem_number", approvalMember5);
			paramMap.put("ast_ea_number", param_ea_number);
			paramMap.put("ast_number", "ast"+(priority+5));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+5)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(paramMap!=null ){
			priority = Integer.parseInt(paramMap.get("ast_number").substring(3));
		}
		//시행자
		if(apVO.getImplementMembers()!=null&&!apVO.getImplementMembers().equals("")){
			paramMap = new HashMap<String, String>(); 
			implementMembers = apVO.getImplementMembers();
			StringTokenizer st = new StringTokenizer(implementMembers,","); 
			if(!st.hasMoreTokens()){
				paramMap.put("ast_mem_number", implementMembers);
				paramMap.put("ast_ea_number", param_ea_number);
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "C");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
			while(st.hasMoreTokens()) { 
				paramMap.put("ast_mem_number", st.nextToken());
				paramMap.put("ast_ea_number", param_ea_number);
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "C");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
		}
		if(paramMap.get("ast_number")!=null ){
			priority = Integer.parseInt(paramMap.get("ast_number").substring(3));
		}
		//참조자
		if(apVO.getReferenceMembers()!=null&&!apVO.getReferenceMembers().equals("")){
			paramMap = new HashMap<String, String>(); 
			referenceMembers = apVO.getReferenceMembers();
			StringTokenizer st = new StringTokenizer(referenceMembers,","); 
			if(!st.hasMoreTokens()){
				paramMap.put("ast_mem_number", referenceMembers);
				paramMap.put("ast_ea_number", param_ea_number);
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "D");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
			while(st.hasMoreTokens()) { 
				paramMap.put("ast_mem_number", st.nextToken());
				paramMap.put("ast_ea_number", param_ea_number);
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "D");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
		}	
	}		
		
	public String findLastAhStatus(String ea_number) {
		return individualDocumentBoxDao.ID_selectAhStatus(ea_number);
	}
	
	public String findLastAstMember(String ea_number) {
		return individualDocumentBoxDao.ID_selectAllAS(ea_number);
	}

	public List<CommonApproval_TOTALVO> getRefusedApprovalList(CommonApproval_TOTALVO vo) {
		return individualDocumentBoxDao.getRefusedApprovalList_ID(vo);
	}



	public void editApprovalSet(Model model, String ea_number, Principal principal){
		Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
		model.addAttribute("eaVO", eaVO);
		
		MemberVO member = null;
		try {
			member = appointedUIService.checkMember(principal.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("member", member);
		
		//결재스탭 정보 setting 
		Approval_StepVO asVO = new Approval_StepVO();
		asVO.setAst_ea_number(eaVO.getEa_number());
		asVO.setAst_al_number("A");
		List<Approval_StepVO> approvalMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("B");
		List<Approval_StepVO> agreementMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("C");
		List<Approval_StepVO> implementMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		asVO.setAst_al_number("D");
		List<Approval_StepVO> referenceMemberList = approvalProgressDao.selectAstMemNumberByEaNumber(asVO);
		
		List<MemberVO> approvalMember= new ArrayList<MemberVO>();
		List<MemberVO> agreementMember= new ArrayList<MemberVO>();
		String implementMemberName = "";
		String referenceMemberName = "";
		MemberVO member2 =null;
		
		//결재 이력 정보
		List<String> approvalMem_sign = new ArrayList<String>();
		List<String> agreementMem_sign = new ArrayList<String>();
		int lastAhHistory = approvalProgressDao.selectLastApprovalHistory(eaVO.getEa_number());	//결재 이력에 등록된 마지막 우선순위를 검색
//		Approval_HistoryVO approvalHistory = approvalProgressDao.selectLastAhStatus(eaVO.getEa_number());
//		String lastAhStatus = approvalHistory.getAh_status();
		int index=1;	//결재자와 합의자의 수를 저장하기 위한 변수
		
		int indexA = 0;	//결재한 결재자의 수를 저장하기 위한 변수
		int indexB = 0;	//합의한 합의자의 수를 저장하기 위한 변수
		
		//합의자의 싸인을 담기 위함
		for (Approval_StepVO approval_StepVO : agreementMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			agreementMember.add(member2);
			if(lastAhHistory>=index){	//마지막 결재자의 우선순위가 인덱스보다 크다는 것은 for문에 들어온 결재가 완료되었다는 것
				agreementMem_sign.add(member2.getMem_sign());
				indexB++;
			}
			index++;
		}
		//결재자의 싸인을 담기 위함
		for (Approval_StepVO approval_StepVO : approvalMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			approvalMember.add(member2);
			if(lastAhHistory>=index){
				approvalMem_sign.add(member2.getMem_sign());
				indexA++;
			}
			index++;
		}
		
		model.addAttribute("approvalMem_sign",approvalMem_sign);
		model.addAttribute("agreementMem_sign",agreementMem_sign);
		
		index = 0;
		for (Approval_StepVO approval_StepVO : implementMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			if(index!=0){
				implementMemberName += ", ";
			}
			implementMemberName += (member2.getMem_name());
		}
		for (Approval_StepVO approval_StepVO : referenceMemberList) {
			member2 = commonServiceImpl.findMemberByMemNumber(approval_StepVO.getAst_mem_number());
			if(index!=0){
				referenceMemberName += ", ";
			}
			referenceMemberName += (member2.getMem_name());
		}
		model.addAttribute("approvalMember",approvalMember);
		model.addAttribute("agreementMember",agreementMember);
		model.addAttribute("implementMemberName",implementMemberName);
		model.addAttribute("referenceMemberName",referenceMemberName);
		
		//자신의 결재우선순위
		//작성자 이름
//		MemberVO member3 = commonServiceImpl.findMemberByMemId(principal.getName());
//		
		Map<String, String> paramMap = new HashMap<String,String>();
//		paramMap.put("ast_ea_number", eaVO.getEa_number());
//		paramMap.put("ast_mem_number",member3.getMem_number());
//		int memberAstPriority = approvalProgressDao.selectOneAstPriority(paramMap);
//		model.addAttribute("ast_number","ast"+memberAstPriority);
		
		//자신의 결재 종류 (합의인지 결재인지)
		paramMap = new HashMap<String,String>();
		paramMap.put("ea_number", eaVO.getEa_number());
		paramMap.put("mem_id", principal.getName());
		
		String mem_al_number = approvalProgressDao.EA_selectAstALNumber(paramMap);
		model.addAttribute("mem_al_number",mem_al_number);
		
		//전체 결재선의 크기
		int lastAstPriorityOfA = approvalProgressDao.selectLastAstPriorityOfA(eaVO.getEa_number());
		int lastAstPriorityOfB = approvalProgressDao.selectLastAstPriorityOfB(eaVO.getEa_number());
		model.addAttribute("lastAstPriorityOfA",5-lastAstPriorityOfA);	//lastAstPriorityOfA = 5에서 A의 전체 결재선을 뺀 수 = 빈칸의 수 
		model.addAttribute("lastAstPriorityOfB",5-lastAstPriorityOfB);	//lastAstPriorityOfB = 5에서 B의 전체 결재선을 뺀 수 = 빈칸의 수
		
		//결재 이력중 A와 B의 갯수
		List<String> ahAstNumberList = approvalProgressDao.selectAhAstNumberByEaNumber(eaVO.getEa_number());
		int countA = 0;
		int countB = 0;
		for (String ast_number : ahAstNumberList) {
			paramMap = new HashMap<String, String>();
			paramMap.put("ast_number", ast_number);
			paramMap.put("ah_ea_number", eaVO.getEa_number());
			if(approvalProgressDao.selectAstAllNumberByAstNumber(paramMap).equals('A')){
				countA++;
			}else{
				countB++;
			}
		}

		model.addAttribute("emptyStartA",lastAstPriorityOfA+1);	//3
		model.addAttribute("emptyStartB",lastAstPriorityOfB+1);
		model.addAttribute("noStartA",indexA+1);
		model.addAttribute("noStartB",indexB+1);
		model.addAttribute("noEndA",lastAstPriorityOfA);
		model.addAttribute("noEndB",lastAstPriorityOfB);
	}



	public void editApproval(Model model, Electronic_ApprovalVO eaVO,
			String ea_content, ApprovalParamVO apVO, String param_ea_startdate, String param_ea_enddate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = null;
		Date enddate = null;
		try {
			startdate = transFormat.parse(param_ea_startdate);
			enddate = transFormat.parse(param_ea_enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		eaVO.setEa_content(ea_content);
		eaVO.setEa_startdate(startdate);
		eaVO.setEa_enddate(enddate);
		approvalProgressDao.EA_updateApproval(eaVO);
		
		individualDocumentBoxDao.draft_deleteApprovalStep(eaVO.getEa_number());
		
		//스텝등록
		String approvalMember1 = "";
		String approvalMember2 = "";
		String approvalMember3 = "";
		String approvalMember4 = "";
		String approvalMember5 = "";
		String agreementMember1 = "";
		String agreementMember2 = "";
		String agreementMember3 = "";
		String agreementMember4 = "";
		String agreementMember5 = "";
		String implementMembers ="";
		String referenceMembers ="";
		Map<String, String> paramMap = null;
		//합의자
		if(apVO.getAgreementMember1()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember1 =apVO.getAgreementMember1();
			paramMap.put("ast_mem_number", agreementMember1);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast1");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "1");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember2()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember2 =apVO.getAgreementMember2(); 
			paramMap.put("ast_mem_number", agreementMember2);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast2");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "2");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember3()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember3 =apVO.getAgreementMember3();
			paramMap.put("ast_mem_number", agreementMember3);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast3");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "3");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember4()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember4 =apVO.getAgreementMember4();
			paramMap.put("ast_mem_number", agreementMember4);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast4");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "4");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getAgreementMember5()!=null){
			paramMap = new HashMap<String, String>(); 
			agreementMember5 =apVO.getAgreementMember5();
			paramMap.put("ast_mem_number", agreementMember5);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast5");
			paramMap.put("ast_al_number", "B");
			paramMap.put("ast_priority", "5");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		int priority = 0; 
		if(paramMap!=null ){
			priority = Integer.parseInt(paramMap.get("ast_priority"));
		}
		//결재자
		if(apVO.getApprovalMember1()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember1 =apVO.getApprovalMember1();
			paramMap.put("ast_mem_number", approvalMember1);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast"+(priority+1));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+1)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember2()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember2 =apVO.getApprovalMember2();
			paramMap.put("ast_mem_number", approvalMember2);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast"+(priority+2));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+2)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember3()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember3 =apVO.getApprovalMember3();
			paramMap.put("ast_mem_number", approvalMember3);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast"+(priority+3));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+3)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember4()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember4 =apVO.getApprovalMember4();
			paramMap.put("ast_mem_number", approvalMember4);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast"+(priority+4));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+4)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(apVO.getApprovalMember5()!=null){
			paramMap = new HashMap<String, String>(); 
			approvalMember5 =apVO.getApprovalMember5();
			paramMap.put("ast_mem_number", approvalMember5);
			paramMap.put("ast_ea_number", eaVO.getEa_number());
			paramMap.put("ast_number", "ast"+(priority+5));
			paramMap.put("ast_al_number", "A");
			paramMap.put("ast_priority", (priority+5)+"");
			draftDao.draft_insertApprovalStep(paramMap);
		}
		if(paramMap!=null ){
			priority = Integer.parseInt(paramMap.get("ast_number").substring(3));
		}
		//시행자
		if(apVO.getImplementMembers()!=null&&!apVO.getImplementMembers().equals("")){
			paramMap = new HashMap<String, String>(); 
			implementMembers = apVO.getImplementMembers();
			StringTokenizer st = new StringTokenizer(implementMembers,","); 
			if(!st.hasMoreTokens()){
				paramMap.put("ast_mem_number", implementMembers);
				paramMap.put("ast_ea_number", eaVO.getEa_number());
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "C");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
			while(st.hasMoreTokens()) { 
				paramMap.put("ast_mem_number", st.nextToken());
				paramMap.put("ast_ea_number", eaVO.getEa_number());
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "C");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
		}
		if(paramMap.get("ast_number")!=null ){
			priority = Integer.parseInt(paramMap.get("ast_number").substring(3));
		}
		//참조자
		if(apVO.getReferenceMembers()!=null&&!apVO.getReferenceMembers().equals("")){
			paramMap = new HashMap<String, String>(); 
			referenceMembers = apVO.getReferenceMembers();
			StringTokenizer st = new StringTokenizer(referenceMembers,","); 
			if(!st.hasMoreTokens()){
				paramMap.put("ast_mem_number", referenceMembers);
				paramMap.put("ast_ea_number", eaVO.getEa_number());
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "D");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
			while(st.hasMoreTokens()) { 
				paramMap.put("ast_mem_number", st.nextToken());
				paramMap.put("ast_ea_number", eaVO.getEa_number());
				paramMap.put("ast_number", "ast"+(priority+1));
				paramMap.put("ast_al_number", "D");
				paramMap.put("ast_priority", "0");
				draftDao.draft_insertApprovalStep(paramMap);
				priority++;
			}
		}	
	}
	
}
