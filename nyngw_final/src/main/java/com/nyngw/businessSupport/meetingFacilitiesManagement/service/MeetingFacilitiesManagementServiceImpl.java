package com.nyngw.businessSupport.meetingFacilitiesManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.meetingFacilitiesManagement.dao.MeetingFacilitiesManagementDaoImpl;

@Service
public class MeetingFacilitiesManagementServiceImpl implements
		MeetingFacilitiesManagementService {
	@Autowired
	private MeetingFacilitiesManagementDaoImpl meetingFacilitiesManagementDao;
}
