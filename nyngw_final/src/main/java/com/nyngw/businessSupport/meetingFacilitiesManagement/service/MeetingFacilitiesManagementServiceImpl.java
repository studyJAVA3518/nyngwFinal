package com.nyngw.businessSupport.meetingFacilitiesManagement.service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

	
	/**
	 * return void이지만 이곳에서 model에 1)회의실들의 리스트와 2)각 회의실별 예약 리스트를 담아 보내준다.
	 * @param rv_date
	 * @param model
	 */
	public void checkReservation(String rv_date,Model model) {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd"); 

		if(rv_date==null){	//검색인자 날짜값이 있으면
			Date today = new Date();	//오늘 날짜
			rv_date =sdformat.format(today).toString();
		}
		
		//모든 미팅룸을 로드
		List<MeetingRoomVO> mrList = meetingFacilitiesManagementDao.selectMeetingRoom();
		//모델에 회의실 리스트을 넣는다
		model.addAttribute("mrList", mrList);
		
		//미팅룸마다 입력받은 날짜로 해당 날짜의 예약정보 검색  
		for (MeetingRoomVO meetingRoomVO : mrList) {
			
			//파라미터를 넘겨주기위한 map
			Map<String, String> paramMap = new HashMap<String, String>();
			
			paramMap.put("rv_mr_number", meetingRoomVO.getMr_number());	//회의실 이름
			paramMap.put("rv_date", rv_date);	//검색 선택한 날짜 //시간을 데이트 형식으로 받아오는데 그걸 스트링으로 바꿔줘야한다.
			//각각의 회의실 만큼 예약정보를 가져온다.
			List<ReservationVO> reservationList = meetingFacilitiesManagementDao.selectReservation(paramMap);
			
			if (reservationList.size()==0) {	//만약 예약정보가 없다면
				model.addAttribute(meetingRoomVO.getMr_number(),reservationList);
			}
			//모델에 각 회의실 별 예약정보 리스트 추가
			model.addAttribute(meetingRoomVO.getMr_number(),reservationList);
			//mr_number => 'mr201'~
			
			//모델에 검색 날짜 추가
			model.addAttribute("rv_date", rv_date);
		} 
	}
}
