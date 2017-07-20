package com.nyngw.businessSupport.meetingManagement.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.businessSupport.meetingManagement.service.MeetingManagementServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.Meeting_DocumentVO;
import com.nyngw.dto.Meeting_Document_ListViewVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;

@Controller
@RequestMapping("businessSupport/meetingManagement")
public class MeetingManagementController {
	@Autowired
	private MeetingManagementServiceImpl meetingManagementService;
	
	@Autowired
	private BasicSettingServiceImpl basicSettingService;
	
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
	
	@RequestMapping("meetingCalendar")
	public String calendar(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index, String page, Principal principal){
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		MemberVO mem = basicSettingService.selectMember(principal.getName());
		String mem_number = mem.getMem_number();
		select.setMem_number(mem_number);
		MeetingListViewVO viewData =(MeetingListViewVO)meetingManagementService.selectMeetingList(pageNumber, select);
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		if(viewData.getPageTotalCount()>0){
			int beginPageNumber = (viewData.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > viewData.getPageTotalCount()){
				endPageNumber = viewData.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", viewData.getMeetingList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		
		model.addAttribute("page",page);
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
	public String addMeet(Model model, 
			String mt_title, 
			String mt_date,
			String mt_reader,
			String mt_mr_number,
			@RequestParam( value="content") String mt_content
			){
		MeetingVO meeting = new MeetingVO();
		
		meeting.setMt_title(mt_title);
		meeting.setMt_reader(mt_reader);
		meeting.setMt_content(mt_content);
		meeting.setMt_mr_number(mt_mr_number);
		try {
			meeting.setMt_date(new SimpleDateFormat("yyyy-MM-dd").parse(mt_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		meetingManagementService.meetingInsert(meeting);
		System.out.println("컨트롤러 : "+meeting.getMt_content());
		System.out.println("컨트롤러 : "+meeting.getMt_number());
		System.out.println("컨트롤러 : "+meeting.getMt_reader());
		System.out.println("컨트롤러 : "+meeting.getMt_title());
		System.out.println("컨트롤러 : "+meeting.getMt_date());
		return "redirect:/businessSupport/meetingManagement/meetingCalendar";
	}
	
	
	@RequestMapping("/meetingDetail")
	public String meetingDetail(String mt_number, Model model, String page){
		System.out.println("넘어오니 ?");
		MeetingVO meeting = meetingManagementService.selectMeetingNumber(mt_number);
		List<MeetingRoomVO> meetingroom = meetingManagementService.selectMeetingRoom();
		
		model.addAttribute("meetingroom",meetingroom);
		model.addAttribute("meeting", meeting);
		model.addAttribute("page", page);
		return "businessSupport/meetingManagement/meetingDetail";
	}
	
	@RequestMapping("/meetingUpdateForm")
	public String meetingUpdateForm(String mt_number, Model model, String page,String param_mt_date){
		MeetingVO meeting = meetingManagementService.selectMeetingNumber(mt_number);
		List<MeetingRoomVO> meetingroom = meetingManagementService.selectMeetingRoom();
		System.out.println("업데이트폼 들어와?");
		model.addAttribute("meetingroom",meetingroom);
		model.addAttribute("meeting", meeting);
		model.addAttribute("page",page);
		model.addAttribute("param_mt_date",param_mt_date);
		
		return "businessSupport/meetingManagement/meetingUpdateForm";
	}
	
	@RequestMapping("/meetingUpdate")
	public String meetingUpdate(MeetingVO meeting,@RequestParam(value="content") String mt_content,String param_mt_date){
		meeting.setMt_content(mt_content);
		try {
			meeting.setMt_date(new SimpleDateFormat("yyyy-MM-dd").parse(param_mt_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		meetingManagementService.updateMeeting(meeting);
		return "redirect:/businessSupport/meetingManagement/meetingCalendar";
	}
	
	@RequestMapping("/meetingDelete")
	public @ResponseBody Map<String,String> meetingDelete(String mt_number){
		System.out.println(mt_number+"오니?");
		meetingManagementService.meetingDelete(mt_number);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/businessSupport/meetingManagement/meetingCalendar");
		return resultMap;
	}
	

	
	
//	--------------------------------회의록 -----------------------------------------
	
	
	@RequestMapping("/meetingFile")
	public String meetingfileselect(@RequestParam(value="page",defaultValue="1")int pageNumber,
				Model model,String val, String index, String searchDate, String titleType,
					String setSearchOption, String setTitleOption,Principal principal, String page){
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		Meeting_Document_ListViewVO viewData =meetingManagementService.meeting_DocumentList(pageNumber, select);
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		if(viewData.getPageTotalCount()>0){
			int beginPageNumber = (viewData.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > viewData.getPageTotalCount()){
				endPageNumber = viewData.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", viewData.getMeeting_DocumentList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		
		model.addAttribute("page",page);
		
		return "businessSupport/meetingManagement/meetingFile";
	}
	
}
