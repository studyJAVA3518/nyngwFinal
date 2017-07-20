package com.nyngw.businessSupport.dutyReport.service;

import java.security.Principal;
import java.util.List;

import org.springframework.ui.Model;

import com.nyngw.dto.Common_CodeVO;


public interface DutyReportService {
	public void dutyReportList(String searchDate, String reportType,
			String titleType, String val, Model model, Principal principal, int pageNumber);
	public List<Common_CodeVO> dutyReportCodeSelect();
}
