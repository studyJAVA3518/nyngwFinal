package com.nyngw.humanResource.vacationManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/humanResource/vacationManagement")
public class VacationManagementController {
	
	@RequestMapping("/vmtm")
	public String vacationMain(){
		return "/humanResource/vacationManagement/vmtm";
	}
	
}
