package com.nyngw.humanResource.vacationManagement.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.Member_Vacation_FN_GETCNTVO;
import com.nyngw.dto.Paging;
import com.nyngw.humanResource.vacationManagement.service.VacationManagementServiceImpl;

@Controller
@RequestMapping("/humanResource/vacationManagement")
public class VacationManagementController {
	
	@Autowired
	private VacationManagementServiceImpl vactionManagementService;
	
	@RequestMapping("/vmtm")
	public String vacationMain(
			@DateTimeFormat(pattern="yyyy-MM-dd")String startdate,
			@DateTimeFormat(pattern="yyyy-MM-dd")String enddate,
			Model model,String list,String search,String page){
		
		Member_Vacation_FN_GETCNTVO mvfg = new Member_Vacation_FN_GETCNTVO();
		if(startdate==null){
			Date date = new Date();
			String today=date.getYear()+1900+"-"+(date.getMonth()+1)+"-"+date.getDate();
			startdate = today;
			enddate = today;
		}
		if(search==null){
			search="";
		}
		mvfg.setChoice(list);
		mvfg.setSearch(search);
		mvfg.setDept_startdate(startdate);
		mvfg.setDept_enddate(enddate);
		
		int p=1;
		if(page != null){
			p=Integer.valueOf(page);
			if(p<1){
				p=1;
			}
		}
		Paging paging = new Paging(p, 10);
		paging.setNumberOfRecords(vactionManagementService.countTotalMember(mvfg));
		
		int firstRow = 0;
		int endRow = 0;
		
		paging.makePaging();
		
		System.out.println(paging);
				
		firstRow = (paging.getCurrentPageNo() - 1) * paging.getRecordsPerPage() + 1;
		endRow = firstRow + paging.getRecordsPerPage() - 1;
		
		mvfg.setStartPage(firstRow-1);	
		mvfg.setEndPage(endRow);
		
		List<Member_Vacation_FN_GETCNTVO> deptVacation = vactionManagementService.getDeptVaction(mvfg);
		List<Member_Vacation_FN_GETCNTVO> memberVacation = vactionManagementService.getMemberVacation(mvfg);		
		
		model.addAttribute("list", list);
		model.addAttribute("startdate", startdate);
		model.addAttribute("enddate", enddate);
		model.addAttribute("page", paging);
		model.addAttribute("memberVacation", memberVacation);
		model.addAttribute("deptVacation", deptVacation);
		model.addAttribute("sideValue", "sideMenu2");
		return "humanResource/vacationManagement/vmtm";
	}
	
}
