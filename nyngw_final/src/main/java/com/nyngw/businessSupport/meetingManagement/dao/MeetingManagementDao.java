package com.nyngw.businessSupport.meetingManagement.dao;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;

public interface MeetingManagementDao {

	public List<MeetingVO> selectMeetingList(int firstRow, int endRow,
			Board_SelectVO select);

	public int boardMeetingCount(Board_SelectVO select);

	public int selectMeetingCount();

	public void meetingInsert(MeetingVO meeting);

	public List<MeetingVO> meetingSelect(String mt_reader);

	public List<MeetingRoomVO> selectMeetingRoom();

}
