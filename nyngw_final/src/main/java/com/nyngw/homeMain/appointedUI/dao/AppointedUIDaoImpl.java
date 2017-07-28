package com.nyngw.homeMain.appointedUI.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.BirthdayVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DocumentViewVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.dto.UserInterfaceVO;
import com.nyngw.dto.UserUiVO;

@Repository
public class AppointedUIDaoImpl implements AppointedUIDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	MongoTemplate mongoTemplate;
	private static String COLLECTION_NAME = "Company";
	
	/**
	 * 회사 정보 불러오는 메서드
	 * @return CompanyVO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CompanyVO selectCompany() throws SQLException{

		CompanyVO Company = (CompanyVO) sqlSession.selectOne("selectCompany","");
		CompanyVO company=mongoTemplate.findById(0, CompanyVO.class, COLLECTION_NAME);
		
		return company;
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


	
	
	
	
	
	///////////UI설정
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


	@Override
	public void userUiInsert_UI(UserInterfaceVO ui) {
		sqlSession.insert("userUiInsert_UI", ui);
	}

	@Override
	public int userUiSelect_UI(String mem_number) {
		int result = (int) sqlSession.selectOne("userUiSelect_UI", mem_number);
		return result;
	}

	@Override
	public void userUiUpdate_UI(UserInterfaceVO ui) {
		sqlSession.insert("userUiUpdate_UI", ui);
	}

	/**
	 * 유저의 넘버를 통해서 UI사용 여부를 꺼내오는 곳
	 */
	@Override
	public UserInterfaceVO userSelectInterface(String mem_number) {
		UserInterfaceVO user = (UserInterfaceVO) sqlSession.selectOne("userSelectInterface", mem_number);
		return user;
	}

	
	/**
	 * 공지사항을 갖고오기 위한 메서드
	 */
	@Override
	public List<BoardVO> userUiNoticeMatter_UI() {
		List<BoardVO> list = sqlSession.selectList("userUiNoticeMatter_UI");
		return list;
	}

	/**
	 * 자유게시판을 갖고오기 위한 메서드 
	 */
	@Override
	public List<BoardVO> userUiBoardList_UI() {
		List<BoardVO> list = sqlSession.selectList("userUiBoardList_UI");
		return list;
	}

	/**
	 * 주소록을 갖고오기 위한 메서드
	 */
	@Override
	public List<AddressBookVO> userUiMemberAddressList_UI() {
		List<AddressBookVO> list = sqlSession.selectList("userUiMemberAddressList_UI");
		return list;
	}

	/**
	 * 생일자를 갖고오기 위한 메서드
	 */
	@Override
	public List<BirthdayVO> userUiMemberBirthdayList_UI() {
		List<BirthdayVO> list = sqlSession.selectList("userUiMemberBirthdayList_UI");
		return list;
	}

	/**
	 * 부서업무를 갖고오기 위한 메서드
	 */
	@Override
	public List<Duty_DocumentVO> userUiDepartmentList_UI(String mem_id) {
		List<Duty_DocumentVO> list = sqlSession.selectList("userUiDepartmentList_UI", mem_id);
		return list;
	}

	/**
	 * 개인업무를 갖고오기 위한 메서드
	 */
	@Override
	public List<Duty_DocumentVO> userUiPersonalBusinessList_UI(String mem_number) {
		List<Duty_DocumentVO> list = sqlSession.selectList("userUiPersonalBusinessList_UI", mem_number);
		return list;
	}


	@Override
	public List<DocumentViewVO> userUiDocumentManagerList_UI() {
		List<DocumentViewVO> list = sqlSession.selectList("userUiDocumentManagerList_UI");
		return list;
	}


	@Override
	public MiddleMenuVO selectMiddleMenuFind_UI(String mid_name) {
		MiddleMenuVO mid = (MiddleMenuVO) sqlSession.selectOne("selectMiddleMenuFind_UI", mid_name);
		return mid;
	}


	@Override
	public MiddleMenuVO selectBigMiddleMenuFind_UI(String mid_number) {
		MiddleMenuVO mid = (MiddleMenuVO) sqlSession.selectOne("selectBigMiddleMenuFind_UI", mid_number);
		return mid;
	}


	@Override
	public List<Duty_ReportVO> userUiDutyReportList_UI(String mem_id) {//받은업무보고
		List<Duty_ReportVO> list = sqlSession.selectList("userUiDutyReportList_UI",mem_id);
		return list;
	}


	@Override
	public int userUiApprovalCount(String mem_number) {
		int count = (int) sqlSession.selectOne("userUiApprovalCount",mem_number);
		return count;
	}
	
	

}
