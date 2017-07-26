package com.nyngw.businessSupport.meetingManagement.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.meetingManagement.dao.MeetingManagementDaoImpl;
import com.nyngw.dto.AttendanceVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingRoomVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.dto.Meeting_DocumentVO;
import com.nyngw.dto.Meeting_Document_ListViewVO;

@Service
public class MeetingManagementServiceImpl implements MeetingManagementService {
	@Autowired
	private MeetingManagementDaoImpl meetingManagementDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;

	@Override
	public MeetingListViewVO selectMeetingList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int boardTotalCount = meetingManagementDao.selectMeetingCount(select.getMem_number());
			List<MeetingVO> meetingList = null;
			int firstRow = 0;
			int endRow = 0;
			if (boardTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				meetingList = meetingManagementDao.selectMeetingList(firstRow, endRow, select);
				if(select.getVal()!=null && !select.getVal().equals("")){
					boardTotalCount = meetingManagementDao.boardMeetingCount(select);
				}
			} else {
				currentPageNumber = 0;
				meetingList = Collections.emptyList();
			}
			return  new MeetingListViewVO(meetingList, boardTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	
	/**
	 * 게시물등록
	 */
	@Override
	public void meetingInsert(MeetingVO meeting) {
		meetingManagementDao.meetingInsert(meeting);
	}
	@Override
	public void attendInsert(AttendanceVO attend) {
		meetingManagementDao.attendInsert(attend);
	}
	
	@Override
	public List<MeetingVO> meetingSelet(String mt_reader){
		List<MeetingVO> meetingList = meetingManagementDao.meetingSelect(mt_reader);
		return meetingList;
	}
	@Override
	public List<MeetingVO> selectMeetingAll(){
		List<MeetingVO> meetingList = meetingManagementDao.selectMeetingAll();
		return meetingList;
	}
	@Override
	public MeetingVO selectMeetingNumber(String mt_number){
		MeetingVO meetingvo = meetingManagementDao.selectMeetingNumber(mt_number);
		return meetingvo;
	}
	
	@Override
	public List<MeetingRoomVO> selectMeetingRoom(){
		List<MeetingRoomVO> meetingRoom = meetingManagementDao.selectMeetingRoom();
		return meetingRoom;
	}
	
	@Override
	public void updateMeeting(MeetingVO mt_number){
		meetingManagementDao.updateMeeting(mt_number);
	}
	@Override
	public void meetingDelete(String mt_number) {
		meetingManagementDao.meetingDelete(mt_number);
	}
	@Override
	public void attendDelete(String mt_number) {
		meetingManagementDao.attendDelete(mt_number);
	}
	
//	--------------------------------------회의록------------------------
	@Override
	public Meeting_Document_ListViewVO meeting_DocumentList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int documentTotalCount =meetingManagementDao.selectMeeting_DocumentCount(select.getMem_id()); 
			List<Meeting_DocumentVO> meeting_DocumentList = null;
			int firstRow = 0;
			int endRow = 0;
			if (documentTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				meeting_DocumentList = meetingManagementDao.meeting_DocumentList(firstRow, endRow, select);
			} else {
				currentPageNumber = 0;
				meeting_DocumentList = Collections.emptyList();
			}
			return  new Meeting_Document_ListViewVO(meeting_DocumentList, documentTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	
	@Override
	public Meeting_DocumentVO selectMeetingFileNumber(String md_number){
		Meeting_DocumentVO meeting_documentvo = meetingManagementDao.selectMeeting_DocumentNumber(md_number);
		return meeting_documentvo;
	}
	
	
	@Override
	public void meetingFileInsert(Meeting_DocumentVO meetingFile) {
		meetingManagementDao.meetingFileInsert(meetingFile);
	}
	
	
}
