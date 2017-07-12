package com.nyngw.sharingInformation.scheduleManagement.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleManagementDaoImpl implements ScheduleManagementDao {
	@Autowired
	private SqlSession sqlSession;
}
