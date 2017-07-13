package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.CompanyVO;

@Repository
public class PlanPublicRelationsSettingDaoImpl implements
		PlanPublicRelationsSettingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int insertCompanyInfo(CompanyVO vo) throws SQLException {
		int result=(Integer)sqlSession.update("insertCompanyInfo",vo);
		return result;
	}
	
	/**
	 * 회사 정보를 업데이트하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int updateCompanyInfo(CompanyVO vo) throws SQLException {
		int result=(Integer)sqlSession.update("updateCompanyInfo",vo);
		return result;
	}
	
	/**
	 * 회사 로고를 입력하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int insertCompanyLogo(String company_logo) throws SQLException {
		int result=(Integer)sqlSession.update("inserttCompanyInfo",company_logo);
		return result;
	}
	
	/**
	 * 회사 로고를 업데이트하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updateCompanyLogo(String company_logo, String company_number) throws SQLException {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("company_logo", company_logo);
		paramMap.put("company_number", company_number);
		System.out.println("파람맵:"+paramMap);
		int result=(Integer)sqlSession.update("updateCompanyLogo",paramMap);
		return result;
	}
}
