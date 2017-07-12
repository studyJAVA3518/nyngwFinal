package com.nyngw.electronicApproval.approvalProgress.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.electronicApproval.approvalProgress.service.ApprovalProgressServiceImpl;

@Controller
@RequestMapping("/electronicApproval/approvalProgress")
public class ApprovalProgressController {

	@Autowired
	private ApprovalProgressServiceImpl ApprovalProgressService; 
	
	@Autowired
	private CommonServiceImpl commonServiceImpl;
	
	
	//미결재 문서 페이지 열기
	@RequestMapping("/waitingApproval")
	public String waitingApproval(Model model){
		List<Electronic_ApprovalVO> EAList = ApprovalProgressService.defaultWA();
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		
		model.addAttribute("EAList",EAList );
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/approvalProgress/waitingApproval";
	}

	/**
	 * 미결재 문서 검색 
	 * @param model 보내주기 위한 model
	 * @param EADateOption 검색일자
	 * @param EAStatusOption 결재상태
	 * @param EAClassificationOption 결재분류
	 * @param docSearchOption 문서검색
	 * @param searchText 입력 받는 택스트
	 * @return
	 */
	@RequestMapping("/searchWaitingApproval")
	public String searchWaitingApproval(Model model,String EADateOption, String EAStatusOption, String EAClassificationOption, String docSearchOption, String searchText){
		List<Electronic_ApprovalVO> EAList = ApprovalProgressService.searchWA(EADateOption,EAStatusOption,EAClassificationOption,docSearchOption,searchText);;
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("EAList",EAList );
		return "electronicApproval/approvalProgress/waitingApproval";
	}
	
	//결재완료문서 페이지
	@RequestMapping("/completeApproval")
	public String completeApproval(Model model){
		List<Electronic_ApprovalVO> EAList = ApprovalProgressService.defaultCA();
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("EAList",EAList );
		return "electronicApproval/approvalProgress/completeApproval";
	}
	
	//반려 문서 페이지
	@RequestMapping("/refusedApproval")
	public String refusedApproval(Model model){
		List<Electronic_ApprovalVO> EAList = ApprovalProgressService.defaultRA();
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("EAList",EAList );
		return "electronicApproval/approvalProgress/refusedApproval";
	}
	
	//미결재 문서 상세 페이지
	@RequestMapping("/waitingApprovalDetail")
	public String waitingApprovalDetail(Model model,String ea_number){
		return "electronicApproval/approvalProgress/waitingApprovalDetail";
	}
	
	//완료 문서 상세 페이지
	@RequestMapping("/completeApprovalDetail")
	public String completeApprovalDetail(Model model,String ea_number){
		return "electronicApproval/approvalProgress/completeApprovalDetail";
	}
	
	//반려 문서 상세 페이지
	@RequestMapping("/refusedApprovalDetail")
	public String refusedApprovalDetail(Model model,String ea_number){
		return "electronicApproval/approvalProgress/refusedApprovalDetail";
	}
	
	//결재하기
	@RequestMapping("/conformApproval")
	public @ResponseBody Map<String,String> conformApproval(String id){
		System.out.println(id);
		Map<String,String> map = new HashMap<String, String>();
		map.put("uri", "/electronicApproval/individualDocumentBox/completeApprovalBox");
		return map;
	}
	
	
	//결재하기
	@RequestMapping("/editDraftForm")
	public String  editDraftForm(String id){
		return "electronicApproval/approvalProgress/editDraftForm";
	}
	
	
}
