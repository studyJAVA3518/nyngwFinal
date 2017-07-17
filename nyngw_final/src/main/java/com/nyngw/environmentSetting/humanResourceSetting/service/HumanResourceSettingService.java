package com.nyngw.environmentSetting.humanResourceSetting.service;

import java.util.List;

import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Year_VacationVO;

public interface HumanResourceSettingService {

	public List<Vacation_PolicyVO> getVacationKind();

	public void updateVacation(Vacation_PolicyVO vacation);

	public void insertVacation(Vacation_PolicyVO vacation);

	public void deleteVacation(Vacation_PolicyVO vacation);

	public List<Year_VacationVO> getVacationYearSetting();

	public void setModifyVacationYearSetting(Year_VacationVO vacation);

	public void insertYearVacation(Year_VacationVO vacation);

	public void deleteYearVacation(Year_VacationVO vacation);

}
