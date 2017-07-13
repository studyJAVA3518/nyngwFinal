package com.nyngw.humanResource.memberJoin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.Member_Detail_InformationVO;
import com.nyngw.humanResource.memberJoin.service.MemberJoinServiceImpl;

@Controller
@RequestMapping("/humanResource/memberJoin")
public class MemberJoinController {
	
	@Autowired
	private MemberJoinServiceImpl memberJoinService;
	
	@RequestMapping("/mjm")
	public String mjm(){
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
	public @ResponseBody Map<String,Object> joinMember(JoinMemberVO joinMember){
		Map<String,Object> map = new HashMap<String, Object>();
			
		System.out.println(joinMember);
		
		
		return map;
	}
	
	
}
