package com.nyngw.mypage.myPayManagement.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Member_payViewVO;
import com.nyngw.dto.Member_pay_PageViewVO;
import com.nyngw.mypage.myPayManagement.dao.MyPayManagementDaoImpl;

@Service
public class MyPayManagementServiceImpl implements MyPayManagementService {
	@Autowired
	private MyPayManagementDaoImpl myPayManagementDao;
	
	private static final int BOARD_COUNT_PER_PAGE = 1;
	
	@Override
	public Member_pay_PageViewVO selectMySalaryList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int memberTotalCount = myPayManagementDao.selectMySalaryCount(select.getMem_number());
			List<Member_payViewVO> mySalaryList = null;
			int firstRow = 0;
			int endRow = 0;
			if (memberTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				mySalaryList = myPayManagementDao.selectMySalaryList(firstRow, endRow, select);
				if(select.getVal()!=null && !select.getVal().equals("")){
					memberTotalCount = myPayManagementDao.boardMySalaryCount(select);
				}
			} else {
				currentPageNumber = 0;
				mySalaryList = Collections.emptyList();
			}
			return  new Member_pay_PageViewVO(mySalaryList, memberTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
}
