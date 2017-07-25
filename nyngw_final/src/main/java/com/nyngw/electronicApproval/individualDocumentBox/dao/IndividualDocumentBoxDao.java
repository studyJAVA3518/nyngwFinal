package com.nyngw.electronicApproval.individualDocumentBox.dao;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Electronic_ApprovalVO;

public interface IndividualDocumentBoxDao {
	public List<Electronic_ApprovalVO> selectSAB(int firstRow, int endRow,
			Board_SelectVO select);
	public int selectsangsinCount(String mem_number);
	public int boardsangsinCount(Board_SelectVO select);
	
	
	public List<Electronic_ApprovalVO> selectCAB(String mem_id);
	public List<Electronic_ApprovalVO> selectRAB(String mem_id);
	//----------------------------------------------------------
	
	public Approval_HistoryVO selectAhMember(Map paramMap);
	public List<Approval_HistoryVO> selectAhAll(String ea_number);
	public String selectMemberDept(String mem_number);
	public String selectEaStepMember(Map map);
	public String selectDeptName(String dept_number);
	public String selectMemberPosition(String mem_number);
	public String selectPositionName(String position_number);
}
