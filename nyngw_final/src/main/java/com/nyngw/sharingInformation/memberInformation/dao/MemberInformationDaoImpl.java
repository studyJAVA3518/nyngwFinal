package com.nyngw.sharingInformation.memberInformation.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.BirthdayVO;
import com.nyngw.dto.DepartmentVO;

@Repository
public class MemberInformationDaoImpl implements MemberInformationDao {
	@Autowired
	private SqlSession sqlSession;

	//부서의 수
	public List<DepartmentVO> SI_selectDepartment() {
		return sqlSession.selectList("SI_selectDepartment");
	}
	
	//검색한 회원의 수
	public int SI_selectMemberCount(String mem_name) {
		return (int) sqlSession.selectOne("SI_selectMemberCount",mem_name);
	}
	
	//모든 회원의 수
	public int SI_selectAllMemberCount() {
		return (int) sqlSession.selectOne("SI_selectAllMemberCount");
	}
	
	//검색한 회원의 정보 검색
	public List<AddressBookVO> SI_selectAllMember(String mem_name, RowBounds rowBounds) {
		return sqlSession.selectList("SI_selectAllMember",mem_name,rowBounds);
	}
	
	//생일인 회원의 수
	public int SI_selectBirthdayMemberCount(String month) {
		return (int) sqlSession.selectOne("SI_selectBirthdayMemberCount",month);
	}
	
	//생일인 회원 리스트
	public List<BirthdayVO> SI_selectBirthdayMember(String month, RowBounds rowBounds) {
		return sqlSession.selectList("SI_selectBirthdayMember",month,rowBounds);
	}
}
