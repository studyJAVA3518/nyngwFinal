package com.nyngw.homeMain.appointedUI.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.homeMain.appointedUI.dao.AppointedUIDaoImpl;

@Service
public class AppointedUIServiceImpl implements AppointedUIService {
	
	@Autowired
	AppointedUIDaoImpl appointedUIDao;
	
	@Override
	public CompanyVO checkCompany() throws SQLException {
		CompanyVO companyList = appointedUIDao.selectCompany();
		return companyList;
	}

	@Override
	public MemberVO checkMember(String mem_id) throws SQLException {
		MemberVO memberList = appointedUIDao.selectMember(mem_id);
		return memberList;
	}

	@Override
	public List<BigMenuVO> selectBigMenu() {
		List<BigMenuVO> bigMenu = appointedUIDao.selectBigMenu();
		return bigMenu;
	}

	@Override
	public List<MiddleMenuVO> selectMiddleMenu(String big_number) {
		List<MiddleMenuVO> middleMenu = appointedUIDao.selectMiddleMenu(big_number);
		return middleMenu;
	}

}
