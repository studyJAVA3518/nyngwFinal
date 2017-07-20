package com.nyngw.businessSupport.dutyReport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_ReportVO;

@Repository
public class DutyReportDaoImpl implements DutyReportDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int dutyReportmentCount(Map<String, Object> select) {
		int result =(Integer) sqlSession.selectOne("dutyReportmentCount",select);
		return result;
	}
	
	@Override
	public List<Duty_ReportVO> selectDutyReportList(int firstRow, int endRow, Map<String, Object> select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Duty_ReportVO> documentList = (ArrayList<Duty_ReportVO>)sqlSession.selectList("selectDutyReportList",select,rowBounds);
		return documentList;
	}

	@Override
	public List<Common_CodeVO> dutyReportCodeSelect() {
		List<Common_CodeVO> codeList = (ArrayList<Common_CodeVO>) sqlSession.selectList("dutyReportCodeSelect");
		return codeList;
	}
	
}
