package com.nyngw.humanResource.memberJoin.service;

import java.sql.SQLException;

import org.springframework.ui.Model;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;

public interface MemberJoinService {
	
	public MemberVO idCheck(String id);
	public int joinMember(JoinMemberVO joinMember);
	
	//지호-메서드 추가
	public void viewMjmInfo(Model model) throws SQLException;
	
}
