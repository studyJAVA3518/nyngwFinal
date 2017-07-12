package com.nyngw.businessSupport.meetingManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.meetingManagement.dao.MeetingManagementDaoImpl;

@Service
public class MeetingManagementServiceImpl implements MeetingManagementService {
	@Autowired
	private MeetingManagementDaoImpl meetingManagementDao;
}
