package com.nyngw.electronicApproval.approvalLineManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/electronicApproval/approvalLineManagement")
public class ApprovalLineManagementController {
	
	@RequestMapping("/approvalLineManagement")
	public String ApprovalLineManagement(){
		return "electronicApproval/approvalLineManagement/approvalLineManagement";
	}
}
