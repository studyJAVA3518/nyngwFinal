package com.nyngw.electronicApproval.approvalProgress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;

@Service
public class ApprovalProgressServiceImpl implements ApprovalProgressService {

	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;

	//모든 미결재문서를 가져옴
	public List<Electronic_ApprovalVO> defaultWA() {
		return approvalProgressDao.selectWA();
	}
	
	//WA = waiting Approval
	//미결재 문서 검색  -> 구현 필요
	public List<Electronic_ApprovalVO> searchWA(String eADateOption,
			String eAStatusOption, String eAClassificationOption,
			String docSearchOption, String searchText) {
		return approvalProgressDao.selectWA();
	}

	//모든 완료문서를 가져옴
	public List<Electronic_ApprovalVO> defaultCA() {
		return approvalProgressDao.selectCA();
	}

	public List<Electronic_ApprovalVO> defaultRA() {
		return approvalProgressDao.selectRA();
	}


}
