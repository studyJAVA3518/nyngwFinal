package com.nyngw.documentManagement.documentManager.dao;

import java.util.List;

import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.DocumentViewVO;

public interface DocumentManagerDao {
	public List<DocumentViewVO> selectDocumentManagerList(int firstRow, int endRow);
	public int selectDocumentCount();
	public void documentManagerUpdate(DocumentVO document);
	public DocumentVO selectDocumentDetail(String doc_number);
	public DocumentVO selectDocumentUpdateForm(String doc_number);
}
