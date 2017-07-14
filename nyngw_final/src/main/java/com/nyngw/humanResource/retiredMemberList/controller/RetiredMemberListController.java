package com.nyngw.humanResource.retiredMemberList.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> saveMember(@RequestParam(value="mem_chk[]")List<String> mem_chk){
		Map<String,Object> map = new HashMap<String,Object>();
		if(mem_chk!=null){
			for (int i = 0; i < mem_chk.size(); i++) {
				System.out.println(mem_chk.get(i));
				retireMemberListService.saveMember(mem_chk.get(i));
			}
		}
		
		return map;
	}
	
	
}
