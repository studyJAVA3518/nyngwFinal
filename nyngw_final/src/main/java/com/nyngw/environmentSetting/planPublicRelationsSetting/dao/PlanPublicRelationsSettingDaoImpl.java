package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.nyngw.dto.CompanyVO;

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
		int result=(Integer)sqlSession.update("insertCompany",vo);
		return result;
	}
	
	/**
	 * 회사 정보를 업데이트하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int updateCompanyInfo(CompanyVO vo) throws SQLException {
		int result=(Integer)sqlSession.update("updateCompany",vo);
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 회사 로고를 업데이트하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updateCompanyLogo(String company_logo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
