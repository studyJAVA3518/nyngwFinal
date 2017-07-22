package com.nyngw.homeMain.appointedUI.dao;

import java.sql.SQLException;
import java.util.List;

import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.BirthdayVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DocumentViewVO;
import com.nyngw.dto.Duty_DocumentVO;
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
	
	//유저의UI관리 테이블을 갖고오는것
	public UserInterfaceVO userSelectInterface(String mem_number);
	
	public List<BoardVO> userUiNoticeMatter_UI();//공지시항을 갖고오기위한 메서드
	public List<BoardVO> userUiBoardList_UI();//자유게시판을 갖고오기 위한 메서드
	public List<AddressBookVO> userUiMemberAddressList_UI();//주소록을 갖고오기 위한 메서드
	public List<BirthdayVO> userUiMemberBirthdayList_UI();//생일자를 갖고오기 위한 메서드
	public List<Duty_DocumentVO> userUiDepartmentList_UI(String mem_id);//부서업무를 갖고오기 위한 메서드 한달 전것까지.
	public List<Duty_DocumentVO> userUiPersonalBusinessList_UI(String mem_number);//개인업무를 갖고오기 위한 메서드
	public List<DocumentViewVO> userUiDocumentManagerList_UI();//문서조회를 갖고오기 위한 메서드
	
	//메뉴의 값을 초기화 해주기위해 쓰는 메서드
	public MiddleMenuVO selectMiddleMenuFind_UI(String mid_name);
	public MiddleMenuVO selectBigMiddleMenuFind_UI(String mid_number);
}

