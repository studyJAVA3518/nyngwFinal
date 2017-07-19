package com.nyngw.homeMain.appointedUI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;

@Controller
@RequestMapping("/homeMain/appointedUI")
public class AppointedUIController {

	@Autowired
	private AppointedUIServiceImpl appointedUIservice;
	
	@RequestMapping("appointedUI")
	public String appointedUI(){
		return "homeMain/appointedUI/appointedUI";
	}
}
