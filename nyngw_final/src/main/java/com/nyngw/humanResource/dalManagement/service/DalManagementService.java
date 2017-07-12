package com.nyngw.humanResource.dalManagement.service;

import java.util.List;

import com.nyngw.dto.FN_GETDALCNT;
import com.nyngw.dto.Member_ViewVO;

public interface DalManagementService {
	
	public List<Member_ViewVO> searchContent(Member_ViewVO dil);
	public List<FN_GETDALCNT> positionAllCount(FN_GETDALCNT fncnt);
	
}
