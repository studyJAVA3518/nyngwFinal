package com.nyngw.mypage.myDalManagement.dao;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.VacationVO;


public interface MyDalManagementDao {
	//출결
	public List<DalViewVO> selectAttendList();
	public List<DalViewVO> selectBoardList(int firstRow, int endRow,
			Board_SelectVO select);
	public int selectBoardCount(String mem_number);
	public int dalSelectCount(Board_SelectVO select);

	
	
	//새로만든것
	public List<VacationVO> vacationList(int firstRow, int endRow,
			Board_SelectVO select);
	public int selectVacationCount(String mem_number);
	public int boardVacationCount(Board_SelectVO select);
	
}
