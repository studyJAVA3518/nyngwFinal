package com.nyngw.sharingInformation.noticeMatter.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeMatterDaoImpl implements NoticeMatterDao {
	@Autowired
	private SqlSession sqlSession;
}
