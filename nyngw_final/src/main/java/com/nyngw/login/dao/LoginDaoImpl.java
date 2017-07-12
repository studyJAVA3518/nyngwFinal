package com.nyngw.login.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.MemberVO;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MemberVO> getMemberList() {
		return sqlSession.selectList("getMemberList");
	}

	@Override
	public MemberVO getMemberParam(Map<String, String> param) {
		return (MemberVO) sqlSession.selectOne("getMemberParam", param);
	}

	@Override
	public MemberVO getMemberSearchPwd(MemberVO member) {
		return (MemberVO) sqlSession.selectOne("getMemberSearchPwd", member);
	}

	@Override
	public void updatePwd(MemberVO member) {
		sqlSession.update("updatePwd", member);
	}
	
}
