package com.nyngw.documentManagement.documentManager.service;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.dto.DocumentVO;

public interface DocumentManagerService {
	public DocumentListView selectDocumentList(int pageNumber);
	public void documentManagerUpdate(DocumentVO document);
	public DocumentVO selectDocumentDetail(String doc_number);
	public DocumentVO selectDocumentUpdateForm(String doc_number);
	public int documentInsertComplete(DocumentVO documentVO);
}
