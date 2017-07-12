package com.nyngw.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.MemberVO;
import com.nyngw.login.dao.LoginDaoImpl;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDaoImpl loginDao;

	@Override
	public List<MemberVO> getMemberList(){
		return loginDao.getMemberList();
	}
	
	@Override
	public MemberVO getMemberParam(Map<String, String> param) {
		return loginDao.getMemberParam(param);
	}

	@Override
	public MemberVO getMemberSearchPwd(MemberVO member) {
		return loginDao.getMemberSearchPwd(member);
	}

	@Override
	public void updatePwd(MemberVO member) {
		loginDao.updatePwd(member);
	}
}
