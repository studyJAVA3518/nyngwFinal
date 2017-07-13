package com.nyngw.humanResource.memberJoin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.humanResource.memberJoin.service.MemberJoinServiceImpl;

@Controller
@RequestMapping("/humanResource/memberJoin")
public class MemberJoinController {
	
	@Autowired
	private MemberJoinServiceImpl memberJoinService;
	
	@RequestMapping("/mjm")
	public String mjm(Model model){
		
		
		
		return "humanResource/memberJoin/mjm";
	}
	
	@RequestMapping("/idCheck")
	public @ResponseBody Map<String,Object> insertMember(String id){
		Map<String,Object> map = new HashMap<String, Object>();
		
		MemberVO member = memberJoinService.idCheck(id);
		
		map.put("status", "no");
		
		if(member==null){
			map.put("status", "ok");
		}
		
		map.put("id", id);
		
		return map;
	}
	
	@RequestMapping("/joinMember")
	public @ResponseBody Map<String,Object> joinMember(JoinMemberVO joinMember,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		/*
		try {
			joinMember.setMem_birthday(new SimpleDateFormat("yyyy-MM-dd").parse(joinMember.getMem_reg()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
		System.out.println(joinMember);
		
		model.addAttribute("status", "no");
		int result = memberJoinService.joinMember(joinMember);
		
		return map;
	}
	
	
}
