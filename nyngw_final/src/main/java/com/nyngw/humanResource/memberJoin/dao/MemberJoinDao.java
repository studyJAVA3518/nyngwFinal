package com.nyngw.humanResource.memberJoin.dao;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;

public interface MemberJoinDao {
	public MemberVO idCheck_MJ(String id);
	public int joinMember_JM(JoinMemberVO joinMember);
	public int joinMemberMDI_JM(JoinMemberVO joinMember);
}
