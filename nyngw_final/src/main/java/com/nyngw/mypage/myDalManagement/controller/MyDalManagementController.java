package com.nyngw.mypage.myDalManagement.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.VacationVO;
import com.nyngw.mypage.myDalManagement.MyAttendedListView;
import com.nyngw.mypage.myDalManagement.MyVacationListView;
import com.nyngw.mypage.myDalManagement.service.MyDalManagementServiceImpl;

@Controller
@RequestMapping("/mypage/myDalManagement")
public class MyDalManagementController {
	
	@Autowired
	private MyDalManagementServiceImpl myDalManagementService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	
	
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
	
	/**
	 * 출결보기 버튼을 누를시 화면전환 url을 보내주는 메서드
	 * @return	출결보기url 반환
	 */
	
	
	@RequestMapping("/attended")
	public String boardList1(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index, Principal principal){
		String user = principal.getName();
		MemberVO member = commonService.findMemberByMemId(user);
		
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		select.setMem_number(member.getMem_number());
		MyAttendedListView viewData = (MyAttendedListView) myDalManagementService.selectAttendList(pageNumber, select);
	
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("sideValue","sideMenu1");
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
		
		if(viewData.getPageTotalCount()>0){
			int beginPageNumber = (viewData.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > viewData.getPageTotalCount()){
				endPageNumber = viewData.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", viewData.getAttendedList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		return "mypage/myDalManagement/attended";
	}
	
	
	
	/**
	 * 휴가현황보기 버튼을 누를시 화면전환 url을 보내주는 메서드
	 * @return 휴가보기 url반환
	 */
	
	
	@RequestMapping("/vacation")
	public String vacationList(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index, String page, Principal principal){
		Board_SelectVO select = new Board_SelectVO();
		
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		String mem_number = member.getMem_number();
		select.setMem_number(mem_number);
		
		MyVacationListView viewData =(MyVacationListView)myDalManagementService.selectVacationList(pageNumber, select);
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select); 
		}
		
		if(viewData.getPageTotalCount()>0){
			int beginPageNumber = (viewData.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > viewData.getPageTotalCount()){
				endPageNumber = viewData.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", viewData.getVacationList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		model.addAttribute("sideValue","sideMenu2");
		model.addAttribute("page",page);
		return "mypage/myDalManagement/vacation";
	}
	
}
