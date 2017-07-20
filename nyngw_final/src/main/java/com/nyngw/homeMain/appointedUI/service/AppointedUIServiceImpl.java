package com.nyngw.homeMain.appointedUI.service;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.BigMenuVO;
import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MiddleMenuVO;
import com.nyngw.dto.UserInterfaceVO;
import com.nyngw.dto.UserUiVO;
import com.nyngw.homeMain.appointedUI.dao.AppointedUIDaoImpl;

@Service
public class AppointedUIServiceImpl implements AppointedUIService {
	
	@Autowired
	private AppointedUIDaoImpl appointedUIDao;
	
	@Autowired
	private CommonDaoImpl CommonDao;
	
	@Override
	public CompanyVO checkCompany() throws SQLException {
		CompanyVO companyList = appointedUIDao.selectCompany();
		return companyList;
	}

	@Override
	public MemberVO checkMember(String mem_id) throws SQLException {
		MemberVO memberList = appointedUIDao.selectMember(mem_id);
		return memberList;
	}

	///////////////////////////UI설정
	//화면1용
	@Override
	public List<Map> selectBigMenu() {
		List<BigMenuVO> list = appointedUIDao.selectBigMenu();
		List<Map> bigList = new ArrayList<Map>();
		for(int i = 0; i < list.size(); i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("big_number", list.get(i).getBig_number());
			map.put("big_name", list.get(i).getBig_name());
			bigList.add(map);
		}
		return bigList;
	}

	@Override
	public List<Map> selectMiddleMenu(String big_num) {
		List<MiddleMenuVO> list = appointedUIDao.selectMiddleMenu(big_num);
		List<Map> middleList = new ArrayList<Map>();
		for(int i = 0; i < list.size(); i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put("mid_number", list.get(i).getMid_number());
			map.put("mid_name", list.get(i).getMid_name());
			map.put("mid_big_number", list.get(i).getMid_big_number());
			middleList.add(map);
		}
		return middleList;
	}

	@Override
	public void userUiInsert_UI(UserUiVO userUi, Principal principal) {
		MemberVO member = CommonDao.common_selectMemberByMemID(principal.getName());
		int result = appointedUIDao.userUiSelect_UI(member.getMem_number());
		UserInterfaceVO ui = new UserInterfaceVO();
		if(result == 0){
			//여기는 등록하는 부분
			ui.setUi_mem_number(member.getMem_number());
			ui.setUi_user1(userUi.getMiddleMenu());
			ui.setUi_user2(userUi.getMiddleMenu1());
			ui.setUi_user3(userUi.getMiddleMenu2());
			ui.setUi_autouse("n");
			appointedUIDao.userUiInsert_UI(ui);
		}else{
			//이미 등록한적이 있으면 열로 들어와서 수정이된다.
			ui.setUi_mem_number(member.getMem_number());
			ui.setUi_user1(userUi.getMiddleMenu());
			ui.setUi_user2(userUi.getMiddleMenu1());
			ui.setUi_user3(userUi.getMiddleMenu2());
			ui.setUi_autouse("n");
			appointedUIDao.userUiUpdate_UI(ui);
		}
		
		
	}
	
	
}
