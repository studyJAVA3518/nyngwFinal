package com.nyngw.humanResource.vacationManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Member_Vacation_FN_GETCNTVO;
import com.nyngw.humanResource.vacationManagement.dao.VacationManagementDaoImpl;

@Service
public class VacationManagementServiceImpl implements VacationManagementService {

	@Autowired
	private VacationManagementDaoImpl vactionMangementDao;
	
	@Override
	public List<Member_Vacation_FN_GETCNTVO> getMemberVacation(Member_Vacation_FN_GETCNTVO mvfg) {
		return vactionMangementDao.getMemberVactionList_VM(mvfg);
	}

	@Override
	public List<Member_Vacation_FN_GETCNTVO> getDeptVaction(Member_Vacation_FN_GETCNTVO mvfg) {
		return vactionMangementDao.getDeptVactionList_VM(mvfg);
	}

}

