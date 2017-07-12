package com.nyngw.login.service;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.MemberVO;

public interface LoginService {
	
	public List<MemberVO> getMemberList();
	public MemberVO getMemberParam(Map<String, String> param);
	public MemberVO getMemberSearchPwd(MemberVO member);
	public void updatePwd(MemberVO member);
	
}
