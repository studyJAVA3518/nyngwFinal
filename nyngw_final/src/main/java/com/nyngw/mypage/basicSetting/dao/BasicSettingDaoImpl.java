package com.nyngw.mypage.basicSetting.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicSettingDaoImpl implements BasicSettingDao {
	@Autowired
	private SqlSession sqlSession;
}
