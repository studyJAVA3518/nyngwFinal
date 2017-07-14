package com.nyngw.humanResource.memberJoin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;

@Repository
public class MemberJoinDaoImpl implements MemberJoinDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberVO idCheck_MJ(String id) {
		return (MemberVO) sqlSession.selectOne("idCheck_MJ", id);
	}

	@Override
	public int joinMember_JM(JoinMemberVO joinMember) {
		return sqlSession.insert("joinMember_JM", joinMember);
	}

	@Override
	public int joinMemberMDI_JM(JoinMemberVO joinMember) {
		return sqlSession.insert("joinMemberMDI_JM", joinMember);
	}

}
