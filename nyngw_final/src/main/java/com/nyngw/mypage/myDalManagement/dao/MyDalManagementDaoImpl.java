package com.nyngw.mypage.myDalManagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Board_SelectVO;
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
			int endRow,Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<DalViewVO> boardList = (ArrayList<DalViewVO>)sqlSession.selectList("selectAttendList",select,rowBounds);
		return boardList;
	}
	

	@Override
	public int dalSelectCount(Board_SelectVO select){
		int result = (Integer) sqlSession.selectOne("dalSelectCount",select);
		return result;
	}
	
	@Override
	public int selectBoardCount(String mem_number) {
		int result =(Integer) sqlSession.selectOne("selectAttendCount", mem_number);
		return result;
	}

	
	
	
	//새로만든것
	
	@Override
	public List<VacationVO> vacationList(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<VacationVO> vacationList = (ArrayList<VacationVO>)sqlSession.selectList("vacationList",select,rowBounds);
		return vacationList;
	}
	@Override
	public int selectVacationCount(String mem_number) {
		int result =(Integer) sqlSession.selectOne("selectVacationCount",mem_number);
		return result;
	}

	@Override
	public int boardVacationCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardVacationCount",select);
		return result;
	}
	
	//------------------예전거
	
	
	
	
	
	
}
