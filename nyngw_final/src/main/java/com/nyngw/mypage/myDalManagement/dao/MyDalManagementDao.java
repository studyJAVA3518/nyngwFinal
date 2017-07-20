package com.nyngw.mypage.myDalManagement.dao;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.VacationVO;


public interface MyDalManagementDao {
	//출결
	public List<DalViewVO> selectAttendList();
	public List<DalViewVO> selectBoardList(int firstRow, int endRow,
			Board_SelectVO select);
	public int selectBoardCount(String mem_number);
	public int dalSelectCount(Board_SelectVO select);

	//휴가
	public List<VacationVO> selectVacationList();
	public List<VacationVO> selectBoardList2(int firstRow, int endRow);
	public int selectBoardCount2();
	
}
