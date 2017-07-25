package com.nyngw.electronicApproval.theRestDocumentBox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.CommonApproval_TOTALVO;
import com.nyngw.electronicApproval.theRestDocumentBox.dao.TheRestDocumentBoxDaoImpl;

@Service
public class TheRestDocumentBoxServiceImpl implements TheRestDocumentBoxService {

	@Autowired
	private TheRestDocumentBoxDaoImpl theRestDocumentBoxDao;
	
	public List<CommonApproval_TOTALVO> getApprovalList(CommonApproval_TOTALVO vo) {
		return theRestDocumentBoxDao.getApprovalList(vo);
	}

	public List<CommonApproval_TOTALVO> getApproval_HistoryList(CommonApproval_TOTALVO vo) {
		return theRestDocumentBoxDao.getApproval_HistoryList(vo);
	}

	public List<CommonApproval_TOTALVO> getApprovalREList(CommonApproval_TOTALVO vo) {
		return theRestDocumentBoxDao.getApprovalREList(vo);
	}

	public List<CommonApproval_TOTALVO> getApproval_RE_HistoryList(CommonApproval_TOTALVO vo) {
		return theRestDocumentBoxDao.getApproval_RE_HistoryList(vo);
	}

	public List<CommonApproval_TOTALVO> getApprovalTOList(CommonApproval_TOTALVO vo) {
		return theRestDocumentBoxDao.getApprovalTOList(vo);
	}

}
