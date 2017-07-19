package com.nyngw.businessSupport.meetingManagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;

@Repository
public class MeetingManagementDaoImpl implements MeetingManagementDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MeetingVO> selectMeetingList(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<MeetingVO> meetingList = (ArrayList<MeetingVO>)sqlSession.selectList("meetingCalList",select,rowBounds);
		return meetingList;
	}
	
	@Override
	public int selectMeetingCount() {
		int result =(Integer) sqlSession.selectOne("selectMeetingCount");
		return result;
	}

	@Override
	public int boardMeetingCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardMeetingCount",select);
		return result;
	}
	
	/**
	 * 게시물 등록 메서드
	 */
	@Override
	public void meetingInsert(MeetingVO meeting) {
		sqlSession.insert("meetingInsert", meeting);
	}
	
	@Override
	public List<MeetingVO> meetingSelect(String mt_reader){
		List<MeetingVO> meetingList=(ArrayList<MeetingVO>)sqlSession.selectList("meetingSelect");
		return meetingList;
	}
	
	@Override
	public MeetingVO selectMeetingNumber(String mt_number){
		MeetingVO meetingvo = (MeetingVO) sqlSession.selectOne("selectMeetingNumber",mt_number);
		return meetingvo;
	}
	
	@Override
	public List<MeetingRoomVO> selectMeetingRoom(){
		List<MeetingRoomVO> meetingRoom = sqlSession.selectList("selectMeetingRoom2");
		return meetingRoom;
	}
	
	@Override
	public void updateMeeting(MeetingVO meeting) {
		sqlSession.selectOne("meetingUpdate",meeting);
	}
	
	@Override
	public void meetingDelete(String mt_number) {
		sqlSession.delete("meetingDelete", mt_number);
	}
}
