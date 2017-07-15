package com.nyngw.sharingInformation.memberInformation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.DepartmentVO;
import com.nyngw.sharingInformation.memberInformation.dao.MemberInformationDaoImpl;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {
	@Autowired
	private MemberInformationDaoImpl memberInformationDao;

	public List<Map> getAllDepartment() {
		List<DepartmentVO> departmentList = memberInformationDao.SI_selectDepartment();

		List<Map> treeJsonList = new ArrayList<Map>();
		
		for (DepartmentVO department : departmentList) {
			Map<String,String> treejsonMap = new HashMap<String,String>();
			treejsonMap.put("label", department.getDept_name());
			treejsonMap.put("itemId", department.getDept_number());
			treejsonMap.put("parentId", department.getDept_parents());
			
			treeJsonList.add(treejsonMap);
		}
		return treeJsonList;
	}
}
