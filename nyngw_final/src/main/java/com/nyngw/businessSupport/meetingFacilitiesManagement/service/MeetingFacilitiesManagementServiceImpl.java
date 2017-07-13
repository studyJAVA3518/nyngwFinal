package com.nyngw.businessSupport.meetingFacilitiesManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.SubstringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.businessSupport.meetingFacilitiesManagement.dao.MeetingFacilitiesManagementDaoImpl;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.ReservationVO;

@Service
public class MeetingFacilitiesManagementServiceImpl implements
		MeetingFacilitiesManagementService {
	@Autowired
	private MeetingFacilitiesManagementDaoImpl meetingFacilitiesManagementDao;

	public void checkReservation(String rv_date,Model model) {
		//모든 미팅룸을 로드
		List<MeetingRoomVO> mrList = meetingFacilitiesManagementDao.selectMeetingRoom();
		Map<String, List> resultMap = new HashMap<String, List>();
		
		//미팅룸마다 입력받은 날짜로 해당 날짜의 예약정보 검색  
		for (MeetingRoomVO meetingRoomVO : mrList) {
			//파라미터를 넘겨주기위한 map
			Map<String, String> paramMap = new HashMap<String, String>();
			
			//select에 필요한 값을 정해서 넣어준다.
			
			paramMap.put("rv_mr_number", meetingRoomVO.getMr_number());	//회의실 이름
//			paramMap.put("rv_time", rv_date);	
			paramMap.put("rv_date", "20170707");	//검색 선택한 날짜 //시간을 데이트 형식으로 받아오는데 그걸 스트링으로 바꿔줘야한다.
			
			//각각의 회의실 만큼 예약정보를 가져온다.
			List<ReservationVO> reservationList = meetingFacilitiesManagementDao.selectReservation(paramMap);
			
			//모델에 각각의 회의실 번호로 list를 넣어준다.
			model.addAttribute(meetingRoomVO.getMr_number(),reservationList);
			//mr_number => 'mr201'~
		} 
		
		//모델에 회의실 리스트을 넣는다
		model.addAttribute("mrList", mrList);
	}
}
