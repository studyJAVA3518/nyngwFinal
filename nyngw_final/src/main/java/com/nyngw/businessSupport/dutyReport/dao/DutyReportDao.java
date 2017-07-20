package com.nyngw.businessSupport.dutyReport.dao;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_ReportVO;

public interface DutyReportDao {
	public int dutyReportmentCount(Map<String, Object> select);
	public List<Duty_ReportVO> selectDutyReportList(int firstRow, int endRow, Map<String, Object> select);
	public List<Common_CodeVO> dutyReportCodeSelect();
}	
