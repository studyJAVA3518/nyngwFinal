package com.nyngw.sharingInformation.memberInformation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.AddressBookViewVO;
import com.nyngw.dto.BirthdayVO;
import com.nyngw.dto.BirthdayViewVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.sharingInformation.memberInformation.dao.MemberInformationDaoImpl;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {
	@Autowired
	private MemberInformationDaoImpl memberInformationDao;

	private static final int ADDRESSBOOK_COUNT_PER_PAGE = 5;	//한 주소록에 보여줄 주소록 수
	private static final int BIRTHDAY_COUNT_PER_PAGE = 5;		//한 생일정보에 보여줄 생일자 수
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;			//한 페이지에 보여줄 페이지 번호 수
	
	//조직도를 출력하기 위해 모든 부서를 검색해 맵 형식으로 (Json데이터 형식)으로 변환하여 리턴해주는 메서드
	public List<Map> getAllDepartment() {
		List<DepartmentVO> departmentList = memberInformationDao.SI_selectDepartment();

		List<Map> treeJsonList = new ArrayList<Map>();
		
		for (DepartmentVO department : departmentList) {
			Map<String,String> treejsonMap = new HashMap<String,String>();
			treejsonMap.put("label", department.getDept_name());
			treejsonMap.put("itemId", department.getDept_number());
			treejsonMap.put("parentId", department.getDept_parents());
			
			treeJsonList.add(treejsonMap);
		}
		return treeJsonList;
	}

	//주소록 정보를 가져오기 위해 DB에 접속하여 검색하고 model에 담아주는 메서드
	public void getMemberInfo(int pageNumber,Model model,String mem_name) {
		//처리에 필요한 것들 준비
		List<AddressBookVO> addressBookList = new ArrayList<AddressBookVO>();
		int currentPageNumber = pageNumber;
		int memberTotalCount = 0;
		int firstRow = 0;
		int endRow = 0;
		//사원의 수(count)를 검색 
		if(mem_name.equals("")){	//모든 사원의 수
			memberTotalCount = memberInformationDao.SI_selectAllMemberCount();
		}else{						//검색한 이름을 가진 사원의 수
			memberTotalCount = memberInformationDao.SI_selectMemberCount(mem_name);
		}
		
		//위에서 구한 숫자로 주소록 검색 실행
		if (memberTotalCount > 0) {	//만약 검색한 사원의 정보고 DB에 있으면
			//////페이지 번호에 따라 필요한 갯수만큼만 검색하기 위해 rowBounds를 설정해주는 곳//////
			firstRow = (pageNumber - 1) * ADDRESSBOOK_COUNT_PER_PAGE + 1;
			endRow = firstRow + ADDRESSBOOK_COUNT_PER_PAGE - 1;
			int offset = firstRow - 1;
			int limit = endRow - firstRow + 1;
			RowBounds rowBounds = new RowBounds(offset, limit);
			//////////////////////////////////////////////////////////////////
			
			addressBookList = memberInformationDao.SI_selectAllMember(mem_name, rowBounds);	//rowBounds를 가지고 회원을 검색
		} else {	//만약 검색한 사원이 없으면
			currentPageNumber = 0;
			addressBookList = Collections.emptyList();
		}
		
		//위에서 구한 값들을 View에 설정해준 후 model에 담아 보낸다.
		AddressBookViewVO addressBookViewVO = new AddressBookViewVO(addressBookList, memberTotalCount,
				currentPageNumber, ADDRESSBOOK_COUNT_PER_PAGE, firstRow, endRow);
		model.addAttribute("addressBookViewVO", addressBookViewVO);
		
		//페이지 번호별로 이전 다음 설정해주기 위해 설정
		if(addressBookViewVO.getPageTotalCount()>0){
			int beginPageNumber = (addressBookViewVO.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > addressBookViewVO.getPageTotalCount()){
				endPageNumber = addressBookViewVO.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", addressBookViewVO.getAddressBookList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		
		//검색어를 넘겨주기 위한 model 셋팅
		model.addAttribute("mem_name",mem_name);
	}
	
	public void getBirthdayMember(int pageNumber,Model model,String month) {
		month = (Integer.parseInt(month) < 10 ? "0" : "") + month;
		List<BirthdayVO> birthdayList = new ArrayList<BirthdayVO>();
		int currentPageNumber = pageNumber;
		int memberTotalCount = 0;
		memberTotalCount = memberInformationDao.SI_selectBirthdayMemberCount(month);
		int firstRow = 0;
		int endRow = 0;
		if (memberTotalCount > 0) {
			firstRow = (pageNumber - 1) * BIRTHDAY_COUNT_PER_PAGE + 1;
			endRow = firstRow + BIRTHDAY_COUNT_PER_PAGE - 1;
			int offset = firstRow - 1;
			int limit = endRow - firstRow + 1;
			RowBounds rowBounds = new RowBounds(offset, limit);
			birthdayList = memberInformationDao.SI_selectBirthdayMember(month, rowBounds);
			/*if(select.getVal()!=null && !select.getVal().equals("")){
				MemberTotalCount = memberInformationDao.SI_MemberSelectCount(select);
			}	검색할 때 사용할 부분*/
		} else {
			currentPageNumber = 0;
			birthdayList = Collections.emptyList();
		}
		
		BirthdayViewVO birthdayViewVO = new BirthdayViewVO(birthdayList, memberTotalCount,
				currentPageNumber, BIRTHDAY_COUNT_PER_PAGE, firstRow, endRow);
		model.addAttribute("birthdayViewVO", birthdayViewVO);
		if(birthdayViewVO.getPageTotalCount()>0){
			int beginPageNumber = (birthdayViewVO.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > birthdayViewVO.getPageTotalCount()){
				endPageNumber = birthdayViewVO.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", birthdayViewVO.getBirthdayList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
	}
	
	//부서의 번호로 회원찾기
	public List<MemberVO> SI_selectOrganizationChart(String dept_number){
		List<MemberVO> member = memberInformationDao.SI_selectOrganizationChart(dept_number);
		return member;
	}
	
	//부서번호로 이름찾기
	public String SI_selectDepartment(String dept_number){
		String dept_name = memberInformationDao.SI_selectDepartment(dept_number);
		return dept_name;
	}
	
	//직위번호로 직급이름찾기
	public String SI_selectPosition(String position_number){
		String position_name = memberInformationDao.SI_selectPosition(position_number);
		return position_name;
	}
}
