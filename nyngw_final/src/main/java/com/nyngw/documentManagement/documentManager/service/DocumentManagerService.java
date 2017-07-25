package com.nyngw.documentManagement.documentManager.service;

import java.util.List;

import org.springframework.ui.Model;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.DocumentVO;

public interface DocumentManagerService {
	public DocumentListView selectDocumentList(int pageNumber, Board_SelectVO select);
	public void documentManagerUpdate(DocumentVO document);
	public DocumentVO selectDocumentDetail(String doc_number);
	public DocumentVO selectDocumentUpdateForm(String doc_number);
	public int documentInsertComplete(DocumentVO documentVO);
	public List<Common_CodeVO> documentCodeSelect();
	public void documentDelete(String doc_number);
	
//	electro =========================================
	public int edocumentInsertComplete(DocumentVO documentVO);
	DocumentListView selectEDocumentList(int pageNumber, Board_SelectVO select);
	public void edocumentManagerUpdate(DocumentVO document);
}
