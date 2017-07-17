package com.nyngw.businessSupport.dutyDocument.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;

@Repository
public class DutyDocumentDaoImpl implements DutyDocumentDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int selectDocumentCount_DD() {
		int result =(Integer) sqlSession.selectOne("selectDocumentCount_DD");
		return result;
	}

	@Override
	public List<Duty_DocumentVO> selectDocumentList_DD(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Duty_DocumentVO> documentList = (ArrayList<Duty_DocumentVO>)sqlSession.selectList("selectDocumentList_DD",select,rowBounds);
		return documentList;
	}

	@Override
	public int documentSelectCount_DD(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("documentSelectCount_DD",select);
		return result;
	}

	@Override
	public Common_CodeVO documentSelectCodeName_DD(String reportType) {
		Common_CodeVO result = (Common_CodeVO) sqlSession.selectOne("documentSelectCodeName_DD",reportType);
		return result;
	}
	
	
	
}
