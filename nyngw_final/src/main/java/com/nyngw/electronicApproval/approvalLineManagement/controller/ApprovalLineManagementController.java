package com.nyngw.electronicApproval.approvalLineManagement.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.approvalLineManagement.service.ApprovalLineManagementServiceImpl;

@Controller
@RequestMapping("/electronicApproval/approvalLineManagement")
public class ApprovalLineManagementController {
	
	@Autowired
	private ApprovalLineManagementServiceImpl approvalLineManagementService;

	@RequestMapping("/approvalLineManagement")
	public String ApprovalLineManagement(Model model){
		String sb = approvalLineManagementService.getMenuDocumentString().toString();
		model.addAttribute("sb",sb);
		return "electronicApproval/approvalLineManagement/approvalLineManagement";
	}
	
	
	@RequestMapping("/searchMember")
	public String searchMember(Model model,String searchText){
//		List<MemberVO> memberList = approvalLineManagementService.searchMember(searchText);
//		model.addAttribute("memberList",memberList);
		return "electronicApproval/approvalLineManagement/approvalLineManagement";
	}
	
	
}
