package com.nyngw.businessSupport.dutyDocument.service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.businessSupport.dutyDocument.dao.DutyDocumentDaoImpl;
import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_ListView;
import com.nyngw.dto.MemberVO;

@Service
public class DutyDocumentServiceImpl implements DutyDocumentService {
	@Autowired
	private DutyDocumentDaoImpl dutyDocumentDao;
	
	@Autowired
	private CommonDaoImpl codeManagerDao;

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
	@Override
	public void dutyDocumentInsert_DD(Duty_DocumentVO dutyDocument) {
		Date dt = new Date();
		dutyDocument.setDd_date(dt);
		if(dutyDocument.getDd_public()==null){
			dutyDocument.setDd_public("n");
		}
		System.out.println(dutyDocument.getDd_number()); //시퀀스
		System.out.println(dutyDocument.getDd_title());  //제목
		System.out.println(dutyDocument.getDd_content());//내용
		System.out.println(dutyDocument.getDd_date());//날짜
		System.out.println(dutyDocument.getDd_public());//공개여부
		System.out.println(dutyDocument.getDd_mem_number());//사용자아이디
		System.out.println(dutyDocument.getDd_code_number());//코드종류
		dutyDocumentDao.dutyDocumentInsert_DD(dutyDocument);
	}
	@Override
	public void dutyDocumentDelete_DD(String dd_number) {
		dutyDocumentDao.dutyDocumentDelete_DD(dd_number);
	}
	@Override
	public Duty_DocumentVO documentSelect_DD(String dd_number) {
		Duty_DocumentVO dutyDocument = dutyDocumentDao.documentSelect_DD(dd_number);
		if(dutyDocument.getDd_public().equals("y")){
			dutyDocument.setDd_select_name("부서일지");
		}else{
			dutyDocument.setDd_select_name("개인일지");
		}
		
		return dutyDocument;
	}
	@Override
	public void dutyDocumentUpdate_DD(Duty_DocumentVO dutyDocument) {
		dutyDocumentDao.dutyDocumentUpdate_DD(dutyDocument);
	}
	
	///////////////////////////////////////////////////부서
	@Override
	public void departmentList(String searchDate, String reportType,
			String titleType, String val, Model model, Principal principal, int pageNumber) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd"); 
		Map<String, Object> select = new HashMap<String, Object>();
		String user = principal.getName();
		String date = "";
		select.put("user", user);
		select.put("reportType", reportType);
		select.put("titleType", titleType);
		select.put("val", val);
		select.put("pageNumber", pageNumber);
		select.put("searchDate", searchDate);
		if(searchDate.equals("today")){
			date = sdformat.format(new Date());
		}else if(searchDate.equals("week")){
			cal.add(Calendar.DATE, -7);
			date = sdformat.format(cal.getTime());
		}else if(searchDate.equals("month")){
			cal.add(Calendar.MONTH, -1);
			date = sdformat.format(cal.getTime());
		}else if(searchDate.equals("trimester")){
			cal.add(Calendar.MONTH, -3);
			date = sdformat.format(cal.getTime());
		}
		select.put("date", date);
		System.out.println(select.get("searchDate"));
		int currentPageNumber = pageNumber;
		int documentTotalCount =dutyDocumentDao.departmentCount_DD(select); 
		System.out.println(documentTotalCount);
		List<Duty_DocumentVO> documentList = null;
		int firstRow = 0;
		int endRow = 0;
		if (documentTotalCount > 0) {
			firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
			endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
			documentList = dutyDocumentDao.selectDepartmentList_DD(firstRow, endRow, select);
		} else {
			currentPageNumber = 0;
			documentList = Collections.emptyList();
		}
		Duty_Document_ListView viewData = new Duty_Document_ListView(documentList, documentTotalCount,
				currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
			
		List<Duty_DocumentVO> list = viewData.getDocumentList();
		for(int i = 0; i < list.size(); i++){
			Common_CodeVO comm = codeManagerDao.common_selectCodeNameByDocument(list.get(i).getDd_code_number());  
			MemberVO member = codeManagerDao.common_selectMemberByMemNumber(list.get(i).getDd_mem_number());
			viewData.getDocumentList().get(i).setDd_code_name(comm.getCode_name());
			viewData.getDocumentList().get(i).setDd_name(member.getMem_name());
		}			
			
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("select",select);
	}
	@Override
	public void departmentDetail(int pageNumber, String searchDate,
			String reportType, String titleType, String val, Model model,
			Principal principal, String dd_number) {
		Map<String, Object> select = new HashMap<String, Object>();
		Duty_DocumentVO dutyDocument = dutyDocumentDao.documentSelect_DD(dd_number);
		MemberVO member = codeManagerDao.common_selectMemberByMemID(principal.getName());
		dutyDocument.setMem_name(member.getMem_name());
		Common_CodeVO common = codeManagerDao.common_selectCodeNameByDocument(dutyDocument.getDd_code_number());
		dutyDocument.setDd_name(common.getCode_name());
		String user = principal.getName();
		if(dutyDocument.getDd_public().equals("y")){
			dutyDocument.setDd_select_name("부서일지");
		}else{
			dutyDocument.setDd_select_name("개인일지");
		}
		select.put("reportType", reportType);
		select.put("titleType", titleType);
		select.put("val", val);
		select.put("pageNumber", pageNumber);
		select.put("searchDate", searchDate);
		model.addAttribute("dutyDocument",dutyDocument);
		model.addAttribute("select",select);
	}
	
	
	
}
