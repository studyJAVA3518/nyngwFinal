package com.nyngw.humanResource.retiredMemberList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/humanResource/retiredMemberList")
public class RetiredMemberListController {
	
	@RequestMapping("/rmm")
	public String rmm(){
		return "/humanResource/retiredMemberList/rmm";
	}
}
