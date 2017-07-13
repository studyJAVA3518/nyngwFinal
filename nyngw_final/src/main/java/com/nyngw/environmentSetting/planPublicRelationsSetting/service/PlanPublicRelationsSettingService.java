package com.nyngw.environmentSetting.planPublicRelationsSetting.service;

import java.sql.SQLException;

import com.nyngw.dto.CompanyVO;

public interface PlanPublicRelationsSettingService {
	
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public int companyJoin(CompanyVO vo) throws SQLException;
	
	/**
	 * 회사 정보를 수정하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public int companyModify(CompanyVO vo) throws SQLException;
	
	/**
	 * 회사 로고를 입력하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	public int joinCompanyLogo(String company_logo) throws SQLException;
	
	/**
	 * 회사 로고를 업데이트하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	public int modifyCompanyLogo(String company_logo) throws SQLException;
}
