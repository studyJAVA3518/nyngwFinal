package com.nyngw.electronicApproval.individualDocumentBox.dao;

import java.util.List;

import com.nyngw.dto.Electronic_ApprovalVO;

public interface IndividualDocumentBoxDao {
	public List<Electronic_ApprovalVO> selectSAB(String mem_id);
	public List<Electronic_ApprovalVO> selectCAB(String mem_id);
	public List<Electronic_ApprovalVO> selectRAB(String mem_id);
	
}
