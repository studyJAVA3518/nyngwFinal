package com.nyngw.humanResource.memberJoin.service;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;

public interface MemberJoinService {
	
	public MemberVO idCheck(String id);
	public int joinMember(JoinMemberVO joinMember);
	
}
