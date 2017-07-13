package com.nyngw.documentManagement.documentManager.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.service.DocumentManagerServiceImpl;
import com.nyngw.dto.DocumentVO;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;

/**
 * 문서관리 컨트롤러
 * @author p25
 */
@Controller
@RequestMapping("documentManagement/documentManager")
public class DocumentManagerController {
	
	@Autowired
	private DocumentManagerServiceImpl documentManagerService;
	
	@Autowired
	private BasicSettingServiceImpl basicSettingService; 
	
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
	
	@RequestMapping(value="/documentInsertComplete", method=RequestMethod.POST)
	public String documentInsertComplete(@RequestParam("doc_file_name") MultipartFile multipartFile,
			Model model , HttpServletRequest request, Principal principal) throws IOException{
		
		String upload = request.getSession().getServletContext().getRealPath("/upload");
		
		if(!multipartFile.isEmpty()){
			File file = new File(upload , multipartFile.getOriginalFilename()+"$$"+System.currentTimeMillis());
			
			multipartFile.transferTo(file);
			
			DocumentVO documentVO = new DocumentVO();
			documentVO.setDoc_name("doc_name");
			documentVO.setDoc_file_name("doc_file_name");
			documentVO.setDoc_explanation("doc_explanation");
			documentVO.setDoc_code_number("doc_code_number");
//			documentVO.setDoc_mem_number(basicSettingService.);
			
			model.addAttribute("doc_file_name",multipartFile.getOriginalFilename());
			model.addAttribute("uploadPath",file.getAbsolutePath());
			return "documentManagement/documentManager/documentSelect";
		}
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
