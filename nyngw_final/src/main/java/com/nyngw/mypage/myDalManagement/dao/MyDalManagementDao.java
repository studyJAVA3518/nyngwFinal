package com.nyngw.mypage.myDalManagement.dao;

import java.util.List;

import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.VacationVO;


public interface MyDalManagementDao {
	//출결
	public List<DalViewVO> selectAttendList();
	public List<DalViewVO> selectBoardList(int firstRow, int endRow);
	public int selectBoardCount();

	//휴가
	public List<VacationVO> selectVacationList();
	public List<VacationVO> selectBoardList2(int firstRow, int endRow);
	public int selectBoardCount2();
}
