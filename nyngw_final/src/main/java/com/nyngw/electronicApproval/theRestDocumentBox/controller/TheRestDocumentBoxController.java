package com.nyngw.electronicApproval.theRestDocumentBox.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.CommonApproval_TOTALVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.electronicApproval.theRestDocumentBox.service.TheRestDocumentBoxServiceImpl;

@Controller
@RequestMapping("/electronicApproval/theRestDocumentBox")
public class TheRestDocumentBoxController {

	@Autowired
	private TheRestDocumentBoxServiceImpl theRestDocumentBoxService;
	@Autowired
	private CommonServiceImpl commonService;
	
	//시행문서함
	@RequestMapping("/implementDocumentBox")
	public String implementDocumentBox(Model model,Principal principal){
		
		CommonApproval_TOTALVO vo = new CommonApproval_TOTALVO();
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		
		vo.setEa_mem_number(member.getMem_number());
		
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxService.getApprovalList(vo);
		
		model.addAttribute("EAList", appList);
		
		return "electronicApproval/theRestDocumentBox/implementDocumentBox";
	}
	
	//시행문서 검색
	@RequestMapping("/searchImplementDocument")
	public String searchImplementDocument(Model model,CommonApproval_TOTALVO vo,String docSearchOption,String searchText,Principal principal){
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		
		vo.setEa_mem_number(member.getMem_number());
		
		if(docSearchOption.equals("all")){
		}else if(docSearchOption.equals("ea_title")){
			vo.setEa_title(searchText);
		}else if(docSearchOption.equals("ea_number")){
			vo.setEa_number(searchText);
		}else if(docSearchOption.equals("doc_name")){
			vo.setDoc_name(searchText);
		}
		
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxService.getApprovalList(vo);
		
		model.addAttribute("EAList", appList);
		
		return "electronicApproval/theRestDocumentBox/implementDocumentBox";
	}
	
	//참조문서함
	@RequestMapping("/referenceDocumentBox")
	public String referenceDocumentBox(Model model,Principal principal){
		CommonApproval_TOTALVO vo = new CommonApproval_TOTALVO();
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		vo.setEa_mem_number(member.getMem_number());
		
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxService.getApprovalREList(vo);
		
		model.addAttribute("EAList", appList);
		
		return "electronicApproval/theRestDocumentBox/referenceDocumentBox";
	}
	//참조문서 검색
	@RequestMapping("/searchReferenceDocument")
	public String searchReferenceDocument(Model model,CommonApproval_TOTALVO vo,String docSearchOption,String searchText,Principal principal){
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		
		vo.setEa_mem_number(member.getMem_number());
		
		System.out.println("<<<===================== 참조");
		
		
		if(docSearchOption.equals("all")){
		}else if(docSearchOption.equals("ea_title")){
			vo.setEa_title(searchText);
		}else if(docSearchOption.equals("ea_number")){
			vo.setEa_number(searchText);
		}else if(docSearchOption.equals("doc_name")){
			vo.setDoc_name(searchText);
		}
		
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxService.getApprovalREList(vo);
		
		model.addAttribute("EAList", appList);
		
		
		return "electronicApproval/theRestDocumentBox/referenceDocumentBox";
	}
	
	//전체문서함
	@RequestMapping("/overallDocumentBox")
	public String overAllDocumentBox(Model model){
		
		CommonApproval_TOTALVO vo = new CommonApproval_TOTALVO();
		
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxService.getApprovalTOList(vo);
		
		model.addAttribute("EAList", appList);
		
		return "electronicApproval/theRestDocumentBox/overallDocumentBox";
	}
	//전체문서 검색
	@RequestMapping("/searchOverallDocument")
	public String searchOverallDocument(Model model,CommonApproval_TOTALVO vo,String docSearchOption,String searchText,Principal principal){
		
		MemberVO member = commonService.findMemberByMemId(principal.getName());
		
		vo.setEa_mem_number(member.getMem_number());
		
		if(docSearchOption.equals("all")){
		}else if(docSearchOption.equals("ea_title")){
			vo.setEa_title(searchText);
		}else if(docSearchOption.equals("ea_number")){
			vo.setEa_number(searchText);
		}else if(docSearchOption.equals("doc_name")){
			vo.setDoc_name(searchText);
		}
		
		List<CommonApproval_TOTALVO> appList= theRestDocumentBoxService.getApprovalTOList(vo);
		
		model.addAttribute("EAList", appList);
		
		return "electronicApproval/theRestDocumentBox/overallDocumentBox";
	}

}
