package com.nyngw.businessSupport.meetingFacilitiesManagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.ReservationVO;

@Repository
public class MeetingFacilitiesManagementDaoImpl implements
		MeetingFacilitiesManagementDao {
	@Autowired
	private SqlSession sqlSession;

	public List<MeetingRoomVO> selectMeetingRoom() {
		return sqlSession.selectList("selectMeetingRoom");
	}

	public List<ReservationVO> selectReservation(Map<String, String> paramMap) {
		return sqlSession.selectList("selectReservation",paramMap);
	}
}
