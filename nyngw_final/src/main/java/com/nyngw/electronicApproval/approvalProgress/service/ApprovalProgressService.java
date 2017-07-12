package com.nyngw.electronicApproval.approvalProgress.service;

import java.util.List;

import com.nyngw.dto.Electronic_ApprovalVO;

public interface ApprovalProgressService {
	public List<Electronic_ApprovalVO> defaultWA();
	//WA = waiting Approval
	//미결재 문서 검색  -> 구현 필요
	public List<Electronic_ApprovalVO> searchWA(String eADateOption,
			String eAStatusOption, String eAClassificationOption,
			String docSearchOption, String searchText);
	//모든 완료문서를 가져옴
	public List<Electronic_ApprovalVO> defaultCA();
	public List<Electronic_ApprovalVO> defaultRA();

}
