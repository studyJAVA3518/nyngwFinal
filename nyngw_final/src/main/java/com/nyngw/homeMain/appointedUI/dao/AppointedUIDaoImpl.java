package com.nyngw.homeMain.appointedUI.dao;

import java.sql.SQLException;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;

@Repository
public class AppointedUIDaoImpl implements AppointedUIDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	/**
	 * 회사 정보 불러오는 메서드
	 * @return CompanyVO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CompanyVO selectCompany() throws SQLException{

		CompanyVO Company
			= (CompanyVO) sqlSession.selectOne("selectCompany","");
		return Company;
	}
	
	/**
	 * 회사 정보 수정하는 메서드
	 * @return int
	 */
	@Override
	public int updateCompany(CompanyVO vo) throws SQLException{
		int result=(Integer)sqlSession.update("updateCompany",vo);
		return result;
	}
	
	/**
	 * 회원 정보를 조회하는 메서드
	 @return MemberVO
	 */
	@Override
	public MemberVO selectMember(String mem_id) throws SQLException {
		MemberVO member
			= (MemberVO) sqlSession.selectOne("selectMember",mem_id);
		return member;
	}
	
	

}
