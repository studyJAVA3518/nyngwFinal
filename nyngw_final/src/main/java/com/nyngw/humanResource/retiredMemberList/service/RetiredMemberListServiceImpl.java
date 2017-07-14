package com.nyngw.humanResource.retiredMemberList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.JoinMemberVO;
import com.nyngw.humanResource.retiredMemberList.dao.RetiredMemberListDaoImpl;

@Service
public class RetiredMemberListServiceImpl implements RetiredMemberListService {
	
	@Autowired
	private RetiredMemberListDaoImpl retiredMemberListDao;
	
	@Override
	public List<JoinMemberVO> getRetiredMemberList(JoinMemberVO member) {
		return retiredMemberListDao.getRetiredMemberList_RM(member);
	}

	@Override
	public void saveMember(String mem_id) {
		retiredMemberListDao.saveMember_RM(mem_id);
	}

}
