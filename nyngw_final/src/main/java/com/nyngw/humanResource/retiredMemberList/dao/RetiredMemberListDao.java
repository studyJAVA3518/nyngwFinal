package com.nyngw.humanResource.retiredMemberList.dao;

import java.util.List;

import com.nyngw.dto.JoinMemberVO;

public interface RetiredMemberListDao {
	public List<JoinMemberVO> getRetiredMemberList_RM(JoinMemberVO member);
	public void saveMember_RM(String mem_id);
	public int countTotalMember_RM(JoinMemberVO member);
}
