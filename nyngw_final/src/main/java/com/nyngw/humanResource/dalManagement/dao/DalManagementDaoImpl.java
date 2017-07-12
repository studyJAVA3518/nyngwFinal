package com.nyngw.humanResource.dalManagement.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.FN_GETDALCNT;
import com.nyngw.dto.Member_ViewVO;

@Repository
public class DalManagementDaoImpl implements DalManagementDao {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Member_ViewVO> searchContent(Member_ViewVO dil) {
		return sqlSession.selectList("searchContent", dil);
	}

	@Override
	public List<FN_GETDALCNT> dateSearchList_HRDS(FN_GETDALCNT fncnt) {
		return sqlSession.selectList("dateSearchList_HRDS", fncnt);
	}


}
