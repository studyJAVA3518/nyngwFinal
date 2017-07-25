package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.Electronic_ApprovalViewVO;

public interface IndividualDocumentBoxService {

	public Electronic_ApprovalViewVO sangsinList(int pageNumber, Board_SelectVO select);

	public List<Electronic_ApprovalVO> defaultCAB(String string);

	public List<Electronic_ApprovalVO> defaultRAB(String string);
//------------------------------------------------------------------
	public List<String> ap_selectEaNumberByMemId(String mem_number);
	
	public int selectLastAstPriority(String ast_ea_number);
	
	public int selectOneAstPriority(Map paramMap);
	
	public int selectLastApprovalHistory(String ah_ea_number);
	
	public Approval_HistoryVO selectAhMember(Map paramMap);
	public List<Approval_HistoryVO> selectAhAll(String ea_number);
	public String selectMemberDept(String mem_number);
	public String selectEaStepMember(Map map);
	public String selectDeptName(String dept_number);
	public String selectMemberPosition(String mem_number);
	public String selectPositionName(String position_number);

}
