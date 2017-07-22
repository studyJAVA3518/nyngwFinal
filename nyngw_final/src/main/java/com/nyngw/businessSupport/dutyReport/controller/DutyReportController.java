package com.nyngw.businessSupport.dutyReport.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.businessSupport.dutyReport.service.DutyReportServiceImpl;
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;

@Controller
@RequestMapping("/businessSupport/dutyReport")
public class DutyReportController {
	@Autowired
	private DutyReportServiceImpl dutyReportService;
	@Autowired
	private CommonServiceImpl commonService;
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
	@Autowired
	private BasicSettingServiceImpl basicSettingService; 
	
	@RequestMapping("/dutyReport")
	public String dutyReportselect(@RequestParam(value="page",defaultValue="1")int pageNumber,
			String searchDate, String reportType, String titleType, String val, Model model, Principal principal){
		if(searchDate == null && reportType == null && titleType == null && val == null){
			searchDate = "today";
			reportType = "";
			titleType = "";
			val = "";
		}
		MemberVO mem = basicSettingService.selectMember(principal.getName());
		model.addAttribute("mem_name",mem.getMem_name());
		dutyReportService.dutyReportList(searchDate,reportType,titleType,val,model,principal,pageNumber);
		return "businessSupport/dutyReport/dutyReport";
	}
	
	@RequestMapping("/dutyReportWriteForm")
	public String dutyReportWriteForm(Model model){
		List<Common_CodeVO> codeList = dutyReportService.dutyReportCodeSelect();
		model.addAttribute("dr_codeList",codeList);
		return "businessSupport/dutyReport/dutyReportWrite";
	}
	
	@RequestMapping("/dutyReportWrite")
	public String dutyReportWrite(Duty_ReportVO dutyReportVO, Principal principal,String dr_date1
			, @RequestParam( value="content") String dr_content){
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		try {
			dutyReportVO.setDr_date(new SimpleDateFormat("yyyy-MM-dd").parse(dr_date1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dutyReportVO.setDr_content(dr_content);
		dutyReportVO.setDr_mem_number(member.getMem_number());
		dutyReportService.dutyReportWrite(dutyReportVO);
		return "redirect:/businessSupport/dutyReport/dutyReport";
	}
	@RequestMapping("/dutyReportDelete")
	public @ResponseBody Map<String, Object> dutyReportDelete(@RequestParam(value="dr_chk[]")List<String> dr_chk){
		Map<String,Object> map = new HashMap<String,Object>();
		if(dr_chk!=null){
			for(int i=0; i<dr_chk.size(); i++){
				dutyReportService.dutyReportDelete(dr_chk.get(i));
			}
		}
		map.put("uri", "/businessSupport/dutyReport/dutyReport");
		return map;
	}
	@RequestMapping("/dutyReportDetail")
	public String dutyReportDetail(@RequestParam(value="page",defaultValue="1")int pageNumber,
			String searchDate, String reportType, String titleType, String val, Model model, Principal principal, String dr_number){
		
		dutyReportService.dutyReportDetail(pageNumber, searchDate, reportType, titleType, val, model, principal, dr_number);
		
		return "businessSupport/dutyReport/dutyReportDetail";
	}
	
	//----------------------------------------------------
	@RequestMapping("/getDutyReportselect")
	public String getDutyReportselect(@RequestParam(value="page",defaultValue="1")int pageNumber,
			String searchDate, String reportType, String titleType, String val, Model model, Principal principal){
		if(searchDate == null && reportType == null && titleType == null && val == null){
			searchDate = "today";
			reportType = "";
			titleType = "";
			val = "";
		}
		dutyReportService.getDutyReportselect(searchDate,reportType,titleType,val,model,principal,pageNumber);
		return "businessSupport/dutyReport/getDutyReportselect";
	}
	
	@RequestMapping("/dutyReportCommentWrite")
	public @ResponseBody Map<String,String> dutyReportCommentWrite(String drc_content, String id, Principal principal){
		System.out.println(drc_content);
		System.out.println(id);
		System.out.println(principal.getName());
		dutyReportService.dutyReportCommentWrite(drc_content, id, principal);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/businessSupport/dutyReport/dutyReportDetail?dr_number="+id);
		return resultMap;
	}
	
	@RequestMapping("/dutyReportCommentDelete")
	public @ResponseBody Map<String,String> dutyReportCommentDelete(String id, String dr_number){
		dutyReportService.dutyReportCommentDelete(id);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/businessSupport/dutyReport/dutyReportDetail?dr_number="+dr_number);
		return resultMap;
	}
}
