package com.nyngw.businessSupport.meetingManagement.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingManagementDaoImpl implements MeetingManagementDao {
	@Autowired
	private SqlSession sqlSession;
}
