package com.nyngw.electronicApproval.draft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.electronicApproval.draft.service.DraftServiceImpl;

@Controller
@RequestMapping("/electronicApproval/draft")
public class DraftController {

	@Autowired
	private DraftServiceImpl draftService;
	
	@Autowired
	private CommonServiceImpl commonServiceImpl;
	
	
	@RequestMapping("/draft")
	public String draft(Model model){
		List<DocumentVO> documentList = draftService.defaultDocumentList();
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();
		
		for (DocumentVO docVO : documentList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(docVO.getDoc_number()));
		}
		
		model.addAttribute("documentList", documentList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/draft/draft";
	}
	
	/**
	 * 
	 * @param draftBoxOption - code8(기안문서A),code9(기안문서B),code10(기안문서C)	 
	 * @param searchOption - 문서명, 기안문서함, 문서설명, 문서등록일
	 * @param searchText - 사용자 입력 text
	 * @return
	 */
	@RequestMapping("/searchDraftDocument")
	public String searchDraft(String draftBoxOption, String searchOption, String searchText,Model model){
			List<DocumentVO> documentList = draftService.searchDocument(draftBoxOption, searchOption, searchText);
			model.addAttribute("documentList", documentList);
		return "electronicApproval/draft/draft";
	}
	
	@RequestMapping("/createDraftForm")
	public String createDraftForm(String doc_number,Model model){
		return "electronicApproval/draft/createDraftForm";
	}
	
	@RequestMapping("/approvalLineManager")
	public String approvalLineManager(Model model){
		String sb = draftService.getMenuDepartmentString().toString();
		model.addAttribute("sb",sb);
		return "electronicApproval/draft/approvalLineManager";
	}
	
	@RequestMapping("/searchMember")
	public String searchMember(Model model,String searchText){
//		List<MemberVO> memberList = approvalLineManagementService.searchMember(searchText);
//		model.addAttribute("memberList",memberList);
		return "electronicApproval/draft/approvalLineManagement";
	}

	//결재 상신
	@RequestMapping("/submitApproval")
	public String submitApproval(Model model){
//		List<MemberVO> memberList = approvalLineManagementService.searchMember(searchText);
//		model.addAttribute("memberList",memberList);
		return "electronicApproval/individualDocumentBox/submitApprovalBox";
	}
	
}
