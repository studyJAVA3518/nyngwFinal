package com.nyngw.humanResource.joinMemberList.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.CommonJoinMemberVO;
import com.nyngw.dto.JoinMemberVO;

@Repository
public class JoinMemberListDaoImpl implements JoinMemberListDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<JoinMemberVO> getJoinMemberList_JM(JoinMemberVO member) {
		return sqlSession.selectList("getJoinMemberList_JM",member);
	}

	@Override
	public List<JoinMemberVO> getJoinMemberVOList_JM(JoinMemberVO member) {
		return sqlSession.selectList("getJoinMemberVOList_JM",member);
	}

	@Override
	public JoinMemberVO getMemberDetail_JM(String mem_id) {
		return (JoinMemberVO) sqlSession.selectOne("getMemberDetail_JM", mem_id);
	}

	@Override
	public JoinMemberVO getMemberDetailCommon_JM(String mem_id) {
		return (JoinMemberVO) sqlSession.selectOne("getMemberDetailCommon_JM", mem_id);
	}
	
	@Override
	public void modifyMemberBank(JoinMemberVO member) {
		sqlSession.update("modifyMemberBank", member);
	}

	@Override
	public void modifyMemberBankinsert(JoinMemberVO member) {
		sqlSession.insert("modifyMemberBankinsert",member);
	}
	
	@Override
	public void modifyDeleteMembter(JoinMemberVO member) {
		sqlSession.update("modifyDeleteMembter",member);
	}

	@Override
	public int countTotalJoinMember() {
		int result=0;
		if(sqlSession.selectOne("countTotalJoinMember")!=null){
			result = (int) sqlSession.selectOne("countTotalJoinMember");
		}
		return result;
	}

}


