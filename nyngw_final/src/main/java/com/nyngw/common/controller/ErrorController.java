package com.nyngw.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/common/error")
public class ErrorController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ErrorController.class); 
	
	@RequestMapping("/404")
	public String pageError404(Model model){
		
		
		model.addAttribute("title", "error 404");
		model.addAttribute("error", "404");
		model.addAttribute("message", "해당 페이지를 찾을수 없습니다. 페이지의 주소를 확인해주세요.");
		
		return "error/pageError";
	}
	
	@RequestMapping("/500")
	public String pageError500(Model model){
		
		
		model.addAttribute("title", "error 500");
		model.addAttribute("error", "500");
		model.addAttribute("message", ".");
		
		return "error/pageError";
	}
	
	@RequestMapping("/exception")
	public String pageException(Model model){
		
		
		model.addAttribute("message", "error Exception");
		model.addAttribute("title", "Exception Error");
		model.addAttribute("error", "exception");
		model.addAttribute("url", "location.href='/user/loginForm';");
		
		return "error/pageError";		
	}
	
	
}
