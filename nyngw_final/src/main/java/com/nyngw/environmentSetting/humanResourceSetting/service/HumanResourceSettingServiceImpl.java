package com.nyngw.environmentSetting.humanResourceSetting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Member_Carear_VacationVO;
import com.nyngw.dto.VacationVO;
import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Vacation_TotalVO;
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

	@Override
	public List<Member_Carear_VacationVO> getCarearVacationSet(Member_Carear_VacationVO mcv) {
		
		List<Member_Carear_VacationVO> search = humanResourceSettingDao.getCarearVacationSet_HRS(mcv);
		List<Member_Carear_VacationVO> result = new ArrayList<Member_Carear_VacationVO>();
		Member_Carear_VacationVO vo=null;
		for (int i = 0; i < search.size(); i++) {
			vo = search.get(i);
			vo.setYear(String.valueOf(1900+new Date().getYear()));
			if(vo.getMem_carear()==null){
				vo.setMem_carear(String.valueOf(1900+new Date().getYear()));
			}
			vo.setMem_carear(String.valueOf(1900+new Date().getYear()-Integer.valueOf(vo.getMem_carear())));
			List<Year_VacationVO> yvyearList = humanResourceSettingDao.getCarearVacationAddSet_HRS(vo.getMem_carear());
			
			String vp_totalday = null;
			if(yvyearList.size()>0){
				vp_totalday = String.valueOf(Integer.valueOf(vo.getVp_totalday())+Integer.valueOf(yvyearList.get(yvyearList.size()-1).getYv_vacation_day()));
			}else{
				vp_totalday = vo.getVp_totalday();
			}
			
			
			int use_vacation = humanResourceSettingDao.getcountUsingVacation_HRS(vo);
			
			vo.setNouse_vacation(Integer.valueOf(vp_totalday)-use_vacation);
			vo.setUse_vacation(use_vacation);
			vo.setVp_totalday(vp_totalday);
			result.add(vo);
		}
		return result;
	}

	@Override
	public int countContent(Member_Carear_VacationVO mcv) {
		return humanResourceSettingDao.countCarearVaction_HRS(mcv);
	}

	@Override
	public Member_Carear_VacationVO getMember(String mem_number) {
		Member_Carear_VacationVO member = humanResourceSettingDao.getMemberMCV_HRS(mem_number);
		
		member.setYear(String.valueOf(1900+new Date().getYear()));
		member.setMem_carear(String.valueOf(1900+new Date().getYear()-Integer.valueOf(member.getMem_carear())));
		List<Year_VacationVO> yvyearList = humanResourceSettingDao.getCarearVacationAddSet_HRS(member.getMem_carear());
		String vp_totalday = String.valueOf(Integer.valueOf(member.getVp_totalday())+Integer.valueOf(yvyearList.get(yvyearList.size()-1).getYv_vacation_day()));
		member.setVp_totalday(vp_totalday);
		int use_vacation = humanResourceSettingDao.getcountUsingVacation_HRS(member);
		member.setUse_vacation(use_vacation);
		member.setNouse_vacation(Integer.valueOf(vp_totalday)-use_vacation);
		
		
		return member;
	}

	@Override
	public List<Vacation_TotalVO> getVacationList(Member_Carear_VacationVO member) {
		return humanResourceSettingDao.getVacationList_HRS(member);
	}

}
