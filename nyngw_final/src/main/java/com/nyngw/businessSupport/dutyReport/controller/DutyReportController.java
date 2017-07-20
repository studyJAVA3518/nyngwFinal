package com.nyngw.businessSupport.dutyReport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.dutyReport.service.DutyReportServiceImpl;

@Controller
@RequestMapping("/businessSupport/dutyReport")
public class DutyReportController {
	@Autowired
	private DutyReportServiceImpl dutyReportService;
	
	@RequestMapping("/dutyReport")
	public String dutyReportselect(){
		
		return "businessSupport/dutyReport/dutyReport";
	}
	
	@RequestMapping("/dutyReportWrite")
	public String dutyReportWrite(){
		return "businessSupport/dutyReport/dutyReportWrite";
	}
	
	@RequestMapping("/getDutyReportselect")
	public String getDutyReportselect(){
		return "businessSupport/dutyReport/getDutyReportselect";
	}
}
