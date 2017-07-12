package com.nyngw.businessSupport.dutyReport.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.businessSupport.dutyReport.service.DutyReportServiceImpl;

@Repository
public class DutyReportDaoImpl implements DutyReportDao {
	@Autowired
	private SqlSession sqlSession;
}
