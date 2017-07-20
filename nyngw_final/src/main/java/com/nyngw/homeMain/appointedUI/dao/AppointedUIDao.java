package com.nyngw.homeMain.appointedUI.dao;

import java.sql.SQLException;
import java.util.List;

import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.dto.UserInterfaceVO;
import com.nyngw.dto.UserUiVO;



public interface AppointedUIDao {
	/**
	 * 회사 정보 불러오는 메서드
	 * @return CompanyVO
	 */
	public CompanyVO selectCompany() throws SQLException;

	/**
	 * 회원 정보를 조회하는 메서드
	 */
	public MemberVO selectMember(String mem_id) throws SQLException;
	
	
	
	
	/////////////////////UI설정
	public List<BigMenuVO> selectBigMenu();
	public List<MiddleMenuVO> selectMiddleMenu(String select);
	public void userUiInsert_UI(UserInterfaceVO ui);
	public int userUiSelect_UI(String mem_number);
	public void userUiUpdate_UI(UserInterfaceVO ui);
}
