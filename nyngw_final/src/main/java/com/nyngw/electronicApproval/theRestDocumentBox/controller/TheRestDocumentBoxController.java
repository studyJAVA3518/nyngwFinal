package com.nyngw.electronicApproval.theRestDocumentBox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/electronicApproval/theRestDocumentBox")
public class TheRestDocumentBoxController {

	@RequestMapping("/implementDocumentBox")
	public String implementDocumentBox(){
		return "electronicApproval/theRestDocumentBox/implementDocumentBox";
	}
	
	@RequestMapping("/referenceDocumentBox")
	public String referenceDocumentBox(){
		return "electronicApproval/theRestDocumentBox/referenceDocumentBox";
	}
	@RequestMapping("/overallDocumentBox")
	public String overAllDocumentBox(){
		return "electronicApproval/theRestDocumentBox/overallDocumentBox";
	}
	
	
}
