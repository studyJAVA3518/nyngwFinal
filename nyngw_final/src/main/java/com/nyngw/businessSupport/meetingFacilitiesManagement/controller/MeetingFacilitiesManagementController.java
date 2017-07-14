package com.nyngw.businessSupport.meetingFacilitiesManagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.businessSupport.meetingFacilitiesManagement.service.MeetingFacilitiesManagementServiceImpl;
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.MemberVO;

@Controller
@RequestMapping("/businessSupport/meetingFacilitiesManagement")
public class MeetingFacilitiesManagementController {
	
	@Autowired
	private MeetingFacilitiesManagementServiceImpl meetingFacilitiesManagementService;
	
	@Autowired
	private CommonServiceImpl CommonService;
	
	/**
	 * 회의실 목록
	 * @return
	 */
	@RequestMapping("/meetingRoomBooking")
	public String meetingRoomBooking(String rv_date, Model model,Principal principal){
		MemberVO member = CommonService.findMemberByMemId((principal.getName()));
		model.addAttribute("member",member);
		meetingFacilitiesManagementService.checkReservation(rv_date,model);
		return "businessSupport/meetingFacilitiesManagement/meetingRoomBooking";
	}
	
	@RequestMapping("/reservation")
	public String reservation(Principal principal, String rv_time,String rv_date, String rv_mr_number){
		MemberVO member = CommonService.findMemberByMemId(principal.getName());
		//MR = MeetingRoom = 회의실
		meetingFacilitiesManagementService.reserveMR(member.getMem_number(),rv_time,rv_date,rv_mr_number);
		return "redirect:/businessSupport/meetingFacilitiesManagement/meetingRoomBooking?rv_date="+rv_date;
	}
	
	@RequestMapping("/cancel")
	public String cancel(String rv_number,String rv_date){
		//MR = MeetingRoom = 회의실
		meetingFacilitiesManagementService.cancelMR(rv_number);
		return "redirect:/businessSupport/meetingFacilitiesManagement/meetingRoomBooking?rv_date="+rv_date;
	}
	
}
