package com.nyngw.electronicApproval.approvalProgress.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MemberVO;
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
	public String waitingApproval(Model model,Principal principal,String check){
		ApprovalProgressService.defaultWA(model,principal,check);
		model.addAttribute("sideValue","sideMenu2");
		if(check==null){
			check = "";
		}
		ApprovalProgressService.defaultWA(model,principal,check);
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
	public String completeApproval(Model model, Principal principal){
		List<Electronic_ApprovalVO> EAList = ApprovalProgressService.defaultCA(model, principal);
		model.addAttribute("sideValue","sideMenu3");
		return "electronicApproval/approvalProgress/completeApproval";
	}
	
	//반려 문서 페이지
	@RequestMapping("/refusedApproval")
	public String refusedApproval(Model model, Principal principal){
		List<Electronic_ApprovalVO> EAList = ApprovalProgressService.defaultRA(model, principal);
		model.addAttribute("sideValue","sideMenu4");
		return "electronicApproval/approvalProgress/refusedApproval";
	}
	
	//미결재 문서 상세 페이지
	@RequestMapping("/waitingApprovalDetail")
	public String waitingApprovalDetail(Model model,String ea_number,Principal principal){
		int check = 1;	//미결재
		ApprovalProgressService.waDetail(model, ea_number,principal,check);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO member = commonServiceImpl.findMemberByMemId(user.getUsername());
		String userName = member.getMem_name();
		model.addAttribute("userName",userName);
		return "electronicApproval/approvalProgress/waitingApprovalDetail";
	}
	
	//완료 문서 상세 페이지
	@RequestMapping("/completeApprovalDetail")
	public String completeApprovalDetail(String checkBox, Model model,String ea_number, Principal principal){
		int check = 2;	//완료
		model.addAttribute("checkBox",checkBox);
		ApprovalProgressService.waDetail(model, ea_number,principal,check);
		return "electronicApproval/approvalProgress/completeApprovalDetail";
	}
	
	//반려 문서 상세 페이지
	@RequestMapping("/refusedApprovalDetail")
	public String refusedApprovalDetail(Model model,String ea_number, Principal principal){
		int check = 3;	//반려
		ApprovalProgressService.waDetail(model, ea_number,principal,check);
		return "electronicApproval/approvalProgress/refusedApprovalDetail";
	}
	
	//결재하기
	@RequestMapping("/conformApproval")
	public @ResponseBody Map<String,String> conformApproval(Approval_HistoryVO ahVO, 
			String mem_pwd,Principal principal){
		Map<String,String> map = new HashMap<String, String>();
		map = ApprovalProgressService.conformApproval(ahVO, mem_pwd,principal);
		return map;
	}
	
	//재기안하기 폼
	@RequestMapping("/editDraftForm")
	public String editDraftForm(Model model,String ea_number, Principal principal){
		ApprovalProgressService.editApprovalSet(model,ea_number,principal);
		return "electronicApproval/approvalProgress/editDraftForm";
	}
	
	//재기안하기
	@RequestMapping("/editDraft")
	public String editDraft(Model model,Electronic_ApprovalVO eaVO,Principal principal
			,@RequestParam(value="content") String ea_content
			,@RequestParam(value="param_ea_startdate") String startdate
			,@RequestParam(value="param_ea_enddate") String enddate){
		ApprovalProgressService.editApproval(model,eaVO,principal,ea_content,startdate,enddate);
		return "redirect:/electronicApproval/individualDocumentBox/submitApprovalBox";
	}
	
	
}
