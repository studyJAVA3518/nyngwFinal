package com.nyngw.documentManagement.documentManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.service.DocumentManagerServiceImpl;

@Controller
@RequestMapping("documentManagement/documentManager")
public class DocumentManagerController {
	
	@Autowired
	private DocumentManagerServiceImpl documentManagerService;
	
	@RequestMapping("/documentSelect")
	public String documentSelect(@RequestParam(value="page",defaultValue="1")int pageNumber,
			Model model){
		DocumentListView viewData = (DocumentListView) documentManagerService.selectDocumentList(pageNumber);
		
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		return "documentManagement/documentManager/documentSelect";
	}
	
	@RequestMapping("/documentInsert")
	public String documentInsert(){
		return "documentManagement/documentManager/documentInsert";
	}
	
	@RequestMapping("/documentDetail")
	public String documentDetail(){
		return "documentManagement/documentManager/documentDetail";
	}
	
	@RequestMapping("/documentUpdate")
	public String documentUpdate(){
		return "documentManagement/documentManager/documentUpdate";
	}
}
