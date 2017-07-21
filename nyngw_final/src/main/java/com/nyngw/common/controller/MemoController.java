package com.nyngw.common.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MemoVO;
import com.nyngw.dto.Paging;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private CommonServiceImpl commonService;
	
	private String page;
	private Paging paging;
	
	@RequestMapping("/add")
	public @ResponseBody Map<String,Object> memoAdd(Principal principal,MemoVO memo,String page){
		Map<String,Object> map = new HashMap<String,Object>();
		this.page=page;
		map.put("su", "no");
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		try{
			memo.setMemo_mem_number(member.getMem_number());
			commonService.addMemo(memo);
			map.put("su", "ok");
		}catch(Exception e){
		}
		try{
			List<MemoVO> memoList = getList(principal, memo);
			map.put("page", paging);
			map.put("list", memoList);
		}catch(Exception e){
		}
		
		return map;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String,Object> memoDel(MemoVO memo,Principal principal,String page){
		Map<String,Object> map = new HashMap<String,Object>();
		this.page=page;
		map.put("su", "no");
		try{
			commonService.deleteMemo(memo);
			map.put("su", "ok");
		}catch(Exception e){
		}
		try{
			List<MemoVO> memoList = getList(principal, memo);
			map.put("page", paging);
			map.put("list", memoList);
		}catch(Exception e){
		}
		return map;
	}
	
	@RequestMapping("/modifyform")
	public @ResponseBody Map<String,Object> memoForm(MemoVO memo,String page){
		Map<String,Object> map = new HashMap<String,Object>();
		this.page=page;
		map.put("su", "no");
		
		memo = commonService.getMemo(memo); 
		map.put("su", "ok");
		
		map.put("memo", memo);
		return map;
	}
	
	@RequestMapping("/modify")
	public @ResponseBody Map<String,Object> modifyMemo(MemoVO memo,Principal principal,String page){
		Map<String,Object> map = new HashMap<String,Object>();
		this.page=page;
		map.put("su", "no");
		
		commonService.modifyMemo(memo);
		map.put("su", "ok");
		
		try{
			List<MemoVO> memoList = getList(principal, memo);
			map.put("page", paging);
			map.put("list", memoList);
		}catch(Exception e){
		}
		return map;
	}
	
	@RequestMapping("/list")
	public @ResponseBody Map<String,Object> memoList(Principal principal,String page){
		Map<String,Object> map = new HashMap<String,Object>();
		MemoVO memo = new MemoVO();
		this.page=page;
		
		try{
			List<MemoVO> memoList = getList(principal, memo);
			map.put("page", paging);
			map.put("list", memoList);
			map.put("su", "ok");
		}catch(Exception e){
		}
		return map;
	}
	
	private List<MemoVO> getList(Principal principal,MemoVO memo){
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		memo.setMemo_mem_number(member.getMem_number());

		
		int p=1;
		if(page != null){
			p=Integer.valueOf(page);
			if(p<1){
				p=1;
			}
		}
		paging = new Paging(p, 6);
		paging.setNumberOfRecords(commonService.countMemo(memo));
		
		int firstRow = 0;
		int endRow = 0;
		
		paging.makePaging();
		
		System.out.println(paging);
		
		if(p > paging.getFinalPageNo()){
			paging.setCurrentPageNo(1);
		}
				
		firstRow = (paging.getCurrentPageNo() - 1) * paging.getRecordsPerPage() + 1;
		endRow = firstRow + paging.getRecordsPerPage() - 1;
		
		memo.setStartPage(firstRow-1);	
		memo.setEndPage(endRow);
		
		return commonService.getMemoList(memo);
	}
}
