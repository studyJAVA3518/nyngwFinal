package com.nyngw.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.MemberVO;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Common_CodeVO selectCodeNameByDocNumber(String ea_doc_number) {
		return (Common_CodeVO) sqlSession.selectOne("selectCodeNameByDocNumber", ea_doc_number);
	}

	public MemberVO common_selectMember(String mem_id) {
		return (MemberVO) sqlSession.selectOne("common_selectMember", mem_id);
	}

}
