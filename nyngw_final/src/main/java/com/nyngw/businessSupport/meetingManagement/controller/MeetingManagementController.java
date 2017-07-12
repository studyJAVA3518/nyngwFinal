package com.nyngw.businessSupport.meetingManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.meetingManagement.service.MeetingManagementServiceImpl;

@Controller
@RequestMapping("businessSupport/meetingManagement")
public class MeetingManagementController {
	@Autowired
	private MeetingManagementServiceImpl meetingManagementService;
	
	@RequestMapping("meetingCalendar")
	public String calendar(){
		
		return "businessSupport/meetingManagement/meetingCalendar";
	}
	@RequestMapping("meetingFile")
	public String file(){
		
		return "businessSupport/meetingManagement/meetingFile";
	}
}
