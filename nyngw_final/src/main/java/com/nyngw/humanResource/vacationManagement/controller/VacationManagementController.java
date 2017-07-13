package com.nyngw.humanResource.vacationManagement.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.Member_Vacation_FN_GETCNTVO;
import com.nyngw.humanResource.vacationManagement.service.VacationManagementServiceImpl;

@Controller
@RequestMapping("/humanResource/vacationManagement")
public class VacationManagementController {
	
	@Autowired
	private VacationManagementServiceImpl vactionManagementService;
	
	@RequestMapping("/vmtm")
	public String vacationMain(@DateTimeFormat(pattern="yyyy-MM-dd")String startdate,@DateTimeFormat(pattern="yyyy-MM-dd")String enddate,Model model,String list,String search){
		
		Member_Vacation_FN_GETCNTVO mvfg = new Member_Vacation_FN_GETCNTVO();
		if(startdate==null){
			Date date = new Date();
			String today=date.getYear()+1900+"-"+(date.getMonth()+1)+"-"+date.getDate();
			startdate = today;
			enddate = today;
		}
		
		mvfg.setChoice(list);
		mvfg.setSearch(search);
		mvfg.setDept_startdate(startdate);
		mvfg.setDept_enddate(enddate);
		
		List<Member_Vacation_FN_GETCNTVO> deptVacation = vactionManagementService.getDeptVaction(mvfg);
		List<Member_Vacation_FN_GETCNTVO> memberVacation = vactionManagementService.getMemberVacation(mvfg);		
		
		model.addAttribute("memberVacation", memberVacation);
		model.addAttribute("deptVacation", deptVacation);
		
		return "humanResource/vacationManagement/vmtm";
	}
	
}
