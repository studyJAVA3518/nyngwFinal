package com.nyngw.businessSupport.dutyReport.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.businessSupport.dutyReport.service.DutyReportServiceImpl;
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
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
	public String dutyReportWrite(){
		
		return "businessSupport/dutyReport/dutyReport";
	}
	
	@RequestMapping("/getDutyReportselect")
	public String getDutyReportselect(){
		return "businessSupport/dutyReport/getDutyReportselect";
	}
}
