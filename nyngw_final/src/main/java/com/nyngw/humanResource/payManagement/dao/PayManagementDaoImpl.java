package com.nyngw.humanResource.payManagement.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.Member_ViewVO;
import com.nyngw.dto.Member_payVO;
import com.nyngw.dto.Member_payViewVO;

@Repository
public class PayManagementDaoImpl implements PayManagementDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 모든 회원의 수(연도x 부서x 사람x)
	 * @return
	 */
	public int MP_selectAllCount() {
		return (int) sqlSession.selectOne("MP_selectAllCount");
	}
	
	/**
	 * 검색조건 모두 사용(연도o 부서o 사람o)
	 * @param dept_name
	 * @param mem_name
	 * @param mp_pay_date
	 * @return
	 */
	public int MP_selectAllSelectedCount(String dept_name, String mem_name, String mp_pay_date) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_name", dept_name);
		map.put("mem_name", mem_name);
		map.put("mp_pay_date", mp_pay_date);
		return (int) sqlSession.selectOne("MP_selectAllSelectedCount",map);
	}
	
	/**
	 * 검색조건 연도x 부서o 사람o
	 * @param dept_name
	 * @param mem_name
	 * @return
	 */
	public int MP_selectDeptMemberCount(String dept_name, String mem_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_name", dept_name);
		map.put("mem_name", mem_name);
		return (int) sqlSession.selectOne("MP_selectDeptMemberCount",map);
	}
	
	/**
	 * 검색조건 연도x 부서o 사람x
	 * @param dept_name
	 * @return
	 */
	public int MP_selectDeptCount(String dept_name) {
		return (int) sqlSession.selectOne("MP_selectDeptCount",dept_name);
	}
	
	/**
	 * 검색조건 연도x 부서x 사람o
	 * @param mem_name
	 * @return
	 */
	public int MP_selectMemberCount(String mem_name) {
		return (int) sqlSession.selectOne("MP_selectMemberCount",mem_name);
	}
	
	/**
	 * 검색조건 연도o 부서x 사람o
	 * @param mp_pay_date
	 * @param mem_name
	 * @return
	 */
	public int MP_selectDateMemberCount(String mp_pay_date, String mem_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mp_pay_date", mp_pay_date);
		map.put("mem_name", mem_name);
		return (int) sqlSession.selectOne("MP_selectDateMemberCount",map);
	}
	
	/**
	 * 검색조건 연도o 부서x 사람x
	 * @param mp_pay_date
	 * @return
	 */
	public int MP_selectDateCount(String mp_pay_date) {
		System.out.println(mp_pay_date);
		return (int) sqlSession.selectOne("MP_selectDateCount",mp_pay_date);
	}
	
	/**
	 * 검색조건 연도o 부서o 사람x
	 * @param mp_pay_date
	 * @param dept_name
	 * @return
	 */
	public int MP_selectDateDeptCount(String mp_pay_date, String dept_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mp_pay_date", mp_pay_date);
		map.put("dept_name", dept_name);
		return (int) sqlSession.selectOne("MP_selectDateDeptCount",map);
	}
	
	/**
	 * 회원의 급여및 휴가관련 모든 정보 조회 / 검색한 회원의 정보 검색
	 * @param mem_name
	 * @param dept_name
	 * @param mp_pay_date
	 * @param rowBounds
	 * @return
	 */
	public List<Member_payViewVO> MP_selectAllMember(String mem_name,
			String dept_name, String mp_pay_date, RowBounds rowBounds) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_name", dept_name);
		map.put("mem_name", mem_name);
		map.put("mp_pay_date", mp_pay_date);
		return sqlSession.selectList("MP_selectAllMember", map, rowBounds);
	}

	public int updateMemberPay(String mp_number, String mp_bonus, String position_number) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mp_number", mp_number);
		map.put("mp_bonus", mp_bonus);
		map.put("position_number", position_number);
		int result = sqlSession.update("MP_updateMemberPay", map);
		return result;
	}

	public List<Member_ViewVO> selectPositionList(String dept_number) throws SQLException{
		List<Member_ViewVO> posList = sqlSession.selectList("MP_selectPositionList", dept_number);
		return posList;
	}

	public List<Member_ViewVO> selectNameList(String dept_number,
			String position_number) throws SQLException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_number", dept_number);
		map.put("position_number", position_number);
		List<Member_ViewVO> nameList = sqlSession.selectList("MP_selectNameList", map);
		return nameList;
	}
	
	//해당 직급의 기본급+직급수당+식대 금액 조회
	public int selectBasicPayOne(String position_number) {
		int basicPay = 0;
		basicPay = (int) sqlSession.selectOne("MP_selectBasicPay", position_number);
		return basicPay;
	}
	
	//휴가일수 가져오기
	public int selectVacationDayDuring(String mem_number, String clickMonth) {
		int vacationDayDuring = 0;
		Map<String,String> map = new HashMap<String, String>();
		map.put("mem_number", mem_number);
		map.put("clickMonth", clickMonth);
		vacationDayDuring = (int) sqlSession.selectOne("MP_selectVacationDayDuring",map);
		return vacationDayDuring;
	}

}
