package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.Electronic_ApprovalVO;

public interface IndividualDocumentBoxService {

	public List<Electronic_ApprovalVO> defaultSAB(String mem_id);

	public List<Electronic_ApprovalVO> defaultCAB(String string);

	public List<Electronic_ApprovalVO> defaultRAB(String string);
//------------------------------------------------------------------
	public List<String> ap_selectEaNumberByMemId(String mem_number);
	
	public int selectLastAstPriority(String ast_ea_number);
	
	public int selectOneAstPriority(Map paramMap);
	
	public int selectLastApprovalHistory(String ah_ea_number);
}
