package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.dto.Diligence_And_LazinessVO;
import com.nyngw.dto.Pay_PolicyVO;
import com.nyngw.dto.Pay_PolicyViewVO;
import com.nyngw.dto.Pay_kindVO;
import com.nyngw.dto.PositionVO;
import com.nyngw.dto.Work_TimeVO;

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
	
	/**
	 * 회사 부서 개수 세주는 메서드
	 * @return int
	 * @throws SQLException
	 */
	public int selectDeptCount() throws SQLException;

	public int selectPositionCount() throws SQLException;

	public ArrayList<PositionVO> selectPositionList() throws SQLException;

	public int insertPosition(PositionVO vo) throws SQLException;

	public PositionVO selectOnePosition(String position_number) throws SQLException;

	public int updatePosition(PositionVO vo) throws SQLException;

	public int deletePosition(String deletePositionNum) throws SQLException;

	public int updatePositionLevel(PositionVO vo) throws SQLException;

	public int insertDAL(List<Diligence_And_LazinessVO> dalList) throws SQLException;

	public ArrayList<Work_TimeVO> selectWorkTime() throws SQLException;

	public int updateWorkTime(Work_TimeVO vo) throws SQLException;

	public ArrayList<Pay_kindVO> selectPayKindList() throws SQLException;

	public Pay_kindVO selectPayKindOne(String pk_number) throws SQLException;

	public ArrayList<Pay_PolicyViewVO> selectPayInfoList() throws SQLException;

	public ArrayList<Pay_PolicyViewVO> selectPayInfoHourList() throws SQLException;

	public ArrayList<Pay_PolicyViewVO> selectPayInfoBasicList() throws SQLException;

	public ArrayList<Pay_PolicyViewVO> selectPayInfoPositionList() throws SQLException;

	public int insertPayKind(Pay_kindVO vo) throws SQLException;

	public int updatePayKind(Pay_kindVO vo) throws SQLException;

	public int deletePayKind(String pk_number) throws SQLException;

	public int deletePayPolicy(String pp_number) throws SQLException;

	public int updatePayPolicyPrice(Pay_PolicyVO ppOriginvo) throws SQLException;

	public Pay_PolicyViewVO selectPayPolicyViewOne(String up_pp_position_number)
			throws SQLException;


}
