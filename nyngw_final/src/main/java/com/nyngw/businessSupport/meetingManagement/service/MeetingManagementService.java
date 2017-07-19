package com.nyngw.businessSupport.meetingManagement.service;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.Meeting_Document_ListViewVO;

public interface MeetingManagementService {
	public MeetingListViewVO selectMeetingList(int pageNumber, Board_SelectVO select);

	public void meetingInsert(MeetingVO meeting);

	public List<MeetingVO> meetingSelet(String mt_reader);

	public List<MeetingRoomVO> selectMeetingRoom();

	public MeetingVO selectMeetingNumber(String mt_number);

	public void updateMeeting(MeetingVO mt_number);

	public void meetingDelete(String mt_number);

	public Meeting_Document_ListViewVO meeting_DocumentList(int pageNumber,
			Board_SelectVO select);
}
