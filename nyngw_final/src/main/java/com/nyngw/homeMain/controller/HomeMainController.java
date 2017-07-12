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

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;
import com.nyngw.sharingInformation.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/homeMain")
public class HomeMainController {
	
	@Autowired
	AppointedUIServiceImpl appointedUIService;
	@Autowired
	BoardServiceImpl boardService;
	
	@RequestMapping("/main")
	public String homeMain(Model model, Principal principal){
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
		try {
			company = appointedUIService.checkCompany();
			model.addAttribute("company",company);
			member = appointedUIService.checkMember(mem_id);
			model.addAttribute("member",member);
			boardList = boardService.selectList();
			model.addAttribute("boardList", boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
}
