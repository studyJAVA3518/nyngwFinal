package com.nyngw.documentManagement.documentManager.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.util.EncodingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.service.DocumentManagerServiceImpl;
import com.nyngw.dto.Board_SelectVO;
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
			Model model,String val, String index){
		Board_SelectVO select = new Board_SelectVO();
		if(val!=null && !val.equals("")){
			select.setIndex(index);
			select.setVal(val);
		}
		DocumentListView viewData = (DocumentListView) documentManagerService.selectDocumentList(pageNumber,select);

		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		if(val!=null && !val.equals("")){
			model.addAttribute("select",select);
		}
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
	/**
	 * 파일등록하여 글 등록하는 것
	 * @param model
	 * @param request
	 * @param document
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/documentInsertComplete", method=RequestMethod.POST)
	public String documentInsertComplete(Model model , CommandDocumentVO document) throws IOException{
		
		String upload = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/upload/document";
		
		MultipartFile multipartFile = document.getDoc_file_name();
		
		if(!multipartFile.isEmpty()){
			File file = new File(upload , URLEncoder.encode(multipartFile.getOriginalFilename()));//+"$$"+System.currentTimeMillis()
			
			multipartFile.transferTo(file);
			
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String mem_id = user.getUsername(); 
			MemberVO mem = basicSettingService.selectMember(mem_id);
			String mem_number = mem.getMem_number();
			
			DocumentVO doc=document.toDocumentVO();
			doc.setDoc_eadoc("n");
			if(document.getDoc_eadoc() != null){
				doc.setDoc_eadoc("y");
			}
			doc.setDoc_mem_number(mem_number);
			doc.setDoc_file_name(multipartFile.getOriginalFilename());
			
			model.addAttribute(doc);
			
			documentManagerService.documentInsertComplete(doc);
			
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
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO mem = basicSettingService.selectMember(user.getUsername());
		model.addAttribute("mem",mem);
		model.addAttribute("document",document);
		model.addAttribute("page",page);
		return "documentManagement/documentManager/documentDetail";
	}
	
	@RequestMapping("/documentDelete")
	public @ResponseBody Map<String,String> documentDelete(String id){
		documentManagerService.documentDelete(id);
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put("uri", "/documentManagement/documentManager/documentSelect");
		return resultMap;
	}
	
}
