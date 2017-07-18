package com.nyngw.businessSupport.meetingManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.businessSupport.meetingManagement.service.MeetingManagementServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;

@Controller
@RequestMapping("businessSupport/meetingManagement")
public class MeetingManagementController {
	@Autowired
	private MeetingManagementServiceImpl meetingManagementService;
	@Autowired
	private BasicSettingServiceImpl basicSettingService;
	
	@RequestMapping("meetingCalendar")
	public String calendar(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index){
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		MeetingListViewVO viewData =(MeetingListViewVO)meetingManagementService.selectMeetingList(pageNumber, select);
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		
		return "businessSupport/meetingManagement/meetingCalendar";
	}
	
	
	@RequestMapping("addMeetingForm")
	public String addMeetForm(Model model){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = user.getUsername(); 
		MemberVO meetingList2 = basicSettingService.selectMember(mem_id);
		List<MeetingRoomVO> meetingroom = meetingManagementService.selectMeetingRoom();
		
		model.addAttribute("meetingroom",meetingroom);
		model.addAttribute("meetingList2",meetingList2);
		return "businessSupport/meetingManagement/addMeeting";
	}
	 
	@RequestMapping(value="/meetingInsert", method=RequestMethod.POST)
	public String addMeet(Model model, MeetingVO meeting){
		meetingManagementService.meetingInsert(meeting);
		return "redirect:businessSupport/meetingManagement/meetingCalendar";
	}
	
	
	@RequestMapping("meetingFile")
	public String file(){
		
		return "businessSupport/meetingManagement/meetingFile";
	}
}
