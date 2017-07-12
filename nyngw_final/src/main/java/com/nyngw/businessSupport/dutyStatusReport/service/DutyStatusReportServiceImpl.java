package com.nyngw.businessSupport.dutyStatusReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.dutyStatusReport.dao.DutyStatusReportDaoImpl;

@Service
public class DutyStatusReportServiceImpl implements DutyStatusReportService {
	@Autowired
	private DutyStatusReportDaoImpl dutyStatusReportDao;
}
