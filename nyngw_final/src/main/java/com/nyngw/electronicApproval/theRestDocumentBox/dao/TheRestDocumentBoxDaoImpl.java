package com.nyngw.electronicApproval.theRestDocumentBox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.CommonApproval_TOTALVO;

@Repository
public class TheRestDocumentBoxDaoImpl implements TheRestDocumentBoxDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CommonApproval_TOTALVO> getApprovalList(CommonApproval_TOTALVO vo) {
		return sqlSession.selectList("getApprovalList", vo);
	}

	public List<CommonApproval_TOTALVO> getApprovalREList(CommonApproval_TOTALVO vo) {
		return sqlSession.selectList("getApprovalREList", vo);
	}

	public List<CommonApproval_TOTALVO> getApprovalTOList(CommonApproval_TOTALVO vo) {
		return sqlSession.selectList("getApprovalTOList", vo);
	}

}
