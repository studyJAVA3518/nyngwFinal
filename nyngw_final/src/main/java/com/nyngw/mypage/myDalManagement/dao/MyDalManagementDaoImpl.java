package com.nyngw.mypage.myDalManagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.DalViewVO;
import com.nyngw.dto.VacationVO;

@Repository
public class MyDalManagementDaoImpl implements MyDalManagementDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<DalViewVO> selectAttendList() {
		List<DalViewVO> attendList = null;
		attendList = sqlSession.selectList("selectAttendList");
		return attendList;
	}

	@Override
	public List<DalViewVO> selectBoardList(int firstRow,
			int endRow) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<DalViewVO> boardList = (ArrayList<DalViewVO>)sqlSession.selectList("selectAttendList","",rowBounds);
		return boardList;
	}

	@Override
	public int selectBoardCount() {
		int result =(Integer) sqlSession.selectOne("selectAttendCount");
		return result;
	}

	
	
	
	
	@Override
	public List<VacationVO> selectVacationList() {
		List<VacationVO> vacationList = null;
		vacationList = sqlSession.selectList("selectVacationList");
		return vacationList;
	}

	@Override
	public List<VacationVO> selectBoardList2(int firstRow, int endRow) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<VacationVO> boardList = (ArrayList<VacationVO>)sqlSession.selectList("selectVacationList","",rowBounds);
		return boardList;
	}

	@Override
	public int selectBoardCount2() {
		int result =(Integer) sqlSession.selectOne("selectVacationCount");
		return result;
	}
}
