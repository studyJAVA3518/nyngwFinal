package com.nyngw.businessSupport.dutyStatusReport.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DutyStatusReportDaoImpl implements DutyStatusReportDao {
	@Autowired
	private SqlSession sqlSession;
}
