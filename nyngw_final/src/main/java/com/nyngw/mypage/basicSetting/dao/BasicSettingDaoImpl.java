package com.nyngw.mypage.basicSetting.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.MemberVO;

@Repository
public class BasicSettingDaoImpl implements BasicSettingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberVO selectMember(String id){
		MemberVO member = null;
		member = (MemberVO) sqlSession.selectOne("selectMemberDetail",id);
		return member;
	}
	@Override
	public int updateMember(MemberVO vo){
		int success = 0;
		sqlSession.update("updateMemberDetail",vo);
		return success;
	}
}
