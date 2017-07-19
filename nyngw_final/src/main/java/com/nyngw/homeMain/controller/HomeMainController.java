package com.nyngw.homeMain.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.ScheduleVO;
import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;
import com.nyngw.sharingInformation.board.service.BoardServiceImpl;
import com.nyngw.sharingInformation.scheduleManagement.service.ScheduleManagementServiceImpl;

@Controller
@RequestMapping("/homeMain")
public class HomeMainController {
	
	@Autowired
	AppointedUIServiceImpl appointedUIService;
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	private ScheduleManagementServiceImpl shceculeManagementService;
	@Autowired
	private BasicSettingServiceImpl basicSettingService; 
	
	@RequestMapping("/main")
	public String homeMain(
			Model model, 
			Principal principal,
			HttpSession session){
		String url = "homeMain/main";
		
		//회사정보
		CompanyVO company = null;
		//사원 정보
		MemberVO member = null;
		//세션에서 불러올 로그인한 회원 아이디
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = user.getUsername();
		//공지사항 리스트
		List<BoardVO> boardList = null;
		//일정관리
		List<ScheduleVO> scheduleList = null;
		MemberVO mem = basicSettingService.selectMember(mem_id);
		String mem_number = mem.getMem_number();
		try {
			company = appointedUIService.checkCompany();
			model.addAttribute("company",company);
			member = appointedUIService.checkMember(mem_id);
			model.addAttribute("member",member);
			boardList = boardService.selectList();
			model.addAttribute("boardList", boardList);
			scheduleList = shceculeManagementService.SI_selectMemberSchedule(mem_number);
			model.addAttribute("scheduleList",scheduleList);
			scheduleList= shceculeManagementService.todayMemberSchedule(mem_id);
			model.addAttribute("scheduleList", scheduleList);
			int size = scheduleList.size();
			model.addAttribute("size",size);
			//session에 회사 로고 경로를 저장해야 한다.
			session.setAttribute("companyLogo",company.getCompany_logo());
			session.setAttribute("companyNumber",company.getCompany_number());
			session.setAttribute("memberName",member.getMem_name());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
}
