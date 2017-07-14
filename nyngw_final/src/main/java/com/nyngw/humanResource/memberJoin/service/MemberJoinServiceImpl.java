package com.nyngw.humanResource.memberJoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nyngw.dto.JoinMemberVO;
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
	
	@Transactional
	@Override
	public int joinMember(JoinMemberVO joinMember) {
		int result=0;
		try{
			memberJoinDao.joinMember_JM(joinMember);
			MemberVO member = memberJoinDao.idCheck_MJ(joinMember.getMem_id());
			joinMember.setMem_number(member.getMem_number());
			memberJoinDao.joinMemberMDI_JM(joinMember);
			result=1;
		}catch(Exception e){
			
		}
		return result;
	}

}
