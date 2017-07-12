package com.nyngw.mypage.basicSetting.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BasicSettingDaoImpl implements BasicSettingDao {
	
	@Autowired
	private SqlSession sqlSession;
}
