package com.nyngw.businessSupport.dutyDocument.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DutyDocumentDaoImpl implements DutyDocumentDao {
	@Autowired
	private SqlSession sqlSession;
}
