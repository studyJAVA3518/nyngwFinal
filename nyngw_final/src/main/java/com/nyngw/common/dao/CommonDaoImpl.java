package com.nyngw.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Common_CodeVO;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Common_CodeVO selectCodeNameByDocNumber(String ea_doc_number) {
		return (Common_CodeVO) sqlSession.selectOne("selectCodeNameByDocNumber", ea_doc_number);
	}

}
