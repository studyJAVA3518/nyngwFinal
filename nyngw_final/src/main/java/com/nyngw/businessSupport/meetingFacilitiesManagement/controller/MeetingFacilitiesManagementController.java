package com.nyngw.businessSupport.meetingFacilitiesManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.meetingFacilitiesManagement.service.MeetingFacilitiesManagementServiceImpl;

@Controller
@RequestMapping("/businessSupport/meetingFacilitiesManagement")
public class MeetingFacilitiesManagementController {
	
	@Autowired
	private MeetingFacilitiesManagementServiceImpl meetingFacilitiesManagementService;
	
	/**
	 * 회의실 목록
	 * @return
	 */
	@RequestMapping("/meetingRoomBooking")
	public String meetingRoomBooking(String rv_date, Model model){
		meetingFacilitiesManagementService.checkReservation(rv_date,model);
		return "businessSupport/meetingFacilitiesManagement/meetingRoomBooking";
	}
	
	@RequestMapping("/reservation")
	public String reservation(){
		return "businessSupport/meetingFacilitiesManagement/reservation";
	}
	
	@RequestMapping("/searchReservation")
	public String searchReservation(){
		
		return "businessSupport/meetingFacilitiesManagement/reservation";
	}
}
