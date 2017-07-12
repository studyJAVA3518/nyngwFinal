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
	
	/**
	 * 추가업무조회
	 * @return
	 */
	@RequestMapping("/additional")
	public String additionalselect(){
		
		return "businessSupport/dutyDocument/additional";
	}
	
	/**
	 * 부서업무조회?
	 * @return
	 */
	@RequestMapping("/department")
	public String departmentselect(){
		
		return "businessSupport/dutyDocument/department";
	}
	/**
	 * 부서업무 상세보기
	 * @return
	 */
	@RequestMapping("/departmentDetail")
	public String departmentDetail(){
		
		return "businessSupport/dutyDocument/departmentDetail";
	}
	/**
	 * 개인업무조회
	 * @return
	 */
	@RequestMapping("/personal")
	public String personalselect(){
		
		return "businessSupport/dutyDocument/personal";
	}

	@RequestMapping("/personalDetail")
	public String personalDetail(){
		
		return "businessSupport/dutyDocument/personalDetail";
	}
	
	@RequestMapping("/personalWriteForm")
	public String personalWriteForm(){
		
		return "businessSupport/dutyDocument/personalWriteForm";
	}

	@RequestMapping("/personalWrite")
	public String personalWrite(){
		
		return "businessSupport/dutyDocument/personal";
	}
	@RequestMapping("/personalUpdateForm")
	public String personalUpdateForm(){
		
		return "businessSupport/dutyDocument/personalUpdateForm";
	}
	
	@RequestMapping("/personalUpdate")
	public String personalUpdate(){
		
		return "businessSupport/dutyDocument/personal";
	}
	
	
}
