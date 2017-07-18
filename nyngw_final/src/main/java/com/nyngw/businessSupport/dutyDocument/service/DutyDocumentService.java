package com.nyngw.businessSupport.dutyDocument.service;

import java.security.Principal;

import org.springframework.ui.Model;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_ListView;

public interface DutyDocumentService {
	//개인
	public Duty_Document_ListView selectDocumentList(int pageNumber, Board_SelectVO select);
	public Common_CodeVO documentSelectCodeName_DD(String reportType);
	public void dutyDocumentInsert_DD(Duty_DocumentVO dutyDocument);
	public void dutyDocumentDelete_DD(String dd_number);
	public Duty_DocumentVO documentSelect_DD(String dd_number);
	public void dutyDocumentUpdate_DD(Duty_DocumentVO dutyDocument);
	
	//부서
	public void departmentList(String searchDate, String reportType, String titleType, String val, Model model, Principal principal);
}
