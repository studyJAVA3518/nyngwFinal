package com.nyngw.mypage.basicSetting.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nyngw.mypage.basicSetting.dao.BasicSettingDaoImpl;

public class BasicSettingServiceImpl implements BasicSettingService {
	@Autowired
	private BasicSettingDaoImpl basicSettingDaoImpl;
	
}
