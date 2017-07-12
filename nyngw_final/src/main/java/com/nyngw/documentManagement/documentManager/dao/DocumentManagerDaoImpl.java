package com.nyngw.documentManagement.documentManager.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
