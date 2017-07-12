package com.nyngw.mypage.myPayManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.mypage.myPayManagement.service.MyPayManagementServiceImpl;

@Controller
@RequestMapping("mypage/myPayManagement")
public class MyPayManagementController {
//	@Autowired
//	private MyPayManagementServiceImpl myPayManagementServiceImpl;
	
	/**
	 * 급여명세서보기 버튼을 눌러 화면이동 url을 반환하는 메서드
	 * @return	급여명세서 url 반환
	 */
	@RequestMapping("salary")
	public String payStatementCheck(){
	
		return "mypage/myPayManagement/salary";
	}
	
	/**
	 * 퇴직금보기 버튼을 누를시 화면이동 url을 반환하는 메서드
	 * @return 퇴직금 url 반환
	 */
	@RequestMapping("severance")
	public String severancePayCheck(){

		return "mypage/myPayManagement/severance";
	}
}
