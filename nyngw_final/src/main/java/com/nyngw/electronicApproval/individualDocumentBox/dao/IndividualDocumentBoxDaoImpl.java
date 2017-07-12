package com.nyngw.electronicApproval.individualDocumentBox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Electronic_ApprovalVO;

@Repository
public class IndividualDocumentBoxDaoImpl implements IndividualDocumentBoxDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<Electronic_ApprovalVO> selectSAB(String mem_id) {
		return sqlSession.selectList("selectSAB",mem_id);
	}

	public List<Electronic_ApprovalVO> selectCAB(String mem_id) {
		return sqlSession.selectList("selectCAB",mem_id);
	}
	
	public List<Electronic_ApprovalVO> selectRAB(String mem_id) {
		return sqlSession.selectList("selectRAB",mem_id);
	}

}
