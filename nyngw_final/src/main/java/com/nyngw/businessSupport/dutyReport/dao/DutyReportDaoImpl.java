package com.nyngw.businessSupport.dutyReport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.Duty_Report_CommentVO;

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
	public int dutyReportCount(Map<String, Object> select){
		int result = (Integer) sqlSession.selectOne("dutyReportCount",select);
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
	public List<Duty_ReportVO> selectDutyReportList2(int firstRow, int endRow, Map<String, Object> select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Duty_ReportVO> documentList = (ArrayList<Duty_ReportVO>)sqlSession.selectList("selectDutyReportList2",select,rowBounds);
		return documentList;
	}
	@Override
	public List<Common_CodeVO> dutyReportCodeSelect() {
		List<Common_CodeVO> codeList = (ArrayList<Common_CodeVO>) sqlSession.selectList("dutyReportCodeSelect");
		return codeList;
	}

	@Override
	public void dutyReportDelete(String dr_number) {
		sqlSession.delete("dutyReportDelete",dr_number);
	}

	@Override
	public void dutyReportWrite(Duty_ReportVO dutyReportVO) {
		sqlSession.insert("dutyReportWrite",dutyReportVO);
	}

	@Override
	public Duty_ReportVO dutyReportSelect(String dr_number){
		Duty_ReportVO dutyReportVO = (Duty_ReportVO) sqlSession.selectOne("dutyReportSelect",dr_number);
		return dutyReportVO;
	}
	
	@Override
	public void dutyReportCommentWrite(Duty_Report_CommentVO drcVO){
		sqlSession.insert("dutyReportCommentWrite",drcVO);
	}
	
	@Override
	public List<Duty_Report_CommentVO> selectDutyReportComment(String dr_number){
		List<Duty_Report_CommentVO> commentVOs = sqlSession.selectList("selectDutyReportComment",dr_number);
		return commentVOs;
	}
	@Override
	public void dutyReportCommentDelete(String drc_number){
		sqlSession.delete("dutyReportCommentDelete",drc_number);
	}
}
