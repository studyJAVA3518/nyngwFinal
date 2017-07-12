package com.nyngw.electronicApproval.approvalProgress.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Electronic_ApprovalVO;

@Repository
public class ApprovalProgressDaoImpl implements ApprovalProgressDao {

	@Autowired
	private SqlSession sqlSession;
	
	//service에서 처리해서 parameter 받아줘야함
	@Override
	public List<Electronic_ApprovalVO> selectWA() {
		return sqlSession.selectList("selectWA");
	}

	@Override
	public List<Electronic_ApprovalVO> selectCA() {
		return sqlSession.selectList("selectCA");
	}

	@Override
	public List<Electronic_ApprovalVO> selectRA() {
		return sqlSession.selectList("selectRA");
	}

}
