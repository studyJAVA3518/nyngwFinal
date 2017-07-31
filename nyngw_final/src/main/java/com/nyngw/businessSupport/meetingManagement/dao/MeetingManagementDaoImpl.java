package com.nyngw.businessSupport.meetingManagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.AttendanceVO;
import com.nyngw.dto.BoardVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.Meeting_DocumentVO;

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
		System.out.println(select+"  <<<<>>>>>  "+rowBounds);
		List<MeetingVO> meetingList = (ArrayList<MeetingVO>)sqlSession.selectList("meetingCalList",select,rowBounds);
		return meetingList;
	}
	@Override
	public List<MeetingVO> selectMeetingAll() {
		List<MeetingVO> meetingList = (ArrayList<MeetingVO>)sqlSession.selectList("selectMeetingAll");
		return meetingList;
	}
	
	@Override
	public int selectMeetingCount(String mem_id) {
		int result =(Integer) sqlSession.selectOne("selectMeetingCount",mem_id);
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
	public void attendInsert(AttendanceVO attend) {
		sqlSession.insert("attendInsert", attend);
	}

	
	@Override
	public List<MeetingVO> meetingSelect(String mt_reader){
		List<MeetingVO> meetingList=(ArrayList<MeetingVO>)sqlSession.selectList("meetingSelect",mt_reader);
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
	@Override
	public void attendDelete(String mt_number) {
		sqlSession.delete("attendDelete", mt_number);
	}
	
//	----------------------------------------회의록 ---------------------
	@Override
	public int selectMeeting_DocumentCount(String mem_id) {
		int result =(Integer) sqlSession.selectOne("selectMeeting_DocumentCount",mem_id);
		return result;
	}
	
	@Override
	public List<Meeting_DocumentVO> meeting_DocumentList(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Meeting_DocumentVO> meetingDocumentList = (ArrayList<Meeting_DocumentVO>)sqlSession.selectList("meeting_DocumentList",select,rowBounds);
		return meetingDocumentList;
	}

	@Override
	public int boardMeetingMeetingCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardMeeting_DocumentCount",select);
		return result;
	}
	
	@Override
	public Meeting_DocumentVO selectMeeting_DocumentNumber(String md_number){
		Meeting_DocumentVO meeting_documentvo = (Meeting_DocumentVO) sqlSession.selectOne("selectMeeting_DocumentNumber",md_number);
		return meeting_documentvo;
	}
	
	@Override
	public void meetingFileInsert(Meeting_DocumentVO meetingFile) {
		System.out.println(meetingFile);
		sqlSession.insert("meetingFileInsert", meetingFile);
	}
	public int isMeeting_Document_MM(String mt_md_number) {
		return (int) sqlSession.selectOne("isMeeting_Document_MM", mt_md_number);
	}
	
}
