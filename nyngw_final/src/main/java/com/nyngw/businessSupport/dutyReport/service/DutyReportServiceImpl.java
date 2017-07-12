package com.nyngw.businessSupport.dutyReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.dutyReport.dao.DutyReportDaoImpl;

@Service
public class DutyReportServiceImpl implements DutyReportService {
	@Autowired
	private DutyReportDaoImpl dutyReportDao;
}
