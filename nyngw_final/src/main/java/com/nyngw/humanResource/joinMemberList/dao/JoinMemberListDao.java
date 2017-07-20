package com.nyngw.humanResource.joinMemberList.dao;

import java.util.List;

import com.nyngw.dto.CommonJoinMemberVO;
import com.nyngw.dto.JoinMemberVO;

public interface JoinMemberListDao {
	public List<JoinMemberVO> getJoinMemberList_JM(JoinMemberVO member);
	public List<JoinMemberVO> getJoinMemberVOList_JM(JoinMemberVO member);
	public JoinMemberVO getMemberDetail_JM(String mem_id);
	public JoinMemberVO getMemberDetailCommon_JM(String mem_id);
	public void modifyMemberBank(JoinMemberVO member);
	public void modifyMemberBankinsert(JoinMemberVO member);
	public void modifyDeleteMembter(JoinMemberVO member);
	public int countTotalJoinMember();
	
	public void modifyMemberPosDeps(JoinMemberVO member);
}
