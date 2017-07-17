package com.nyngw.humanResource.retiredMemberList.service;

import java.util.List;

import com.nyngw.dto.JoinMemberVO;

public interface RetiredMemberListService {
	public List<JoinMemberVO> getRetiredMemberList(JoinMemberVO member);
	public void saveMember(String mem_id);
	public int countTotalMember(JoinMemberVO member);
}
