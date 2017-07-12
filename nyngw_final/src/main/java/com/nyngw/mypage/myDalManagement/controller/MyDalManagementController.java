package com.nyngw.mypage.myDalManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.mypage.myDalManagement.MyAttendedListView;
import com.nyngw.mypage.myDalManagement.MyVacationListView;
import com.nyngw.mypage.myDalManagement.service.MyDalManagementServiceImpl;

@Controller
@RequestMapping("mypage/myDalManagement")
public class MyDalManagementController {
	
	@Autowired
	private MyDalManagementServiceImpl myDalManagementService;
	
	
	/**
	 * 출결보기 버튼을 누를시 화면전환 url을 보내주는 메서드
	 * @return	출결보기url 반환
	 */
	@RequestMapping("attended")
	public String attendOffCheck(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model){
		MyAttendedListView viewData = (MyAttendedListView) myDalManagementService.selectAttendList(pageNumber); 
		
		model.addAttribute("viewData", viewData);
		model.addAttribute("pageNumber", pageNumber);
		return "mypage/myDalManagement/attended";
	}
	
	/**
	 * 휴가현황보기 버튼을 누를시 화면전환 url을 보내주는 메서드
	 * @return 휴가보기 url반환
	 */
	@RequestMapping("vacation")
	public String vacationCheck(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model){
		MyVacationListView viewData = (MyVacationListView) myDalManagementService.selectVacationList(pageNumber); 
		
		model.addAttribute("viewData", viewData);
		model.addAttribute("pageNumber", pageNumber);
		return "mypage/myDalManagement/vacation";
	}
	
	
	
}
