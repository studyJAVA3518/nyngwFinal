package com.nyngw.electronicApproval.approvalProgress.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Approval_StepVO;
import com.nyngw.dto.Electronic_ApprovalVO;

@Repository
public class ApprovalProgressDaoImpl implements ApprovalProgressDao {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 결재번호로 하나의 결재를 가져오기
	 * service에서 처리해서 parameter 받아줘야함
	 * @param ea_number
	 * @return
	 */
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
	
	/**
	 * 가장 높은 결재 우선순위 ast_ea_number
	 * @param ast_ea_number
	 * @return
	 */
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

	public List<Approval_StepVO> selectAstMemNumberByEaNumber(Approval_StepVO asVO) {
		return sqlSession.selectList("selectAstMemNumberByEaNumber",asVO);
	}

	public void insertApprovalHistory(Approval_HistoryVO ahVO) {
		sqlSession.selectList("insertApprovalHistory",ahVO);
	}

	public int selectLastAstPriorityOfA(String ea_number) {
		int result = 0;
		Object object = sqlSession.selectOne("selectLastAstPriorityOfA",ea_number);
		if(object==null){
			result=0;
		}else{
			result = (int)object;
		}
		return result;
	}

	public int selectLastAstPriorityOfB(String ea_number) {
		int result = 0;
		Object object = sqlSession.selectOne("selectLastAstPriorityOfB",ea_number);
		if(object==null){
			result=0;
		}else{
			result = (int)object;
		}
		return result;
	}

	public String selectAllByApprovalAstNumber(Approval_HistoryVO ahVO) {
		return (String) sqlSession.selectOne("selectAllByApprovalAstNumber",ahVO);
	}

	public List<String> selectAhAstNumberByEaNumber(String ah_ea_number) {
		return  sqlSession.selectList("selectAhAstNumberByEaNumber",ah_ea_number);
	}
	
	public String selectAstAllNumberByAstNumber(Map paramMap) {
		return (String) sqlSession.selectOne("selectAstAllNumberByAstNumber",paramMap);
	}
	public String EA_selectAstALNumber(Map paramMap) {
		return (String) sqlSession.selectOne("EA_selectAstALNumber",paramMap);
	}

	public void updateAstPriority(Map paramMap) {
		sqlSession.update("updateAstPriority",paramMap);
	}
	
	
	/**
	 * 전자결제에서 해당회원이 진행한 모든 전자결재번호 리스트로 불러오기
	 * @param ea_mem_number
	 * @return
	 */
	public List<Electronic_ApprovalVO> selectEaNumberList(String ea_mem_number){
		List<Electronic_ApprovalVO> eaNumList = sqlSession.selectList("selectEaNumberList",ea_mem_number);
		return eaNumList;
	}
	
	/**
	 * 결재히스토리에서 결재완료된 해당결재번호의 카운트를 세주는 쿼리
	 * @param ah_ea_number
	 * @return
	 */
	public int selectApprivalHistoryDoneCount(String ah_ea_number){
		int doneCount = (int) sqlSession.selectOne("selectApprivalHistoryDoneCount",ah_ea_number);
		return doneCount;
	}
	
	public int selectMaxAgreementPriority(String ea_number){
		return (int) sqlSession.selectOne("selectMaxAgreementPriority",ea_number);
	}
	
	/**
	 * 결재히스토리에서 반려된 해당결재번호의 카운트를 세주는 쿼리
	 * @param ah_ea_number
	 * @return
	 */
	public int selectApprivalHistoryRefuseCount(String ah_ea_number){
		int doneCount = (int) sqlSession.selectOne("selectApprivalHistoryRefuseCount",ah_ea_number);
		return doneCount;
	}
	
	/**
	 * 결재이력에서 제일 최근의 결제날짜를 가져온다
	 * @param ah_ea_number
	 * @return Date
	 */
	public Date selectResentHistoryDate(String ah_ea_number){
		List<Approval_HistoryVO> voList = sqlSession.selectList("selectResentHistoryDate",ah_ea_number);
		Date returnDate = null;
		for(int i = 0; i<voList.size();i++){
			if(i==(voList.size()-1)){
				returnDate = voList.get(i).getAh_time();
			}
		}
		return returnDate;
	}
	

}
