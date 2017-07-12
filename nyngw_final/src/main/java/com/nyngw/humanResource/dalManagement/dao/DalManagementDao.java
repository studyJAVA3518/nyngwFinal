package com.nyngw.humanResource.dalManagement.dao;

import java.util.List;

import com.nyngw.dto.FN_GETDALCNT;
import com.nyngw.dto.Member_ViewVO;

public interface DalManagementDao {
	
	public List<Member_ViewVO> searchContent(Member_ViewVO dil);
	public List<FN_GETDALCNT> dateSearchList_HRDS(FN_GETDALCNT fncnt);
	
}
