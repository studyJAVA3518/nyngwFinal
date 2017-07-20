package com.nyngw.homeMain.appointedUI.service;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.dto.UserUiVO;

public interface AppointedUIService {
	/**
	 * 회사 정보를 조회하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public CompanyVO checkCompany() throws SQLException;

	/**
	 * 회원정보를 조회하는 메서드(세션에서 회원 ID가져다가 해당 회원 정보 뿌려줌)
	 * @param mem_id
	 * @return MemberVO
	 * @throws SQLException
	 */
	public MemberVO checkMember(String mem_id) throws SQLException;
	
	
	
	
	
	
	
	
	
	//////////////////////UI설정
	//화면1용
	public List<Map> selectBigMenu();
	public List<Map> selectMiddleMenu(String big_num);
	public void userUiInsert_UI(UserUiVO userUi, Principal principal);
}
