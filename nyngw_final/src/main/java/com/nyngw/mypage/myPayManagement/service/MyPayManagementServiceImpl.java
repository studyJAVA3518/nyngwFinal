package com.nyngw.mypage.myPayManagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nyngw.mypage.myPayManagement.dao.MyPayManagementDaoImpl;

public class MyPayManagementServiceImpl implements MyPayManagementService {
	@Autowired
	private MyPayManagementDaoImpl myPayManagementDaoImpl;
}
