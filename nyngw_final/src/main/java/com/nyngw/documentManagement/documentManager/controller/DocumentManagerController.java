package com.nyngw.documentManagement.documentManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.service.DocumentManagerServiceImpl;
import com.nyngw.dto.DocumentVO;

/**
 * 문서관리 컨트롤러
 * @author p25
 */
@Controller
@RequestMapping("documentManagement/documentManager")
public class DocumentManagerController {
	
	@Autowired
	private DocumentManagerServiceImpl documentManagerService;
	
	/**
	 * @param pageNumber
	 * @param model
	 * @return 문서의 전체목록을 불러옴
	 */
	@RequestMapping("/documentSelect")
	public String documentSelect(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model){
		DocumentListView viewData = (DocumentListView) documentManagerService.selectDocumentList(pageNumber);
		
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		return "documentManagement/documentManager/documentSelect";
	}
	
	public String documentSearch(@RequestParam(value="page",defaultValue="1")int pageNumber,String val, String index, Model model){
		return "documentManagement/documentManager/documentSelect";
	}
	@RequestMapping("/documentInsert")
	public String documentInsert(){
		return "documentManagement/documentManager/documentInsert";
	}
	
	/**
	 * 
	 * @param doc_number
	 * @param model
	 * @param page
	 * @return 문서를 수정할 수 있는 화면을 나타냄
	 */
	@RequestMapping("/documentUpdateForm")
	public String documentUpdate(String doc_number, Model model, String page){
		DocumentVO document = documentManagerService.selectDocumentUpdateForm(doc_number);
		model.addAttribute("document",document);
		model.addAttribute("page",page);
		return "documentManagement/documentManager/documentUpdate";
	}
	
	/**
	 * 
	 * @param document
	 * @return 문서 수정을 완료하고 목록을 보여줌
	 */
	@RequestMapping("/documentUpdate")
	public String documentUpdate(DocumentVO document){
		documentManagerService.documentManagerUpdate(document);
		return "redirect:/documentManagement/documentManager/documentSelect";
	}
	
	/**
	 * 
	 * @param dv_doc_number
	 * @param model
	 * @param page
	 * @return 해당문서의 상세정보를 보여줌
	 */
	@RequestMapping("/documentDetail")
	public String documentDetail(String dv_doc_number, Model model, String page){
		DocumentVO document = documentManagerService.selectDocumentDetail(dv_doc_number);
		model.addAttribute("document",document);
		model.addAttribute("page",page);
		return "documentManagement/documentManager/documentDetail";
	}
	
	@RequestMapping("/documentDelete")
	public String documentDelete(String doc_number,Model model, String page){
		return "";
	}
}
