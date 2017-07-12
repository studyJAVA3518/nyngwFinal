package com.nyngw.humanResource.memberJoin.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.MemberVO;

@Repository
public class MemberJoinDaoImpl implements MemberJoinDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberVO idCheck_MJ(String id) {
		return (MemberVO) sqlSession.selectOne("idCheck_MJ", id);
	}

}
