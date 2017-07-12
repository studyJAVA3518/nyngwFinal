package com.nyngw.mypage.myDalManagement.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.VacationVO;
import com.nyngw.mypage.myDalManagement.MyAttendedListView;
import com.nyngw.mypage.myDalManagement.MyVacationListView;
import com.nyngw.mypage.myDalManagement.dao.MyDalManagementDaoImpl;

@Service
public class MyDalManagementServiceImpl implements MyDalManagementService {
	@Autowired
	private MyDalManagementDaoImpl myDalManagementDaoImpl;
	private static final int BOARD_COUNT_PER_PAGE = 5;
	
	//근태ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ
	
	@Override
	public List<DalViewVO> selectAttendList() {
		List<DalViewVO> attend = myDalManagementDaoImpl.selectAttendList();
		return attend;
	}

	@Override
	public MyAttendedListView selectAttendList(int pageNumber) {
		int currentPageNumber = pageNumber;
		
		int boardTotalCount = myDalManagementDaoImpl.selectBoardCount();
		
		List<DalViewVO> boardList= null;
		int firstRow = 0;
		int endRow = 0;
		if (boardTotalCount > 0) {
			firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
			endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
			boardList =myDalManagementDaoImpl.selectBoardList(firstRow, endRow);
		} else {
			currentPageNumber = 0;
			boardList = Collections.emptyList();
		}
		return new MyAttendedListView(boardList, boardTotalCount, currentPageNumber,
				BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	
	

	//휴가ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
	
	@Override
	public List<VacationVO> selectVacationList() {
		List<VacationVO> vacation = myDalManagementDaoImpl.selectVacationList();
		return vacation;
	}

	@Override
	public MyVacationListView selectVacationList(int pageNumber) {
		int currentPageNumber = pageNumber;
		
		int boardTotalCount = myDalManagementDaoImpl.selectBoardCount2();
		
		List<VacationVO> boardList= null;
		int firstRow = 0;
		int endRow = 0;
		if (boardTotalCount > 0) {
			firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
			endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
			boardList =myDalManagementDaoImpl.selectBoardList2(firstRow, endRow);
		} else {
			currentPageNumber = 0;
			boardList = Collections.emptyList();
		}
		return new MyVacationListView(boardList, boardTotalCount, currentPageNumber,
				BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
}
