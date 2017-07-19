package com.nyngw.businessSupport.dutyDocument.dao;

import java.util.List;
import java.util.Map;

import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_CommentVO;

public interface DutyDocumentDao {
	//개인
	public int selectDocumentCount_DD();
	public List<Duty_DocumentVO> selectDocumentList_DD(int firstRow, int endRow, Board_SelectVO select);
	public int documentSelectCount_DD(Board_SelectVO select);
	public Common_CodeVO documentSelectCodeName_DD(String reportType);
	public void dutyDocumentInsert_DD(Duty_DocumentVO dutyDocument);
	public void dutyDocumentDelete_DD(String dd_number);
	public Duty_DocumentVO documentSelect_DD(String dd_number);
	public void dutyDocumentUpdate_DD(Duty_DocumentVO dutyDocument);
	//부서
	public int departmentCount_DD(Map<String, Object> select);
	public List<Duty_DocumentVO> selectDepartmentList_DD(int firstRow, int endRow, Map<String, Object> select);
	public List<Duty_Document_CommentVO> selectDutyComment(String dd_number);
	public void dutyCommentInsert_DD(Duty_Document_CommentVO comment);
	public void dutyCommentDelete_DD(String ddc_number);
	public void dutyCommentUpdate_DD(Duty_Document_CommentVO comment);
}
