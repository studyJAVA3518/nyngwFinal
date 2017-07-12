package com.nyngw.documentManagement.documentManager.service;

import com.nyngw.documentManagement.documentManager.DocumentListView;

public interface DocumentManagerService {
	public DocumentListView selectDocumentList(int pageNumber);
}
