package com.nyngw.businessSupport.dutyStatusReport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.dutyStatusReport.service.DutyStatusReportServiceImpl;

@Controller
@RequestMapping("businessSupport/dutyStatusReport")
public class DutyStatusReportController {
	
	@Autowired
	private DutyStatusReportServiceImpl dutyStatusReportService;
	
	
	@RequestMapping("completeDuty")
	public String complete(){
		
		return "businessSupport/dutyStatusReport/completeDuty";
	}
	@RequestMapping("incompleteDuty")
	public String incomplete(){
		
		return "businessSupport/dutyStatusReport/incompleteDuty";
	}
}
