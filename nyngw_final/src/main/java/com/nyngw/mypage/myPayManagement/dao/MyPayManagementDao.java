package com.nyngw.mypage.myPayManagement.dao;

import java.util.List;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Member_payViewVO;
import com.nyngw.dto.Member_pay_PageViewVO;

public interface MyPayManagementDao {

	public List<Member_payViewVO> selectMySalaryList(int firstRow, int endRow,
			Board_SelectVO select);

	public int selectMySalaryCount(String mem_number);

	public int boardMySalaryCount(Board_SelectVO select);


}
