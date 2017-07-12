package com.nyngw.electronicApproval.theRestDocumentBox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/electronicApproval/theRestDocumentBox")
public class TheRestDocumentBoxController {

	//시행문서함
	@RequestMapping("/implementDocumentBox")
	public String implementDocumentBox(){
		return "electronicApproval/theRestDocumentBox/implementDocumentBox";
	}
	
	//시행문서 검색
	@RequestMapping("/searchImplementDocument")
	public String searchImplementDocument(){
		return "electronicApproval/theRestDocumentBox/implementDocumentBox";
	}
	
	//참조문서함
	@RequestMapping("/referenceDocumentBox")
	public String referenceDocumentBox(){
		return "electronicApproval/theRestDocumentBox/referenceDocumentBox";
	}
	//참조문서 검색
	@RequestMapping("/searchReferenceDocument")
	public String searchReferenceDocument(){
		return "electronicApproval/theRestDocumentBox/referenceDocumentBox";
	}
	
	//전체문서함
	@RequestMapping("/overallDocumentBox")
	public String overAllDocumentBox(){
		return "electronicApproval/theRestDocumentBox/overallDocumentBox";
	}
	
	//전체문서 검색
	@RequestMapping("/searchOverallDocument")
	public String searchOverallDocument(){
		return "electronicApproval/theRestDocumentBox/overallDocumentBox";
	}
	
}
