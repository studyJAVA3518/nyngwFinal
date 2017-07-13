package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;

import com.nyngw.dto.CompanyVO;

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
	public int updateCompanyLogo(String company_logo) throws SQLException;
	
}
