package com.nyngw.mypage.basicSetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.mypage.basicSetting.dao.BasicSettingDaoImpl;
@Service
public class BasicSettingServiceImpl implements BasicSettingService {
	@Autowired
	private BasicSettingDaoImpl basicSettingDaoImpl;
	
}
