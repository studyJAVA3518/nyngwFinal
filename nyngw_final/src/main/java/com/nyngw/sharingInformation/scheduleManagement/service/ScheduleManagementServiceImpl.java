package com.nyngw.sharingInformation.scheduleManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.sharingInformation.scheduleManagement.dao.ScheduleManagementDaoImpl;

@Service
public class ScheduleManagementServiceImpl implements ScheduleManagementService {
	@Autowired
	private ScheduleManagementDaoImpl scheduleManagementDaoImpl;
}
