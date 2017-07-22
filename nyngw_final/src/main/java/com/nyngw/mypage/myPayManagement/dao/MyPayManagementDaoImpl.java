package com.nyngw.mypage.myPayManagement.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Member_payViewVO;
import com.nyngw.dto.Member_pay_PageViewVO;

@Repository
public class MyPayManagementDaoImpl implements MyPayManagementDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Member_payViewVO> selectMySalaryList(int firstRow, int endRow,
			Board_SelectVO select) {
		int offset = firstRow - 1;
		int limit = endRow - firstRow + 1;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Member_payViewVO> mySalaryList = (ArrayList<Member_payViewVO>)sqlSession.selectList("mySalaryList",select,rowBounds);
		return mySalaryList;
	}
	@Override
	public int selectMySalaryCount(String mem_number) {
		int result =(Integer) sqlSession.selectOne("selectMySalaryCount",mem_number);
		return result;
	}
	@Override
	public int boardMySalaryCount(Board_SelectVO select) {
		int result =(Integer) sqlSession.selectOne("boardMySalaryCount",select);
		return result;
	}
}
