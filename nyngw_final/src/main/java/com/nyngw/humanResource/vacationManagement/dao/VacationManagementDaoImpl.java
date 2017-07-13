package com.nyngw.humanResource.vacationManagement.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Member_Vacation_FN_GETCNTVO;

@Repository
public class VacationManagementDaoImpl implements VacationManagementDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Member_Vacation_FN_GETCNTVO> getMemberVactionList_VM(Member_Vacation_FN_GETCNTVO mvfg) {
		return sqlSession.selectList("getMemberVactionList_VM",mvfg);
	}

	@Override
	public List<Member_Vacation_FN_GETCNTVO> getDeptVactionList_VM(Member_Vacation_FN_GETCNTVO mvfg) {
		return sqlSession.selectList("getDeptVactionList_VM",mvfg);
	}

}
