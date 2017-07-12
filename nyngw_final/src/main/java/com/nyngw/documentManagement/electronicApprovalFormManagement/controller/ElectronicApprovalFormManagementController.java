package com.nyngw.documentManagement.electronicApprovalFormManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("documentManagement/electronicApprovalFormManagement")
public class ElectronicApprovalFormManagementController {
	
	@RequestMapping("/eaFormSelect")
	public String eaFormSelect(){
		return "documentManagement/electronicApprovalFormManagement/eaFormSelect";
	}
	
	@RequestMapping("/eaFormInsert")
	public String eaFormInsert(){
		return "documentManagement/electronicApprovalFormManagement/eaFormInsert";
	}
}
