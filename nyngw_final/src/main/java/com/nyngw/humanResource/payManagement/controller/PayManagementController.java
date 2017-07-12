package com.nyngw.humanResource.payManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/humanResource/payManagement")
public class PayManagementController {
	
	@RequestMapping("/pmm")
	public String pmm(){
		return "/humanResource/payManagement/pmm";
	}
	
}
