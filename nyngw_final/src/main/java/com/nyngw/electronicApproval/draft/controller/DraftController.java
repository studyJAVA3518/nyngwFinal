package com.nyngw.electronicApproval.draft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.MemberVO;
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
		List<Common_CodeVO> code_nameList = new ArrayList<Common_CodeVO>();

		for (DocumentVO docVO : documentList) {
			code_nameList.add(commonServiceImpl.findCodeNameByDocNumber(docVO.getDoc_number()));
		}
		model.addAttribute("draftBoxOption",draftBoxOption);
		model.addAttribute("searchOption",searchOption);
		model.addAttribute("searchText",searchText);
		
		model.addAttribute("documentList", documentList);
		model.addAttribute("code_nameList",code_nameList );
		return "electronicApproval/draft/draft";
	}
	
	@RequestMapping("/createDraftForm")
	public String createDraftForm(String doc_number,Model model){
		return "electronicApproval/draft/createDraftForm";
	}
	
	@RequestMapping("/approvalLineManager")
	@ResponseBody
	public Map<String, String> approvalLineManager(Model model){
		String sb = draftService.getMenuDepartmentString().toString();
		Map<String,String> jsonDataMap = new HashMap<String, String>();
		jsonDataMap.put("sb", sb);
//		model.addAttribute("sb",sb);
//		return "electronicApproval/draft/approvalLineManager";
		return jsonDataMap;
	}

	//결재 상신
	@RequestMapping("/submitApproval")
	public String submitApproval(Model model){
//		List<MemberVO> memberList = approvalLineManagementService.searchMember(searchText);
//		model.addAttribute("memberList",memberList);
		return "electronicApproval/individualDocumentBox/submitApprovalBox";
	}
	

	@RequestMapping("/findMemberByDepartment")
	@ResponseBody
	public List<Map> findMemberByDepartment(String dept_number,Model model){
		List<Map> memberJsonData = draftService.findMemberByDepartment(dept_number,model);
		return memberJsonData;
	}
	
	@RequestMapping("/searchMember")
	@ResponseBody
	public List<Map> searchMember(Model model,String searchText, String dept_number){
		List<Map> memberJsonData = draftService.searchMemberByMemberName(searchText,dept_number);
		return memberJsonData;
	}

}
