package com.nyngw.sharingInformation.memberInformation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.DepartmentVO;

@Repository
public class MemberInformationDaoImpl implements MemberInformationDao {
	@Autowired
	private SqlSession sqlSession;

	public List<DepartmentVO> SI_selectDepartment() {
		return sqlSession.selectList("SI_selectDepartment");
	}
}
