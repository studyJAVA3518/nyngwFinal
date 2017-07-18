package com.nyngw.environmentSetting.humanResourceSetting.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Member_Carear_VacationVO;
import com.nyngw.dto.VacationVO;
import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Vacation_TotalVO;
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

	@Override
	public List<Member_Carear_VacationVO> getCarearVacationSet_HRS(Member_Carear_VacationVO mcv) {
		return sqlSession.selectList("getCarearVacationSet_HRS",mcv);
	}

	@Override
	public List<Year_VacationVO> getCarearVacationAddSet_HRS(String str) {
		return sqlSession.selectList("getCarearVacationAddSet_HRS", str);
	}

	@Override
	public int countCarearVaction_HRS(Member_Carear_VacationVO mcv) {
		int result=0;
		if(sqlSession.selectOne("countCarearVaction_HRS", mcv)!=null){
			result = (int) sqlSession.selectOne("countCarearVaction_HRS", mcv);
		}
		return result;
	}

	@Override
	public int getcountUsingVacation_HRS(Member_Carear_VacationVO vo) {
		int result=0;
		if(sqlSession.selectOne("getcountUsingVacation_HRS", vo)!=null){
			result = (int) sqlSession.selectOne("getcountUsingVacation_HRS", vo);
		}
		return result;
	}

	@Override
	public Member_Carear_VacationVO getMemberMCV_HRS(String mem_number) {
		return (Member_Carear_VacationVO) sqlSession.selectOne("getMemberMCV_HRS", mem_number);
	}

	@Override
	public List<Vacation_TotalVO> getVacationList_HRS(Member_Carear_VacationVO member) {
		return sqlSession.selectList("getVacationList_HRS", member);
	}

}
