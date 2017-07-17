package com.nyngw.sharingInformation.scheduleManagement.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.ScheduleVO;
import com.nyngw.sharingInformation.scheduleManagement.dao.ScheduleManagementDaoImpl;

@Service
public class ScheduleManagementServiceImpl implements ScheduleManagementService {
	@Autowired
	private ScheduleManagementDaoImpl scheduleManagementDao;

	@Autowired
	private CommonServiceImpl commonService;
	
	public void getAllSchedule(Model model,String sc_code_number) {
		List<ScheduleVO> scheduleList = scheduleManagementDao.SI_selectAllSchedule(sc_code_number); 
		model.addAttribute("scheduleList",scheduleList );
	}
	
	public void getSchedule(Model model,String sc_number,Principal principal) {
		String mem_id = principal.getName();
		MemberVO member = commonService.findMemberByMemId(mem_id);
		model.addAttribute("member",member);
		
		ScheduleVO schedule = scheduleManagementDao.SI_selectSchedule(sc_number); 
		model.addAttribute("schedule",schedule );
	}

	public void editSchedule(ScheduleVO schedule) {
		scheduleManagementDao.SI_updateSchedule(schedule);
	}
	
	public void deleteSchedule(String sc_number) {
		scheduleManagementDao.SI_deleteSchedule(sc_number);
	}
	
	public void writeSchedule(ScheduleVO schedule) {
		scheduleManagementDao.SI_insertSchedule(schedule);
	}

}
