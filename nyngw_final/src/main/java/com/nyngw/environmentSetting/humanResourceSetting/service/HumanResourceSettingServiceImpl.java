package com.nyngw.environmentSetting.humanResourceSetting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Year_VacationVO;
import com.nyngw.environmentSetting.humanResourceSetting.dao.HumanResourceSettingDaoImpl;

@Service
public class HumanResourceSettingServiceImpl implements
		HumanResourceSettingService {

	@Autowired
	private HumanResourceSettingDaoImpl humanResourceSettingDao;
	
	@Override
	public List<Vacation_PolicyVO> getVacationKind() {
		return humanResourceSettingDao.getVactionKind_HRS();
	}

	@Override
	public void updateVacation(Vacation_PolicyVO vacation) {
		humanResourceSettingDao.updateDateVacation_HRS(vacation);
	}

	@Override
	public void insertVacation(Vacation_PolicyVO vacation) {
		humanResourceSettingDao.insertVacation_HRS(vacation);
	}

	@Override
	public void deleteVacation(Vacation_PolicyVO vacation) {
		humanResourceSettingDao.deleteVacation_HRS(vacation);
	}

	@Override
	public List<Year_VacationVO> getVacationYearSetting() {
		return humanResourceSettingDao.getVacationYearSetting_HRS();
	}

	@Override
	public void setModifyVacationYearSetting(Year_VacationVO vacation) {
		humanResourceSettingDao.setModifyVacationYearSetting_HRS(vacation);
	}

	@Override
	public void insertYearVacation(Year_VacationVO vacation) {
		humanResourceSettingDao.setYearVacation_HRS(vacation);
	}

	@Override
	public void deleteYearVacation(Year_VacationVO vacation) {
		humanResourceSettingDao.deleteYearVacation_HRS(vacation);
	}

}
