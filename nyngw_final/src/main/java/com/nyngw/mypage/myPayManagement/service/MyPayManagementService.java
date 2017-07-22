package com.nyngw.mypage.myPayManagement.service;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Member_pay_PageViewVO;

public interface MyPayManagementService {

	public Member_pay_PageViewVO selectMySalaryList(int pageNumber,
			Board_SelectVO select);

}
