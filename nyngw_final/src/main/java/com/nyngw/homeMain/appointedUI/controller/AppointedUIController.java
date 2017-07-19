package com.nyngw.homeMain.appointedUI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;

@Controller
@RequestMapping("/homeMain/appointedUI")
public class AppointedUIController {

	@Autowired
	private AppointedUIServiceImpl appointedUIservice;
	
	@RequestMapping("appointedUI")
	public String appointedUI(Model model){
		List<BigMenuVO> bigMenu = (ArrayList<BigMenuVO>)appointedUIservice.selectBigMenu();
		System.out.println("UI설정화면좀떠보자~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
		model.addAttribute("bigMenu",bigMenu);
		
		return "homeMain/appointedUI/appointedUI";
	}
	
	public String appointedUI2(Model model, String big_number){
		System.out.println("여긴뭐냔ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ");
		List<MiddleMenuVO> middleMenu = appointedUIservice.selectMiddleMenu(big_number);
		model.addAttribute("middleMenu",middleMenu);
		return "homeMain/appointedUI/appointedUI";
	}
	
}
