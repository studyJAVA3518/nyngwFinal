package com.nyngw.environmentSetting.humanResourceSetting.dao;

import java.util.List;

import com.nyngw.dto.Member_Carear_VacationVO;
import com.nyngw.dto.VacationVO;
import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Vacation_TotalVO;
import com.nyngw.dto.Year_VacationVO;

public interface HumanResourceSettingDao {

	public List<Vacation_PolicyVO> getVactionKind_HRS();

	public void updateDateVacation_HRS(Vacation_PolicyVO vacation);

	public void insertVacation_HRS(Vacation_PolicyVO vacation);

	public void deleteVacation_HRS(Vacation_PolicyVO vacation);

	public List<Year_VacationVO> getVacationYearSetting_HRS();

	public void setModifyVacationYearSetting_HRS(Year_VacationVO vacation);

	public void setYearVacation_HRS(Year_VacationVO vacation);

	public void deleteYearVacation_HRS(Year_VacationVO vacation);

	public List<Member_Carear_VacationVO> getCarearVacationSet_HRS(Member_Carear_VacationVO mcv);

	public List<Year_VacationVO> getCarearVacationAddSet_HRS(String str);

	public int countCarearVaction_HRS(Member_Carear_VacationVO mcv);

	public int getcountUsingVacation_HRS(Member_Carear_VacationVO vo);

	public Member_Carear_VacationVO getMemberMCV_HRS(String mem_number);

	public List<Vacation_TotalVO> getVacationList_HRS(Member_Carear_VacationVO member);

}
