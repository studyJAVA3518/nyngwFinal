package com.nyngw.humanResource.joinMemberList.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;

@Repository
public class JoinMemberListDaoImpl implements JoinMemberListDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<JoinMemberVO> getJoinMemberList_JM() {
		return sqlSession.selectList("getJoinMemberList_JM");
	}

}
