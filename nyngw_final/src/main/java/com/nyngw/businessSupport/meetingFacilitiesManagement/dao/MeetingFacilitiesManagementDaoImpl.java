package com.nyngw.businessSupport.meetingFacilitiesManagement.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingFacilitiesManagementDaoImpl implements
		MeetingFacilitiesManagementDao {
	@Autowired
	private SqlSession sqlSession;
}
