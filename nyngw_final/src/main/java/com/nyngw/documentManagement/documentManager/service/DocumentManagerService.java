package com.nyngw.documentManagement.documentManager.service;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.dto.DocumentVO;

public interface DocumentManagerService {
	public DocumentListView selectDocumentList(int pageNumber);
	public void documentUpdate(DocumentVO document);
	public DocumentVO selectDocument(String doc_number);
}
