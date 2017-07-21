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
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.AttendanceVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.Meeting_DocumentVO;
import com.nyngw.dto.Meeting_Document_ListViewVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.draft.service.DraftServiceImpl;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;

@Controller
@RequestMapping("businessSupport/meetingManagement")
public class MeetingManagementController {
	@Autowired
	private MeetingManagementServiceImpl meetingManagementService;
	
	@Autowired
	private DraftServiceImpl draftService;
	
	@Autowired
	private BasicSettingServiceImpl basicSettingService;
	
	@Autowired
	private CommonServiceImpl CommonService;
	
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
			@RequestParam( value="content") String mt_content,
			String mt_members1,String mt_members2,String mt_members3,String mt_members4){
		
		MeetingVO meeting = new MeetingVO();
		String mt_members ="";
		
		if(mt_members1!=null){
			MemberVO mem1 = CommonService.findMemberByMemNumber(mt_members1);
			String mem1name = mem1.getMem_name();
			mt_members += mem1name;
		}
		if(mt_members2!=null){
			MemberVO mem2 = CommonService.findMemberByMemNumber(mt_members2);
			String mem2name = mem2.getMem_name();
			mt_members += mem2name;
		}
		if(mt_members3!=null){
			MemberVO mem3 = CommonService.findMemberByMemNumber(mt_members3);
			String mem3name = mem3.getMem_name();
			mt_members += mem3name;
		}
		if(mt_members4!=null){
			MemberVO mem4 = CommonService.findMemberByMemNumber(mt_members4);
			String mem4name = mem4.getMem_name();
			mt_members += mem4name;
		}
		
		meeting.setMt_members(mt_members);
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
		
		List<MeetingVO> met = meetingManagementService.meetingSelet(mt_reader);
		
		MeetingVO met1 = met.get(met.size()-1);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = user.getUsername(); 
		MemberVO mem = basicSettingService.selectMember(mem_id);
		AttendanceVO attend = new AttendanceVO();
		
		attend.setAd_mem_number(mem.getMem_number());
		attend.setAd_mt_number(met1.getMt_number());
		meetingManagementService.attendInsert(attend);
		return "redirect:/businessSupport/meetingManagement/meetingCalendar";
	}
	
	
	@RequestMapping("/meetingDetail")
	public String meetingDetail(String mt_number, Model model, String page){
		System.out.println(mt_number);
		MeetingVO meeting = meetingManagementService.selectMeetingNumber(mt_number);
		
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
	public String meetingUpdate(MeetingVO meeting,
			@RequestParam(value="content") String mt_content,
			String param_mt_date
			){
		
		
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
	

	@RequestMapping("/approvalLineManager")
	@ResponseBody
	public Map<String, String> approvalLineManager(Model model){
		String sb = draftService.getMenuDepartmentString().toString();
		Map<String,String> jsonDataMap = new HashMap<String, String>();
		jsonDataMap.put("sb", sb);
		return jsonDataMap;
	}
	@RequestMapping("/findMemberByDepartment")
	@ResponseBody
	public List<Map> findMemberByDepartment(String dept_number,Model model){
		List<Map> memberJsonData = draftService.findMemberByDepartment(dept_number,model);
		return memberJsonData;
	}
	
	@RequestMapping("/searchMember")
	@ResponseBody
	public List<Map> searchMember(Model model,String searchText, String dept_number){
		List<Map> memberJsonData = draftService.searchMemberByMemberName(searchText,dept_number);
		return memberJsonData;
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
		MemberVO mem = basicSettingService.selectMember(principal.getName());
		String mem_number = mem.getMem_number();
		select.setMem_id(principal.getName());
		select.setMem_number(mem_number);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		Meeting_Document_ListViewVO viewData =meetingManagementService.meeting_DocumentList(pageNumber, select);
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
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
	@RequestMapping("addMeetingFile")
	public String addMeetFileForm(Model model,String val, String index){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = user.getUsername();
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		MemberVO meetingList3 = basicSettingService.selectMember(mem_id);
		List<MeetingVO> metlist = meetingManagementService.selectMeetingAll();
		System.out.println("==========================으아니"+metlist.get(0).getMt_number());
		boolean a =false;
		if(metlist.size()>0){
			a =true;
		}
		
		model.addAttribute("a",a);
		model.addAttribute("metlist",metlist);
		model.addAttribute("meetingList3",meetingList3);
		return "businessSupport/meetingManagement/addMeetingFile";
	}
	
	@RequestMapping(value="/meetingFileInsert", method=RequestMethod.POST)
	public String addMeet1(Model model, 
			String md_name, 
			String md_date,
			String md_writer,
			String md_number,
			@RequestParam( value="content") String md_content){
		Meeting_DocumentVO meetingFile = new Meeting_DocumentVO();
		
		meetingFile.setMd_name(md_name);
		meetingFile.setMd_writer(md_writer);
		meetingFile.setMd_content(md_content);
		meetingFile.setMd_number(md_number);
		System.out.println(md_writer);
		try {
			meetingFile.setMd_date(new SimpleDateFormat("yyyy-MM-dd").parse(md_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		meetingManagementService.meetingFileInsert(meetingFile);
		return "redirect:/businessSupport/meetingManagement/meetingFile";
	}
	
	@RequestMapping("/meetingFileDetail")
	public String meetingFileDetail(String md_number, Model model, String page){
		System.out.println("넘어오니 ?");
		System.out.println(md_number);
		Meeting_DocumentVO meetingFile = meetingManagementService.selectMeetingFileNumber(md_number);
		model.addAttribute("meetingFile", meetingFile);
		model.addAttribute("page", page);
		System.out.println(meetingFile.getMd_name());
		return "businessSupport/meetingManagement/meetingFileDetail";
	}
	
	@RequestMapping("/meetingFileUpdateForm")
	public String meetingFileUpdateForm(String md_number, Model model, String page,String param_md_date){
		Meeting_DocumentVO meetingFile = meetingManagementService.selectMeetingFileNumber(md_number);
		System.out.println(param_md_date);
		model.addAttribute("meetingFile", meetingFile);
		model.addAttribute("page",page);
		model.addAttribute("param_mt_date",param_md_date);
		
		return "businessSupport/meetingManagement/meetingFileUpdateForm";
	}
}
