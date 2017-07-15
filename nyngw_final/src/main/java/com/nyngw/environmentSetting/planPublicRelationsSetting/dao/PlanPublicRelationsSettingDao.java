package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DepartmentViewVO;

public interface PlanPublicRelationsSettingDao {
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int insertCompanyInfo(CompanyVO vo) throws SQLException;
	
	/**
	 * 회사 정보를 업데이트하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int updateCompanyInfo(CompanyVO vo) throws SQLException;
	
	/**
	 * 회사 로고를 입력하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	public int insertCompanyLogo(String company_logo) throws SQLException;
	
	/**
	 * 회사 로고를 업데이트하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	public int updateCompanyLogo(String company_logo, String company_number) throws SQLException;
	
	/**
	 * 회사의 직급에 대한 모든 정보를 조회하는 메서드
	 * @return DepartmentViewVO
	 * @throws SQLException
	 */
	public ArrayList<DepartmentViewVO> selectDepartmentView() throws SQLException;
	
	/**
	 * 상위 직급의 멤버 일부 정보를 조회하는 메서드
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<DepartmentViewVO> selectUpperMember() throws SQLException;
	
	/**
	 * 부서 정보 등록하는 메서드
	 * @param dvVO
	 * @return int
	 * @throws SQLException
	 */
	public int insertDepartment(DepartmentVO dvo) throws SQLException;

	/**
	 * 등록하려는 부모부서의 레벨 가져오는 메서드
	 * @param dvVO
	 * @return int
	 * @throws SQLException
	 */
	public long selectDeptLevel(String dept_number) throws SQLException;
	
	/**
	 * 하나 부서의 정보를 가져오는 메서드
	 * @param dept_id
	 * @return DepartmentVO
	 * @throws SQLException
	 */
	DepartmentVO selectDepartOne(String dept_number) throws SQLException;
	
	/**
	 * 회사 부서 수정하는 메서드
	 * @param String deleteNumber
	 * @return int
	 * @throws SQLException
	 */
	int updateDepartment(DepartmentVO dvo) throws SQLException;

	/**
	 * 회사 부서 삭제하는 메서드
	 * @param String deleteNumber
	 * @return int
	 * @throws SQLException
	 */
	int deleteDepartment(String deleteDeptNum) throws SQLException;

	
}
