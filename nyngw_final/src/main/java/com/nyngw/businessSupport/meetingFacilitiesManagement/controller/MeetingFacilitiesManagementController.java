package com.nyngw.businessSupport.meetingFacilitiesManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.meetingFacilitiesManagement.service.MeetingFacilitiesManagementServiceImpl;

@Controller
@RequestMapping("businessSupport/meetingFacilitiesManagement")
public class MeetingFacilitiesManagementController {
	
	@Autowired
	private MeetingFacilitiesManagementServiceImpl meetingFacilitiesManagementService;
	
	/**
	 * 회의실 목록
	 * @return
	 */
	@RequestMapping("facilitiesBooking")
	public String Booking(){
		
		return "businessSupport/meetingFacilitiesManagement/facilitiesBooking";
	}
	
	@RequestMapping("reservation")
	public String reservation(){
		return "businessSupport/meetingFacilitiesManagement/reservation";
	}
}
