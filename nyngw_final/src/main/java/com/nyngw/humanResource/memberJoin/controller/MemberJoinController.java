package com.nyngw.humanResource.memberJoin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/humanResource/memberJoin")
public class MemberJoinController {
	
	@RequestMapping("/mjm")
	public String mjm(){
		return "/humanResource/memberJoin/mjm";
	}
	
}
