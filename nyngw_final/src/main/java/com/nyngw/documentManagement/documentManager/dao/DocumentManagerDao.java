package com.nyngw.documentManagement.documentManager.dao;

import java.util.List;

import com.nyngw.dto.DocumentViewVO;

public interface DocumentManagerDao {
	public List<DocumentViewVO> selectDocumentManagerList(int firstRow, int endRow);
	public int selectDocumentCount();
}
