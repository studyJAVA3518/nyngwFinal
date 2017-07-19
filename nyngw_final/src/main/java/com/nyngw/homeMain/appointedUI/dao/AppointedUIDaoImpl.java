package com.nyngw.homeMain.appointedUI.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;

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
	 * 회원 정보를 조회하는 메서드
	 @return MemberVO
	 */
	@Override
	public MemberVO selectMember(String mem_id) throws SQLException {
		MemberVO member
			= (MemberVO) sqlSession.selectOne("selectMember",mem_id);
		return member;
	}


	@Override
	public List<BigMenuVO> selectBigMenu() {
		List<BigMenuVO> bigMenu = sqlSession.selectList("selectBigMenu");
		return bigMenu;
	}


	@Override
	public List<MiddleMenuVO> selectMiddleMenu(String big_number) {
		List<MiddleMenuVO> middleMenu = sqlSession.selectList("selectMiddleMenu",big_number);
		return middleMenu;
	}
	
	

}
