package com.nyngw.humanResource.memberJoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.MemberVO;
import com.nyngw.humanResource.memberJoin.dao.MemberJoinDaoImpl;

@Service
public class MemberJoinServiceImpl implements MemberJoinService {
		
	@Autowired
	private MemberJoinDaoImpl memberJoinDao;
	
	@Override
	public MemberVO idCheck(String id) {
		return memberJoinDao.idCheck_MJ(id);
	}

}
