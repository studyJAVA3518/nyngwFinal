package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;
import com.nyngw.electronicApproval.individualDocumentBox.dao.IndividualDocumentBoxDaoImpl;

@Service
public class IndividualDocumentBoxServiceImpl implements IndividualDocumentBoxService {
	
	@Autowired
	private IndividualDocumentBoxDaoImpl individualDocumentBoxDao;
	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;
	
	@Override
	public List<Electronic_ApprovalVO> defaultSAB(String mem_id) {
		return individualDocumentBoxDao.selectSAB(mem_id);
	}

	@Override
	public List<Electronic_ApprovalVO> defaultCAB(String mem_id) {
		return individualDocumentBoxDao.selectCAB(mem_id);
	}

	@Override
	public List<Electronic_ApprovalVO> defaultRAB(String mem_id) {
		return individualDocumentBoxDao.selectRAB(mem_id);
	}
//-----------------------------------------------------------------------
	@Override
	public List<String> ap_selectEaNumberByMemId(String mem_number){
		return approvalProgressDao.ap_selectEaNumberByMemId(mem_number);
	}

	@Override
	public int selectLastAstPriority(String ast_ea_number) {
		return approvalProgressDao.selectLastAstPriority(ast_ea_number);
	}

	@Override
	public int selectOneAstPriority(Map paramMap) {
		return approvalProgressDao.selectOneAstPriority(paramMap);
	}

	@Override
	public int selectLastApprovalHistory(String ah_ea_number) {
		return approvalProgressDao.selectLastApprovalHistory(ah_ea_number);
	}
	
	
}
