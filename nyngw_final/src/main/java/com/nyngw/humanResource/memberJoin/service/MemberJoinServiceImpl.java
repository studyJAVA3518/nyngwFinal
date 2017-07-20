package com.nyngw.humanResource.memberJoin.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.dto.JoinMemberVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.PositionVO;
import com.nyngw.environmentSetting.planPublicRelationsSetting.dao.PlanPublicRelationsSettingDaoImpl;
import com.nyngw.environmentSetting.planPublicRelationsSetting.service.PlanPublicRelationsSettingService;
import com.nyngw.humanResource.memberJoin.dao.MemberJoinDaoImpl;

@Service
public class MemberJoinServiceImpl implements MemberJoinService {
		
	@Autowired
	private MemberJoinDaoImpl memberJoinDao;
	@Autowired
	private PlanPublicRelationsSettingDaoImpl planPublicRelationsSettingDao;
	
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
	
	/**
	 * 회원가입시 직책과 부서 리스트 가져오는 메서드
	 */
	@Override
	public void viewMjmInfo(Model model) throws SQLException{
		ArrayList<PositionVO> posList = planPublicRelationsSettingDao.selectPositionList();
		ArrayList<DepartmentViewVO> depList = planPublicRelationsSettingDao.selectDepartmentView();
		model.addAttribute("posList",posList);
		model.addAttribute("depList",depList);
		
	}

}
