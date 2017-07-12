package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.electronicApproval.individualDocumentBox.dao.IndividualDocumentBoxDaoImpl;

@Service
public class IndividualDocumentBoxServiceImpl implements IndividualDocumentBoxService {
	
	@Autowired
	private IndividualDocumentBoxDaoImpl individualDocumentBoxDao;
	
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

}
