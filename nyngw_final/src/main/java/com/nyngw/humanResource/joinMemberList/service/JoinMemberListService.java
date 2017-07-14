package com.nyngw.humanResource.joinMemberList.service;

import java.util.List;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;

public interface JoinMemberListService {
	
	public List<JoinMemberVO> getJoinMemberList();
	public JoinMemberVO getMemberDetail(String mem_id);
	public int modifyMember(JoinMemberVO member);
	public void modifyDeleteMember(JoinMemberVO member);
	
}
