package com.nyngw.businessSupport.dutyReport.dao;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.Duty_Report_CommentVO;

public interface DutyReportDao {
	public int dutyReportmentCount(Map<String, Object> select);
	public List<Duty_ReportVO> selectDutyReportList(int firstRow, int endRow, Map<String, Object> select);
	public List<Common_CodeVO> dutyReportCodeSelect();
	public void dutyReportDelete(String dr_number);
	public void dutyReportWrite(Duty_ReportVO dutyReportVO);
	public Duty_ReportVO dutyReportSelect(String dr_number);
	public int dutyReportCount(Map<String, Object> select);
	public List<Duty_ReportVO> selectDutyReportList2(int firstRow, int endRow, Map<String, Object> select);
	public void dutyReportCommentWrite(Duty_Report_CommentVO drcVO);
	public List<Duty_Report_CommentVO> selectDutyReportComment(String dr_number);
	public void dutyReportCommentDelete(String drc_number);
}	
