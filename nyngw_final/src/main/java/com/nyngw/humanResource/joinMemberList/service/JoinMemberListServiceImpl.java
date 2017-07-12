package com.nyngw.humanResource.joinMemberList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.humanResource.joinMemberList.dao.JoinMemberListDaoImpl;

@Service
public class JoinMemberListServiceImpl implements JoinMemberListService {
	
	@Autowired
	private JoinMemberListDaoImpl joinMemberListDao; 
	
	@Override
	public List<JoinMemberVO> getJoinMemberList() {
		return joinMemberListDao.getJoinMemberList_JM();
	}
	
	
}
