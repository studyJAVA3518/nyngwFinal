package com.nyngw.electronicApproval.individualDocumentBox.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Approval_HistoryVO;
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

	//-----------------------------------------------------------------
	
	@Override 
	public Approval_HistoryVO selectAhMember(Map paramMap){
		return (Approval_HistoryVO)sqlSession.selectOne("selectAhMember",paramMap);
	}
	
	@Override
	public List<Approval_HistoryVO> selectAhAll(String ea_number){
		return (List<Approval_HistoryVO>) sqlSession.selectList("selectAhAll",ea_number);
	}
	
	@Override
	public String selectMemberDept(String mem_number){
		return (String) sqlSession.selectOne("selectMemberDept",mem_number);
	}

	@Override
	public String selectEaStepMember(Map map) {
		return (String) sqlSession.selectOne("selectEaStepMember",map);
	}

	@Override
	public String selectDeptName(String dept_number) {
		return (String) sqlSession.selectOne("selectDeptName",dept_number);
	}

	@Override
	public String selectMemberPosition(String mem_number) {
		return (String) sqlSession.selectOne("selectMemberPosition",mem_number);
	}

	@Override
	public String selectPositionName(String position_number) {
		return (String) sqlSession.selectOne("selectPositionName",position_number);
	}
}
