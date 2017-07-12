package com.nyngw.homeMain.appointedUI.service;

import java.sql.SQLException;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;

public interface AppointedUIService {
	/**
	 * 회사 정보를 조회하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public CompanyVO checkCompany() throws SQLException;

	/**
	 * 회사 정보를 조회하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public int companyModify(CompanyVO vo) throws SQLException;
	
	/**
	 * 회원정보를 조회하는 메서드(세션에서 회원 ID가져다가 해당 회원 정보 뿌려줌)
	 * @param mem_id
	 * @return MemberVO
	 * @throws SQLException
	 */
	public MemberVO checkMember(String mem_id) throws SQLException;
}
