package com.nyngw.environmentSetting.humanResourceSetting.dao;

import java.util.List;

import com.nyngw.dto.Vacation_PolicyVO;
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

}
