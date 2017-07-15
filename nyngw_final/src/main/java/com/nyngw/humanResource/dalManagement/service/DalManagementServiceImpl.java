package com.nyngw.humanResource.dalManagement.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.FN_GETDALCNT;
import com.nyngw.dto.Member_ViewVO;
import com.nyngw.dto.Paging;
import com.nyngw.humanResource.dalManagement.dao.DalManagementDaoImpl;

@Service
public class DalManagementServiceImpl implements DalManagementService {
	
	@Autowired
	DalManagementDaoImpl dalManagementDao;
	
	@Override
	public List<Member_ViewVO> searchContent(Member_ViewVO dil) {
		return dalManagementDao.searchContent(dil);
	}

	@Override
	public List<FN_GETDALCNT> positionAllCount(FN_GETDALCNT fncnt) {
		return dalManagementDao.dateSearchList_HRDS(fncnt);
	}

	@Override
	public int countContent(Member_ViewVO dil) {
		return dalManagementDao.countContent(dil);
	}
	
	
	
}
