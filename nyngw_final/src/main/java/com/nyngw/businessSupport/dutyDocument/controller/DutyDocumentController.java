package com.nyngw.businessSupport.dutyDocument.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.businessSupport.dutyDocument.service.DutyDocumentServiceImpl;
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_ListView;

@Controller
@RequestMapping("/businessSupport/dutyDocument")
public class DutyDocumentController {
	@Autowired
	private DutyDocumentServiceImpl dutyDocumentService;
	
	@Autowired
	private CommonServiceImpl CommonService;
	
	/**
	 * 추가업무조회
	 * @return
	 */
	@RequestMapping("/additional")
	public String additionalselect(){
		
		return "businessSupport/dutyDocument/additional";
	}
	
	/**
	 * 부서업무조회?
	 * @return
	 */
	@RequestMapping("/department")
	public String departmentselect(){
		
		return "businessSupport/dutyDocument/department";
	}
	/**
	 * 부서업무 상세보기
	 * @return
	 */
	@RequestMapping("/departmentDetail")
	public String departmentDetail(){
		
		return "businessSupport/dutyDocument/departmentDetail";
	}
	
	
	/**
	 * 개인업무조회
	 * @return
	 */
	@RequestMapping("/personal")
	public String personalselect(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index, String searchDate, String reportType){ //업무종류 하나 더 스트링으로 추가
		Board_SelectVO select = new Board_SelectVO();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd"); 
		if(searchDate==null){
			System.out.println("여기들어오냐");
			searchDate = "today";
			val = "";
			reportType = "";
			searchDate = sdformat.format(new Date());
			index = searchDate;
		}
		System.out.println(reportType+"------------------->>>");
		System.out.println(searchDate+"dasdsa");
		if(searchDate.equals("today")){
			searchDate = sdformat.format(new Date());
		}else if(searchDate.equals("week")){
			cal.add(Calendar.DATE, -7);
			searchDate = sdformat.format(cal.getTime());
		}else if(searchDate.equals("month")){
			cal.add(Calendar.MONTH, -1);
			searchDate = sdformat.format(cal.getTime());
		}else if(searchDate.equals("trimester")){
			cal.add(Calendar.MONTH, -3);
			searchDate = sdformat.format(cal.getTime());
		}
		if(val==null ){
			val = "";
		}
		select.setVal(val);
		select.setSearchDate(searchDate);
		select.setReportType(reportType);
		System.out.println(val);
		System.out.println(reportType);
		System.out.println(searchDate);
//		System.out.println(select.getSearchDate()+"여기맞쥬?");
		Duty_Document_ListView viewData = (Duty_Document_ListView) dutyDocumentService.selectDocumentList(pageNumber, select);
		Common_CodeVO common = new Common_CodeVO();
		List<Duty_DocumentVO> list = viewData.getDocumentList();
		for(int i = 0; i < list.size(); i++){
			common = dutyDocumentService.documentSelectCodeName_DD(list.get(i).getDd_code_number());
			viewData.getDocumentList().get(i).setDd_code_name(common.getCode_name());
			System.out.println(";lll"+common.getCode_name());
		}
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("select",select);
		return "businessSupport/dutyDocument/personal";
	}

	@RequestMapping("/personalDetail")
	public String personalDetail(){
		
		return "businessSupport/dutyDocument/personalDetail";
	}
	
	@RequestMapping("/personalWriteForm")
	public String personalWriteForm(){
		
		return "businessSupport/dutyDocument/personalWriteForm";
	}

	@RequestMapping("/personalWrite")
	public String personalWrite(){
		
		return "businessSupport/dutyDocument/personal";
	}
	@RequestMapping("/personalUpdateForm")
	public String personalUpdateForm(){
		
		return "businessSupport/dutyDocument/personalUpdateForm";
	}
	
	@RequestMapping("/personalUpdate")
	public String personalUpdate(){
		
		return "businessSupport/dutyDocument/personal";
	}
	
	
}
