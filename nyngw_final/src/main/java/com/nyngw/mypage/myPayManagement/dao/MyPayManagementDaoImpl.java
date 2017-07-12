package com.nyngw.mypage.myPayManagement.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class MyPayManagementDaoImpl implements MyPayManagementDao {
	@Autowired
	private SqlSession sqlSession;
}
