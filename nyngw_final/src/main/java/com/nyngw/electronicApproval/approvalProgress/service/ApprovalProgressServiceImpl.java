package com.nyngw.electronicApproval.approvalProgress.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;

@Service
public class ApprovalProgressServiceImpl implements ApprovalProgressService {

	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;

	@Autowired
	private CommonServiceImpl commonServiceImpl;
	
	//모든 미결재문서를 가져옴
	public void defaultWA(Model model,Principal principal) {
		//접속자의 정보 검색
		String mem_id = principal.getName();
		MemberVO member = commonServiceImpl.findMemberByMemId(mem_id);
		//접속자의 사원번호로 결재라인에 올라가있는 결재번호 검색
		List<String> ea_numberList = approvalProgressDao.ap_selectEaNumberByMemId(member.getMem_number());
		
		//return할 결재정보 리스트
		List<Electronic_ApprovalVO> eaList = new ArrayList<Electronic_ApprovalVO>();
		List<String> statusList = new ArrayList<String>();

		//결재별 현황 검색
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
				statusList.add("상신");
			}else if(lastAhHistory==lastAstPriority+1){
				Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
				eaList.add(eaVO);
				statusList.add("전결");
			}else{
				Electronic_ApprovalVO eaVO = approvalProgressDao.selectEA(ea_number);
				eaList.add(eaVO);
				statusList.add("전결");
			}
			
		}
		model.addAttribute("eaList",eaList);
		model.addAttribute("statusList", statusList);
		
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		for (Electronic_ApprovalVO eaVO : eaList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(eaVO.getEa_doc_number()));
			memberList.add(commonServiceImpl.findMemberByMemNumber(eaVO.getEa_mem_number()));
		}
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("memberList",memberList );
		
	}
	
	//WA = waiting Approval
	//미결재 문서 검색  -> 구현 필요
	public List<Electronic_ApprovalVO> searchWA(String eADateOption,
			String eAStatusOption, String eAClassificationOption,
			String docSearchOption, String searchText) {
		return null;
	}

	//모든 완료문서를 가져옴
	public List<Electronic_ApprovalVO> defaultCA() {
		return approvalProgressDao.selectCA();
	}

	public List<Electronic_ApprovalVO> defaultRA() {
		return approvalProgressDao.selectRA();
	}


}
