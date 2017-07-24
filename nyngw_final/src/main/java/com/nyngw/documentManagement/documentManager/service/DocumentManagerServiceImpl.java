package com.nyngw.documentManagement.documentManager.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.documentManagement.documentManager.DocumentListView;
import com.nyngw.documentManagement.documentManager.dao.DocumentManagerDaoImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.DocumentViewVO;
@Service
public class DocumentManagerServiceImpl implements DocumentManagerService {
	@Autowired
	private DocumentManagerDaoImpl documentManagerDao;
	private static final int DOCUMENT_COUNT_PER_PAGE = 5;
	
	
	@Override
	public DocumentListView selectDocumentList(int pageNumber, Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		
		int documentTotalCount = documentManagerDao.selectDocumentCount();
		
		List<DocumentViewVO> documentList = null;
		int firstRow = 0;
		int endRow = 0;
		if(documentTotalCount > 0){
			firstRow = (currentPageNumber - 1) * DOCUMENT_COUNT_PER_PAGE + 1;
			endRow = firstRow + DOCUMENT_COUNT_PER_PAGE - 1;
			documentList = documentManagerDao.selectDocumentManagerList(firstRow, endRow,select);
			if(select.getVal()!=null && !select.getVal().equals("")){
				documentTotalCount = documentManagerDao.documentSelectCount(select);
			}
		} else {
			currentPageNumber = 0;
			documentList = Collections.emptyList();
		}
		
		return new DocumentListView(documentList, documentTotalCount, currentPageNumber, DOCUMENT_COUNT_PER_PAGE, firstRow, endRow);
	
	}
	
	@Override
	public DocumentVO selectDocumentDetail(String doc_number){
		DocumentVO document = documentManagerDao.selectDocumentDetail(doc_number);
		return document;
	}
	
	@Override
	public void documentManagerUpdate(DocumentVO document) {
		documentManagerDao.documentManagerUpdate(document);
	}

	@Override
	public DocumentVO selectDocumentUpdateForm(String doc_number) {
		DocumentVO document = documentManagerDao.selectDocumentUpdateForm(doc_number);
		return document;
	}

	@Override
	public int documentInsertComplete(DocumentVO documentVO) {
		int result = documentManagerDao.documentInsertComplete(documentVO);
		return result;
	}

	@Override
	public List<Common_CodeVO> documentCodeSelect() {
		List<Common_CodeVO> code = documentManagerDao.documentCodeSelect();
		return code;
	}

	@Override
	public void documentDelete(String doc_number) {
		documentManagerDao.documentDelete(doc_number);
	}
	
//	electronic ---------------------------------------------------
	@Override
	public DocumentListView selectEDocumentList(int pageNumber, Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		
		int documentTotalCount = documentManagerDao.selectEDocumentCount();
		
		List<DocumentViewVO> documentList = null;
		int firstRow = 0;
		int endRow = 0;
		if(documentTotalCount > 0){
			firstRow = (currentPageNumber - 1) * DOCUMENT_COUNT_PER_PAGE + 1;
			endRow = firstRow + DOCUMENT_COUNT_PER_PAGE - 1;
			documentList = documentManagerDao.selectEDocumentManagerList(firstRow, endRow,select);
			if(select.getVal()!=null && !select.getVal().equals("")){
				documentTotalCount = documentManagerDao.documentSelectCount(select);
			}
		} else {
			currentPageNumber = 0;
			documentList = Collections.emptyList();
		}
		
		return new DocumentListView(documentList, documentTotalCount, currentPageNumber, DOCUMENT_COUNT_PER_PAGE, firstRow, endRow);
	
	}
	
}
