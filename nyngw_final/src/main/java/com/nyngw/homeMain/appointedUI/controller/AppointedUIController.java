package com.nyngw.homeMain.appointedUI.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.dto.UserUiVO;
import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;

@Controller
@RequestMapping("/homeMain/appointedUI")
public class AppointedUIController {

	@Autowired
	private AppointedUIServiceImpl appointedUIservice;
	
	@RequestMapping("/appointedUI")
	public String appointedUI(Model model){
		return "homeMain/appointedUI/appointedUI";
	}
	///////////////////////UI
	//화면 1용
	@RequestMapping("/bigMenu")
	@ResponseBody
	public List<Map> bigMenu(Model model){
		List<Map> bigList = appointedUIservice.selectBigMenu();
		return bigList;
	}

	@RequestMapping("/middleMenu")
	@ResponseBody
	public List<Map> middleMenu(String big_num){
		List<Map> middleList = appointedUIservice.selectMiddleMenu(big_num);
		return middleList;
	}

	//화면2용
	@RequestMapping("/bigMenu1")
	@ResponseBody
	public List<Map> bigMenu1(Model model){
		List<Map> bigList = appointedUIservice.selectBigMenu();
		return bigList;
	}
	
	@RequestMapping("/middleMenu1")
	@ResponseBody
	public List<Map> middleMenu1(String big_num){
		List<Map> middleList = appointedUIservice.selectMiddleMenu(big_num);
		return middleList;
	}
	
	//화면3용	
	@RequestMapping("/bigMenu2")
	@ResponseBody
	public List<Map> bigMenu2(Model model){
		List<Map> bigList = appointedUIservice.selectBigMenu();
		return bigList;
	}
	
	@RequestMapping("/middleMenu2")
	@ResponseBody
	public List<Map> middleMenu2(String big_num){
		List<Map> middleList = appointedUIservice.selectMiddleMenu(big_num);
		return middleList;
	}
	
	@RequestMapping("/userUiSave")
	public @ResponseBody Map<String,String> userUiSave(UserUiVO userUi, Principal principal){
		appointedUIservice.userUiInsert_UI(userUi, principal);
		Map<String, String> map = new HashMap<String, String>();
		map.put("uri", "/homeMain/appointedUI/appointedUI");
		return map;
	}
}
