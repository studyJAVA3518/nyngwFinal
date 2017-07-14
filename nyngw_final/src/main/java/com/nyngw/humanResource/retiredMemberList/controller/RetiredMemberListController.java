package com.nyngw.humanResource.retiredMemberList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.humanResource.retiredMemberList.service.RetiredMemberListServiceImpl;

@Controller
@RequestMapping("/humanResource/retiredMemberList")
public class RetiredMemberListController {
	
	@Autowired
	private RetiredMemberListServiceImpl retireMemberListService;
	
	@RequestMapping("/rmm")
	public String rmm(Model model,JoinMemberVO member){
		
		if(member.getMem_dept_number()!=null){
			if(member.getMem_dept_number().equals("all")){
				member.setMem_dept_number(null);
			}
		}
		
		List<JoinMemberVO> memberList = retireMemberListService.getRetiredMemberList(member);
		
		model.addAttribute("memberList", memberList);		
		
		return "humanResource/retiredMemberList/rmm";
	}
	
	
}
