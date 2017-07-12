package com.nyngw.documentManagement.documentManager.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.dao.DocumentManagerDaoImpl;
import com.nyngw.dto.DocumentViewVO;

@Service
public class DocumentManagerServiceImpl implements DocumentManagerService {
	@Autowired
	private DocumentManagerDaoImpl documentManagerDao;
	private static final int DOCUMENT_COUNT_PER_PAGE = 5;
	
	@Override
	public DocumentListView selectDocumentList(int pageNumber) {
		int currentPageNumber = pageNumber;
		
		int documentTotalCount = documentManagerDao.selectDocumentCount();
		
		List<DocumentViewVO> documentList = null;
		int firstRow = 0;
		int endRow = 0;
		if(documentTotalCount > 0){
			firstRow = (pageNumber - 1) * DOCUMENT_COUNT_PER_PAGE + 1;
			endRow = firstRow + DOCUMENT_COUNT_PER_PAGE - 1;
			documentList = documentManagerDao.selectDocumentManagerList(firstRow, endRow);
		} else {
			currentPageNumber = 0;
			documentList = Collections.emptyList();
		}
		return new DocumentListView(documentList, documentTotalCount, currentPageNumber, DOCUMENT_COUNT_PER_PAGE, firstRow, endRow);
	}
	
}
