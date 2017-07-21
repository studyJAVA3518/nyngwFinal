package com.nyngw.businessSupport.meetingManagement.dao;

import java.util.List;

import com.nyngw.dto.AttendanceVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.Meeting_DocumentVO;

public interface MeetingManagementDao {

	public List<MeetingVO> selectMeetingList(int firstRow, int endRow,
			Board_SelectVO select);

	public int boardMeetingCount(Board_SelectVO select);

	public int selectMeetingCount(String mem_id);

	public void meetingInsert(MeetingVO meeting);

	public List<MeetingVO> meetingSelect(String mt_reader);

	public List<MeetingRoomVO> selectMeetingRoom();

	public MeetingVO selectMeetingNumber(String mt_number);

	public void updateMeeting(MeetingVO meeting);

	public void meetingDelete(String mt_number);
//--------------------회의록-------------------

	public List<Meeting_DocumentVO> meeting_DocumentList(int firstRow, int endRow,
			Board_SelectVO select);

	public int boardMeetingMeetingCount(Board_SelectVO select);

	public int selectMeeting_DocumentCount(String mem_id);

	public void meetingFileInsert(Meeting_DocumentVO meetingFile);

	public Meeting_DocumentVO selectMeeting_DocumentNumber(String md_number);

//	---------------------------- 참석 ----------------------
	public void attendInsert(AttendanceVO attend);

	public List<MeetingVO> selectMeetingAll();



}
