package com.nyngw.documentManagement.documentManager.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiPanelUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.service.DocumentManagerServiceImpl;
import com.nyngw.dto.CommandDocumentVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.MemberVO;
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
	public String documentInsert(Model model,Principal principal){
		List<Common_CodeVO> codeList = documentManagerService.documentCodeSelect(); 
		model.addAttribute("codeList",codeList);
		
		MemberVO mem = basicSettingService.selectMember(principal.getName());
		
		model.addAttribute("mem", mem);
		
		return "documentManagement/documentManager/documentInsert";
	}
	
	@RequestMapping(value="/documentInsertComplete", method=RequestMethod.POST)
	public String documentInsertComplete(Model model , HttpServletRequest request, CommandDocumentVO document) throws IOException{
		
		
		String upload = "";
		
		MultipartFile multipartFile = document.getDoc_file_name();
		
		if(!multipartFile.isEmpty()){
			File file = new File(upload , multipartFile.getOriginalFilename()+"$$"+System.currentTimeMillis());
			
			multipartFile.transferTo(file);
			
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String mem_id = user.getUsername(); 
			MemberVO mem = basicSettingService.selectMember(mem_id);
			String mem_number = mem.getMem_number();
			
			DocumentVO doc=document.toDocumentVO();
//			if(document.getDoc_eadoc().equals(true)){
//				doc.setDoc_eadoc("y");
//			}else{
//				doc.setDoc_eadoc("n");
//			}
			doc.setDoc_eadoc("y");
			doc.setDoc_mem_number(mem_number);
			doc.setDoc_file_name(multipartFile.getOriginalFilename());
			
			//doc.setDoc_file_name(request.getParameter("doc_file_name"));
			
			model.addAttribute(doc);
			
			int result=0;
			result = documentManagerService.documentInsertComplete(doc);
			
			return "redirect:/documentManagement/documentManager/documentSelect";
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
