package com.nyngw.businessSupport.dutyDocument.service;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_Document_ListView;

public interface DutyDocumentService {
	public Duty_Document_ListView selectDocumentList(int pageNumber, Board_SelectVO select);
	public Common_CodeVO documentSelectCodeName_DD(String reportType);
}
