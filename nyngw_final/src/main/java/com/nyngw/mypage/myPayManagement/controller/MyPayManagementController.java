package com.nyngw.mypage.myPayManagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.Member_pay_PageViewVO;
import com.nyngw.mypage.myPayManagement.service.MyPayManagementServiceImpl;

@Controller
@RequestMapping("mypage/myPayManagement")
public class MyPayManagementController {
	@Autowired
	private MyPayManagementServiceImpl myPayManagementService;
	
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 1;
	
	@Autowired
	private CommonServiceImpl commonService;
	/**
	 * 급여명세서보기 버튼을 눌러 화면이동 url을 반환하는 메서드
	 * @return	급여명세서 url 반환
	 */
	@RequestMapping("salary")
	public String payStatementCheck(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model,String val, String index, String page, Principal principal){
		Board_SelectVO select = new Board_SelectVO();
		
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		String mem_id = principal.getName();
		MemberVO mem = commonService.findMemberByMemId(mem_id);
		String mem_number = mem.getMem_number();
		select.setMem_number(mem_number);
		
		Member_pay_PageViewVO viewData =(Member_pay_PageViewVO)myPayManagementService.selectMySalaryList(pageNumber, select);
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
			model.addAttribute("end", viewData.getMemberPayViewList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}
		
		model.addAttribute("page",page);
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
