package com.nyngw.humanResource.payManagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.dto.Member_payVO;
import com.nyngw.dto.Member_payViewVO;
import com.nyngw.dto.Member_pay_PageViewVO;
import com.nyngw.environmentSetting.planPublicRelationsSetting.dao.PlanPublicRelationsSettingDao;
import com.nyngw.humanResource.payManagement.dao.PayManagementDaoImpl;

@Service
public class PayManagementServiceImpl implements PayManagementService {
	
	@Autowired
	private PayManagementDaoImpl payManagementDao;
	@Autowired
	private PlanPublicRelationsSettingDao planPublicRelationsSettingDao; 
	
	private static final int MEMBERPAYVIEW_COUNT_PER_PAGE = 5;	//한 주소록에 보여줄 주소록 수
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;	//한 페이지에 보여줄 페이지 번호 수
	
	//급여 정보를 가져오기 위해 DB에 접속하여 검색하고 model에 담아주는 메서드
	public void viewPmmList(int pageNumber, Model model, String mem_name, String dept_name, String mp_pay_date) throws SQLException{
		
		//처리에 필요한 것들 준비
		List<Member_payViewVO> memPayList = new ArrayList<Member_payViewVO>();
		int currentPageNumber = pageNumber;
		int memberTotalCount = 0;
		int firstRow = 0;
		int endRow = 0;
		
		//사원의 수(count)를 검색 
		
		if(mem_name.equals("") && dept_name.equals("") && mp_pay_date.equals("")){	//모든 회원의 수(연도x 부서x 사람x)
			memberTotalCount = payManagementDao.MP_selectAllCount();
		}else if(mem_name.equals("") && dept_name.equals("")){		//검색조건 연도o 부서x 사람x
			memberTotalCount = payManagementDao.MP_selectDateCount(mp_pay_date);
		}else if(mp_pay_date.equals("") && mem_name.equals("")){	//검색조건 연도x 부서o 사람x
			memberTotalCount = payManagementDao.MP_selectDeptCount(dept_name);
		}else if(mp_pay_date.equals("") && dept_name.equals("")){	//검색조건 연도x 부서x 사람o
			memberTotalCount = payManagementDao.MP_selectMemberCount(mem_name);
		}else if(mem_name.equals("")){								//검색조건 연도o 부서o 사람x
			memberTotalCount = payManagementDao.MP_selectDateDeptCount(mp_pay_date, dept_name);
		}else if(dept_name.equals("")){								//검색조건 연도o 부서x 사람o
			memberTotalCount = payManagementDao.MP_selectDateMemberCount(mp_pay_date, mem_name);
		}else if(mp_pay_date.equals("")){					//검색조건 연도x 부서o 사람o
			memberTotalCount = payManagementDao.MP_selectDeptMemberCount(dept_name, mem_name);
		}else{						//검색조건 모두 사용(연도o 부서o 사람o)
			memberTotalCount = payManagementDao.MP_selectAllSelectedCount(dept_name, mem_name, mp_pay_date);
		}
		
		//위에서 구한 숫자로 주소록 검색 실행
		if (memberTotalCount > 0) {	//만약 검색한 사원의 정보고 DB에 있으면
			//////페이지 번호에 따라 필요한 갯수만큼만 검색하기 위해 rowBounds를 설정해주는 곳//////
			firstRow = (pageNumber - 1) * MEMBERPAYVIEW_COUNT_PER_PAGE + 1;
			endRow = firstRow + MEMBERPAYVIEW_COUNT_PER_PAGE - 1;
			int offset = firstRow - 1;
			int limit = endRow - firstRow + 1;
			RowBounds rowBounds = new RowBounds(offset, limit);
			//////////////////////////////////////////////////////////////////
			
			memPayList = payManagementDao.MP_selectAllMember(mem_name, dept_name, mp_pay_date, rowBounds);	//rowBounds를 가지고 회원을 검색
		} else {	//만약 검색한 사원이 없으면
			currentPageNumber = 0;
			memPayList = Collections.emptyList();
		}
		
		//위에서 구한 값들을 View에 설정해준 후 model에 담아 보낸다.
		Member_pay_PageViewVO memPayPageViewVO = new Member_pay_PageViewVO(memPayList, memberTotalCount,
				currentPageNumber, MEMBERPAYVIEW_COUNT_PER_PAGE, firstRow, endRow);
		model.addAttribute("memPayPageViewVO", memPayPageViewVO);
		
		//페이지 번호별로 이전 다음 설정해주기 위해 설정
		if(memPayPageViewVO.getPageTotalCount()>0){
			int beginPageNumber = (memPayPageViewVO.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > memPayPageViewVO.getPageTotalCount()){
				endPageNumber = memPayPageViewVO.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", memPayPageViewVO.getMemberPayViewList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		
		//검색어를 넘겨주기 위한 model 셋팅
		model.addAttribute("mem_name",mem_name);
		model.addAttribute("dept_name",dept_name);
		model.addAttribute("mp_pay_date",mp_pay_date);
		
		//검색조건에서 부서정보 받아오기
		ArrayList<DepartmentViewVO> deptList = planPublicRelationsSettingDao.selectDepartmentView();
		//부서정보를 넘겨주기 위한 model 세팅
		model.addAttribute("deptList",deptList);
		
	}
	
	//급여금액 수정
	public int modifyPayMember(Model model, String mp_number, String mp_bonus, String position_number) throws SQLException {
		
		int result = payManagementDao.updateMemberPay(mp_number, mp_bonus, position_number);
		
		return result;
	}
	
	//회원번호에 의한 페이정보 하나 가져옴
	public Member_payVO getMemberPayOne(Model model, String mp_number) {
		
		return null;
	}
	
	//json형식을 부서정보리스트 가져옴
	public List<Map<String,String>> viewDeptJsonList(Model model) throws SQLException{
		List<DepartmentViewVO> deptList = planPublicRelationsSettingDao.selectDepartmentView();
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		for(DepartmentViewVO vo : deptList){
			Map<String,String> map = new HashMap<String, String>();
			map.put("dept_number", vo.getDept_number());
			map.put("dept_name", vo.getDept_name());
			resultList.add(map);
		}
		
		return resultList;
	}

	

}
