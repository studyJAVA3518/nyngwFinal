package com.nyngw.documentManagement.documentManager.dao;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.DocumentViewVO;

public interface DocumentManagerDao {
	public List<DocumentViewVO> selectDocumentManagerList(int firstRow, int endRow, Board_SelectVO select);
	public int selectDocumentCount();
	public void documentManagerUpdate(DocumentVO document);
	public DocumentVO selectDocumentDetail(String doc_number);
	public DocumentVO selectDocumentUpdateForm(String doc_number);
	public int documentInsertComplete(DocumentVO documentVO);
	public List<Common_CodeVO> documentCodeSelect();
	public void documentDelete(String doc_number);
	public int documentSelectCount(Board_SelectVO select);
	
//	전자문서 -------------------------------------------------------
	public List<DocumentViewVO> selectEDocumentManagerList(int firstRow, int endRow,
			Board_SelectVO select);
	public int selectEDocumentCount();
}
