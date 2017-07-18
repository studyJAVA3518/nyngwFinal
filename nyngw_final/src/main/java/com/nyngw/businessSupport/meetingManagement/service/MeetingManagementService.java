package com.nyngw.businessSupport.meetingManagement.service;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;

public interface MeetingManagementService {
	public MeetingListViewVO selectMeetingList(int pageNumber, Board_SelectVO select);

	public void meetingInsert(MeetingVO meeting);

	public List<MeetingVO> meetingSelet(String mt_reader);

	public List<MeetingRoomVO> selectMeetingRoom();
}
