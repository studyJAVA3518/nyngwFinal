package com.nyngw.electronicApproval.individualDocumentBox.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.MeetingVO;

@Repository
public class IndividualDocumentBoxDaoImpl implements IndividualDocumentBoxDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public List<Electronic_ApprovalVO> selectSAB(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Electronic_ApprovalVO> sansingList = (ArrayList<Electronic_ApprovalVO>)sqlSession.selectList("selectSAB",select,rowBounds);
		return sansingList;
	}

	@Override
	public int selectsangsinCount(String mem_number) {
		int result =(Integer) sqlSession.selectOne("selectsangsinCount",mem_number);
		return result;
	}

	@Override
	public int boardsangsinCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardsangsinCount",select);
		return result;
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

	public String ID_selectAhStatus(String ea_number) {
		return (String) sqlSession.selectOne("ID_selectAhStatus",ea_number);
	}

	public String ID_selectAllAS(String ea_number) {
		return (String) sqlSession.selectOne("ID_selectAllAS",ea_number);
	}
}
