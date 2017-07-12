package com.nyngw.humanResource.joinMemberList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/humanResource/joinMemberList")
public class JoinMemberListController {
	
	@RequestMapping("/jlm")
	public String joinMain(){
		return "/humanResource/joinMemberList/jlm";
	}

}
