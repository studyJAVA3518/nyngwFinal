package com.nyngw.mypage.basicSetting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.MemberVO;
import com.nyngw.mypage.basicSetting.dao.BasicSettingDaoImpl;

@Service
public class BasicSettingServiceImpl implements BasicSettingService {
	@Autowired
	private BasicSettingDaoImpl basicSettingDaoImpl;
	
	@Override
	public MemberVO selectMember(String id){
		MemberVO member = basicSettingDaoImpl.selectMember(id);
		return member;
	}
	@Override
	public int updateMember(MemberVO vo){
		int success= 0;
		success = (Integer)basicSettingDaoImpl.updateMember(vo);
		return success;
	}
}
