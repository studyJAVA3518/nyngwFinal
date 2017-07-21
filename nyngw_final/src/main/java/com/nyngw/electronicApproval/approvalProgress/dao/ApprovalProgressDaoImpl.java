package com.nyngw.electronicApproval.approvalProgress.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Electronic_ApprovalVO;

@Repository
public class ApprovalProgressDaoImpl implements ApprovalProgressDao {

	@Autowired
	private SqlSession sqlSession;
	
	//service에서 처리해서 parameter 받아줘야함
	public Electronic_ApprovalVO selectEA(String ea_number) {
		return (Electronic_ApprovalVO) sqlSession.selectOne("selectEA",ea_number);
	}

	public List<Electronic_ApprovalVO> selectCA() {
		return sqlSession.selectList("selectCA");
	}

	public List<Electronic_ApprovalVO> selectRA() {
		return sqlSession.selectList("selectRA");
	}
	
	//한 사원이 결재스탭에 포함 된 결재번호들을 검색
	public List<String> ap_selectEaNumberByMemId(String mem_number) {
		return sqlSession.selectList("ap_selectEaNumberByMemId",mem_number);
	}
	
	//가장 높은 결재 우선순위 ast_ea_number
	public int selectLastAstPriority(String ast_ea_number) {
		return (int) sqlSession.selectOne("selectLastAstPriority",ast_ea_number);
	}
	
	//자신의 결재 우선순위 ast_ea_number / ast_mem_number
	public int selectOneAstPriority(Map paramMap) {
		return (int) sqlSession.selectOne("selectOneAstPriority",paramMap);
	}
	
	//결재이력의 가장 높은 우선순위 ah_ea_number
	public int selectLastApprovalHistory(String ah_ea_number) {
		return (int) sqlSession.selectOne("selectLastApprovalHistory",ah_ea_number);
	}
	

}
