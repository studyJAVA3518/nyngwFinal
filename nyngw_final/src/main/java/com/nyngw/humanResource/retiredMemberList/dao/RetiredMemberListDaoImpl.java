package com.nyngw.humanResource.retiredMemberList.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.JoinMemberVO;

@Repository
public class RetiredMemberListDaoImpl implements RetiredMemberListDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<JoinMemberVO> getRetiredMemberList_RM(JoinMemberVO member) {
		return sqlSession.selectList("getRetiredMemberList_RM", member);
	}
	
	@Override
	public void saveMember_RM(String mem_id) {
		sqlSession.update("saveMember_RM", mem_id);
	}

	@Override
	public int countTotalMember_RM(JoinMemberVO member) {
		int result=0;
		if(sqlSession.selectOne("countTotalMember_RM", member)!=null){
			result=(int) sqlSession.selectOne("countTotalMember_RM", member);
		}
		return result;
	}

}
