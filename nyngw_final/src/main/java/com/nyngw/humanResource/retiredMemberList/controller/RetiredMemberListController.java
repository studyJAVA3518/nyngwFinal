package com.nyngw.humanResource.retiredMemberList.controller;

import java.sql.SQLException;
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
import com.nyngw.dto.Paging;
import com.nyngw.humanResource.memberJoin.service.MemberJoinServiceImpl;
import com.nyngw.humanResource.retiredMemberList.service.RetiredMemberListServiceImpl;

@Controller
@RequestMapping("/humanResource/retiredMemberList")
public class RetiredMemberListController {
	
	@Autowired
	private RetiredMemberListServiceImpl retireMemberListService;
	@Autowired
	private MemberJoinServiceImpl memberJoinService;
	
	@RequestMapping("/rmm")
	public String rmm(Model model,JoinMemberVO member,String page){
		
		try {
			memberJoinService.viewMjmInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(member.getMem_dept_number()!=null){
			if(member.getMem_dept_number().equals("all")){
				member.setMem_dept_number(null);
			}
		}
		
		
		int p=1;
		if(page != null){
			p=Integer.valueOf(page);
			if(p<1){
				p=1;
			}
		}
		Paging paging = new Paging(p, 10);
		paging.setNumberOfRecords(retireMemberListService.countTotalMember(member));
		
		int firstRow = 0;
		int endRow = 0;
		
		paging.makePaging();
		
		System.out.println(paging);
				
		firstRow = (paging.getCurrentPageNo() - 1) * paging.getRecordsPerPage() + 1;
		endRow = firstRow + paging.getRecordsPerPage() - 1;
		
		member.setStartPage(firstRow-1);	
		member.setEndPage(endRow);
		
		List<JoinMemberVO> memberList = retireMemberListService.getRetiredMemberList(member);
		
		model.addAttribute("dept_number", member.getMem_dept_number());
		model.addAttribute("page", paging);
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
