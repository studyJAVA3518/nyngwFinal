package com.nyngw.sharingInformation.scheduleManagement.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void getAllSchedule(Model model,String sc_code_number,String mem_number) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sc_code_number", sc_code_number);
		paramMap.put("sc_mem_number", mem_number);
		List<ScheduleVO> scheduleList = scheduleManagementDao.SI_selectAllSchedule(paramMap); 
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
	
	public List<ScheduleVO> SI_selectMemberSchedule(String sc_mem_number){
		return scheduleManagementDao.SI_selectMemberSchedule(sc_mem_number);
	}
	
	public List<ScheduleVO> todayMemberSchedule(String mem_id){
		return scheduleManagementDao.todayMemberScheduling_SI(mem_id);
	}
	
}
