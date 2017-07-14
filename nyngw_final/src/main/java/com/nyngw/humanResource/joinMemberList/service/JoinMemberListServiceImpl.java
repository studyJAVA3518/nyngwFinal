package com.nyngw.humanResource.joinMemberList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.CommonJoinMemberVO;
import com.nyngw.dto.JoinMemberVO;
import com.nyngw.humanResource.joinMemberList.dao.JoinMemberListDaoImpl;

@Service
public class JoinMemberListServiceImpl implements JoinMemberListService {

	@Autowired
	private JoinMemberListDaoImpl joinMemberListDao; 

	@Override
	public List<JoinMemberVO> getJoinMemberList() {

		List<JoinMemberVO> joinMemberlist = joinMemberListDao.getJoinMemberList_JM();

		List<JoinMemberVO> list = joinMemberListDao.getJoinMemberVOList_JM();

		if(joinMemberlist.size()<list.size()){
			return list;
		}

		return joinMemberlist;
	}

	@Override
	public JoinMemberVO getMemberDetail(String mem_id) {
		JoinMemberVO joinMember = joinMemberListDao.getMemberDetail_JM(mem_id);

		JoinMemberVO joinCommonMember = joinMemberListDao.getMemberDetailCommon_JM(mem_id);


		if(joinMember == null ){
			joinMember=joinCommonMember;
		}


		return joinMember;
	}

	@Override
	public int modifyMember(JoinMemberVO member) {
		int result=0;
		JoinMemberVO mem = joinMemberListDao.getMemberDetailCommon_JM(member.getMem_id());
		member.setMem_number(mem.getMem_number());
		try{
			if(member.getMdi_bank()!=null){
				joinMemberListDao.modifyMemberBank(member);
				result=1;
			}else{
				joinMemberListDao.modifyMemberBankinsert(member);
				result=1;
			}
		}catch(Exception e){

		}
		return result;
	}

	@Override
	public void modifyDeleteMember(JoinMemberVO member) {
		joinMemberListDao.modifyDeleteMembter(member);
	}


}
