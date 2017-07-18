package com.nyngw.businessSupport.dutyDocument.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.businessSupport.dutyDocument.service.DutyDocumentServiceImpl;
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_ListView;
import com.nyngw.dto.MemberVO;

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
//	@RequestMapping("/additional")
//	public String additionalselect(){
//		
//		return "businessSupport/dutyDocument/additional";
//	}
	
	/**
	 * 부서업무조회?
	 * @return
	 */
	@RequestMapping("/department")
	public String departmentselect(String searchDate, String reportType, String titleType, String val,
									Model model, Principal principal){
		if(searchDate == null && reportType == null && titleType == null && val == null){
			searchDate = "today";
			reportType = "";
			titleType = "";
			val = "";
		}
		dutyDocumentService.departmentList(searchDate,reportType,titleType,val,model,principal);
		
		
		
		
		
		
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
				Model model,String val, String index, String searchDate, String reportType, 
					String setSearchOption, String setReportOption,Principal principal){ //업무종류 하나 더 스트링으로 추가
		System.out.println("asdfasdf");
		MemberVO member = CommonService.findMemberByMemId(principal.getName());
		System.out.println(reportType+ "-----------------------------------------------------들어와요?");
		Board_SelectVO select = new Board_SelectVO();
		select.setMem_code(member.getMem_number());
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd"); 
		if(searchDate==null){
			System.out.println("여기들어오냐");
			searchDate = "today";
			searchDate = sdformat.format(new Date());
			index = searchDate;
		}
		if(val==null){
			val="";
		}
		if(reportType==null){
			reportType="";
		}
		
		if(setSearchOption==null){
			setSearchOption = searchDate;
		}
		model.addAttribute("setSearchOption", setSearchOption);
		
		if(setReportOption==null){
			setReportOption = reportType;
		}
		model.addAttribute("setReportOption", setReportOption);
		
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
//		if(val==null ){
//			val = "";
//		}
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

	@RequestMapping("/personalDelete")
	public @ResponseBody Map<String,Object> personalDelete(@RequestParam(value="dd_chk[]")List<String> dd_chk){
		Map<String,Object> map = new HashMap<String,Object>();
		if(dd_chk!=null){
			for (int i = 0; i < dd_chk.size(); i++) {
				System.out.println(dd_chk.get(i));
				dutyDocumentService.dutyDocumentDelete_DD(dd_chk.get(i));
			}
		}
		map.put("uri", "/businessSupport/dutyDocument/personal");
		return map;
	}

	@RequestMapping("/personalDetail")
	public String personalDetail(String dd_number, String page, Model model, Principal principal,String searchDate, String reportType,String val){
		Duty_DocumentVO dutyDocument = dutyDocumentService.documentSelect_DD(dd_number);
		MemberVO member = CommonService.findMemberByMemId(principal.getName());
		dutyDocument.setMem_name(member.getMem_name());
		Common_CodeVO common = CommonService.common_selectCodeNameByDocument(dutyDocument.getDd_code_number());
		dutyDocument.setDd_name(common.getCode_name());
		if(reportType==null){
			reportType = "";
		}
		model.addAttribute("dutyDocument", dutyDocument);
		model.addAttribute("page", page);
		model.addAttribute("reportType", reportType);
		model.addAttribute("searchDate", searchDate);
		model.addAttribute("val",val);
		System.out.println(searchDate+"222222222222222222");
		System.out.println(reportType+"33333333333333");
		return "businessSupport/dutyDocument/personalDetail";
	}
	
	
	@RequestMapping("/personalWriteForm")
	public String personalWriteForm(Model model){
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd"); 
		String writeDate = sdformat.format(new Date());
		model.addAttribute("writeDate", writeDate);
		return "businessSupport/dutyDocument/personalWriteForm";
	}

	@RequestMapping("/personalWrite")
	public String personalWrite(Duty_DocumentVO dutyDocument, Principal principal){
		MemberVO member = CommonService.findMemberByMemId(principal.getName());
		dutyDocument.setDd_mem_number(member.getMem_number());
		dutyDocumentService.dutyDocumentInsert_DD(dutyDocument);
		return "redirect:/businessSupport/dutyDocument/personal";
	}
	@RequestMapping("/personalUpdateForm")
	public String personalUpdateForm(String dd_number, Model model){
		Duty_DocumentVO dutyDocument = dutyDocumentService.documentSelect_DD(dd_number);		
		Common_CodeVO common = CommonService.common_selectCodeNameByDocument(dutyDocument.getDd_code_number());
		dutyDocument.setDd_name(common.getCode_name());
		System.out.println(dutyDocument.getDd_code_number());
		model.addAttribute("dutyDocument", dutyDocument);
		return "businessSupport/dutyDocument/personalUpdateForm";
	}
	
	@RequestMapping("/personalUpdate")
	public String personalUpdate(String reportType, String dd_title, String dd_public, String dd_content, Principal principal, String dd_number){
		MemberVO member = CommonService.findMemberByMemId(principal.getName());
		Duty_DocumentVO dutyDocument = new Duty_DocumentVO();
		Date dt = new Date();
		String yAndN = "";
		if(dd_public==null){
			yAndN = "n";
		}else{
			yAndN = "y";
		}
		dutyDocument.setDd_number(dd_number);
		dutyDocument.setDd_title(dd_title);
		dutyDocument.setDd_content(dd_content);
		dutyDocument.setDd_mem_number(member.getMem_number());
		dutyDocument.setDd_code_number(reportType);
		dutyDocument.setDd_date(dt);
		dutyDocument.setDd_public(yAndN);
		dutyDocumentService.dutyDocumentUpdate_DD(dutyDocument);
		return "redirect:/businessSupport/dutyDocument/personal";
	}
	
	
}
