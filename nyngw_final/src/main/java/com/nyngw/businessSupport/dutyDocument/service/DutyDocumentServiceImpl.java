package com.nyngw.businessSupport.dutyDocument.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.dutyDocument.dao.DutyDocumentDaoImpl;

@Service
public class DutyDocumentServiceImpl implements DutyDocumentService {
	@Autowired
	private DutyDocumentDaoImpl dutyDocumentDao;
}
