package com.nyngw.businessSupport.dutyReport.service;

import java.security.Principal;
import java.util.List;

import org.springframework.ui.Model;

import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.Duty_Report_CommentVO;


public interface DutyReportService {
	public void dutyReportList(String searchDate, String reportType,
			String titleType, String val, Model model, Principal principal, int pageNumber);
	public List<Common_CodeVO> dutyReportCodeSelect();
	public void dutyReportDelete(String dr_number);
	public void dutyReportWrite(Duty_ReportVO dutyReportVO);
	public void dutyReportDetail(int pageNumber, String searchDate,
			String reportType, String titleType, String val, Model model,
			Principal principal, String dr_number);
	public void getDutyReportselect(String searchDate, String reportType,
			String titleType, String val, Model model, Principal principal, int pageNumber);
	public void dutyReportCommentWrite(String drc_content, String dr_number, Principal principal);
	public void dutyReportCommentDelete(String id);
}
