package com.nyngw.documentManagement.documentManager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.DocumentViewVO;

@Repository
public class DocumentManagerDaoImpl implements DocumentManagerDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<DocumentViewVO> selectDocumentManagerList(int firstRow, int endRow) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<DocumentViewVO> documentList = (ArrayList<DocumentViewVO>)sqlSession.selectList("selectDocumentManagerList","",rowBounds);
		return documentList;
	}

	@Override
	public int selectDocumentCount() {
		int result =(Integer) sqlSession.selectOne("selectDocumentCount");
		return result;
	}

	
	@Override
	public void documentManagerUpdate(DocumentVO document) {
		sqlSession.update("documentManagerUpdate", document);
	}
	/**
	 * 상세정보 조회
	 */
	@Override
	public DocumentVO selectDocumentDetail(String doc_number){
		DocumentVO document = (DocumentVO) sqlSession.selectOne("selectDocumentDetail",doc_number);
		return document;
	}

	@Override
	public DocumentVO selectDocumentUpdateForm(String doc_number) {
		DocumentVO document = (DocumentVO) sqlSession.selectOne("selectDocumentUpdateForm",doc_number);
		return document;
	}
}
