package com.nyngw.environmentSetting.humanResourceSetting.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Year_VacationVO;

@Repository
public class HumanResourceSettingDaoImpl implements HumanResourceSettingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Vacation_PolicyVO> getVactionKind_HRS() {
		return sqlSession.selectList("getVactionKind_HRS");
	}

	@Override
	public void updateDateVacation_HRS(Vacation_PolicyVO vacation) {
		sqlSession.update("updateDateVacation_HRS", vacation);
	}

	@Override
	public void insertVacation_HRS(Vacation_PolicyVO vacation) {
		sqlSession.insert("insertVacation_HRS", vacation);
	}

	@Override
	public void deleteVacation_HRS(Vacation_PolicyVO vacation) {
		sqlSession.delete("deleteVacation_HRS", vacation);
	}

	@Override
	public List<Year_VacationVO> getVacationYearSetting_HRS() {
		return sqlSession.selectList("getVacationYearSetting_HRS");
	}

	@Override
	public void setModifyVacationYearSetting_HRS(Year_VacationVO vacation) {
		sqlSession.update("setModifyVacationYearSetting_HRS", vacation);
	}

	@Override
	public void setYearVacation_HRS(Year_VacationVO vacation) {
		sqlSession.insert("setYearVacation_HRS", vacation);
	}

	@Override
	public void deleteYearVacation_HRS(Year_VacationVO vacation) {
		sqlSession.delete("deleteYearVacation_HRS", vacation);
	}

}
