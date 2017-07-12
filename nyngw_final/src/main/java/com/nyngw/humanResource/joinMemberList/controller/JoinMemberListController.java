package com.nyngw.humanResource.joinMemberList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.humanResource.joinMemberList.service.JoinMemberListServiceImpl;

@Controller
@RequestMapping("/humanResource/joinMemberList")
public class JoinMemberListController {
	
	@Autowired
	private JoinMemberListServiceImpl joinMemberListService;
	
	
	@RequestMapping("/jlm")
	public String joinMain(Model model){
		
		List<JoinMemberVO> joinMemberList = joinMemberListService.getJoinMemberList();
		
		model.addAttribute("joinMemberList", joinMemberList);
		return "humanResource/joinMemberList/jlm";
	}
	
}
