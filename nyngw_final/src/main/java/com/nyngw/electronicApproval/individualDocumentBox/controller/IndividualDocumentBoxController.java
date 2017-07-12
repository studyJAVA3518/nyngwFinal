package com.nyngw.electronicApproval.individualDocumentBox.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.electronicApproval.individualDocumentBox.service.IndividualDocumentBoxService;

@Controller
@RequestMapping("/electronicApproval/individualDocumentBox")
public class IndividualDocumentBoxController {

	@Autowired
	private IndividualDocumentBoxService individualDocumentBoxService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@RequestMapping("/submitApprovalBox")
	public String submitApprovalBox(Model model, Principal principal){
		//로그인 한 자의 아이디
//		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultSAB(principal.getName());
		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultSAB("4");
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		model.addAttribute("EAList",EAList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/individualDocumentBox/submitApprovalBox";
	}
	
	@RequestMapping("/outbox")
	public String outbox(){
		return "electronicApproval/individualDocumentBox/outbox";
	}
	
	@RequestMapping("/completeApprovalBox")
	public String completeApprovalBox(Model model){
		//로그인 한 자의 아이디
//		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultCAB(principal.getName());
		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultCAB("4");
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		model.addAttribute("EAList",EAList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/individualDocumentBox/completeApprovalBox";
	}
	
	@RequestMapping("/refusedApprovalBox")
	public String refusedApprovalBox(Model model){
		//로그인 한 자의 아이디
//		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultRAB(principal.getName());
		List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.defaultRAB("4");
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		model.addAttribute("EAList",EAList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/individualDocumentBox/refusedApprovalBox";
	}
	
	
	//반려 문서 페이지
	@RequestMapping("/searchRefusedApproval")
	public String searchRefusedApproval(Model model){
		/*List<Electronic_ApprovalVO> EAList = individualDocumentBoxService.searchRefusedApproval();
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (Electronic_ApprovalVO EAVO : EAList) {
			code_nameList.add(commonService.findCodeNameByDocNumber(EAVO.getEa_doc_number()));
		}
		
		model.addAttribute("code_nameList",code_nameList );
		model.addAttribute("EAList",EAList );*/
		return "electronicApproval/individualDocumentBox/refusedApprovalBox";
	}
}
