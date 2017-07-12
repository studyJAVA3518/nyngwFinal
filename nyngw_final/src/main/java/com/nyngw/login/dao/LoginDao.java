package com.nyngw.login.dao;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.MemberVO;

public interface LoginDao {
	
	public List<MemberVO> getMemberList();
	public MemberVO getMemberParam(Map<String, String> param);
	public MemberVO getMemberSearchPwd(MemberVO member);
	public void updatePwd(MemberVO member);
	
}
