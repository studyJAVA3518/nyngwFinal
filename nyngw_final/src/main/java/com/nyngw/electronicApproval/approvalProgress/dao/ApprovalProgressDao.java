package com.nyngw.electronicApproval.approvalProgress.dao;

import java.util.List;

import com.nyngw.dto.Electronic_ApprovalVO;

public interface ApprovalProgressDao {
	public List<Electronic_ApprovalVO> selectWA();
	public List<Electronic_ApprovalVO> selectCA();
	public List<Electronic_ApprovalVO> selectRA();
}
