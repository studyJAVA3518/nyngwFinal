package com.nyngw.homeMain.appointedUI.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
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
	public int companyModify(CompanyVO vo) throws SQLException {
		int result = appointedUIDao.updateCompany(vo);
		return result;
	}

	@Override
	public MemberVO checkMember(String mem_id) throws SQLException {
		MemberVO memberList = appointedUIDao.selectMember(mem_id);
		return memberList;
	}

}
