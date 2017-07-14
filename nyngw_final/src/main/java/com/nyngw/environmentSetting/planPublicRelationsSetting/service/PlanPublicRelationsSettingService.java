package com.nyngw.environmentSetting.planPublicRelationsSetting.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentViewVO;

public interface PlanPublicRelationsSettingService {
	
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public int joinCompanyInfo(CompanyVO vo) throws SQLException;
	
	/**
	 * 회사 정보를 수정하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	public int modifyCompanyInfo(CompanyVO vo) throws SQLException;
	
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
	public int modifyCompanyLogo(String company_logo, String company_number) throws SQLException;
	
	/**
	 * 직급 정보를 모두 불러오는 메서드
	 * @return 
	 * @throws SQLException
	 */
	public void viewDeptInfo(Model model) throws SQLException;
	
}
