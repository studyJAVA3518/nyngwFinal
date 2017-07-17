package com.nyngw.businessSupport.dutyDocument.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.businessSupport.dutyDocument.dao.DutyDocumentDaoImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_ListView;

@Service
public class DutyDocumentServiceImpl implements DutyDocumentService {
	@Autowired
	private DutyDocumentDaoImpl dutyDocumentDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;
	@Override
	public Duty_Document_ListView selectDocumentList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int documentTotalCount =dutyDocumentDao.documentSelectCount_DD(select); 
		System.out.println(documentTotalCount);
//				dutyDocumentDao.selectDocumentCount_DD();
			List<Duty_DocumentVO> documentList = null;
			int firstRow = 0;
			int endRow = 0;
			if (documentTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				documentList = dutyDocumentDao.selectDocumentList_DD(firstRow, endRow, select);
//				documentTotalCount = documentList.size();
//				if(!select.getVal().equals("")){
//					System.out.println("여기 안들어와?");
//					documentTotalCount = dutyDocumentDao.documentSelectCount_DD(select);
//				}
			} else {
				currentPageNumber = 0;
				documentList = Collections.emptyList();
			}
			return  new Duty_Document_ListView(documentList, documentTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	@Override
	public Common_CodeVO documentSelectCodeName_DD(String reportType) {
		Common_CodeVO result = dutyDocumentDao.documentSelectCodeName_DD(reportType);
		return result;
	}
}
