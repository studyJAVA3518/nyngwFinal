package com.nyngw.businessSupport.dutyDocument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.dutyDocument.service.DutyDocumentServiceImpl;

@Controller
@RequestMapping("/businessSupport/dutyDocument")
public class DutyDocumentController {
	@Autowired
	private DutyDocumentServiceImpl dutyDocumentService;
	
	@RequestMapping("/additional")
	public String additionalselect(){
		
		return "businessSupport/dutyDocument/additional";
	}
	@RequestMapping("/department")
	public String departmentselect(){
		
		return "businessSupport/dutyDocument/department";
	}
	@RequestMapping("/personal")
	public String personalselect(){
		
		return "businessSupport/dutyDocument/personal";
	}
	
	
}
