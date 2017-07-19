package com.nyngw.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/common/error")
public class ErrorController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ErrorController.class); 
	
	@RequestMapping("/404")
	public String pageError404(Model model){
		
		System.out.println("Error page 404");
		
		model.addAttribute("message", "error 404");
		model.addAttribute("url", "history.go(-1);");
		
		return "error/error";
	}
	
	@RequestMapping("/500")
	public String pageError500(Model model){
		
		System.out.println("Error page 500");
		
		model.addAttribute("message", "error 500");
		model.addAttribute("url", "history.go(-1);");
		
		return "error/error";
	}
	
	@RequestMapping("/exception")
	public String pageException(Model model){
		
		System.out.println("Exception page");
		
		model.addAttribute("message", "error Exception");
		model.addAttribute("url", "location.href='/user/loginForm';");
		
		return "error/error";		
	}
	
	
}
