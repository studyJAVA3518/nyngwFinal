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
import com.nyngw.dto.Board_CommentVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_Document_CommentVO;
import com.nyngw.dto.Duty_Document_ListView;
import com.nyngw.dto.MemberVO;

@Service
public class DutyDocumentServiceImpl implements DutyDocumentService {
	@Autowired
	private DutyDocumentDaoImpl dutyDocumentDao;
	
	@Autowired
	private CommonDaoImpl codeManagerDao;
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
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
		if(viewData.getPageTotalCount()>0){
			int beginPageNumber = (viewData.getCurrentPageNumber()-1)/PAGE_NUMBER_COUNT_PER_PAGE*PAGE_NUMBER_COUNT_PER_PAGE+1;
			int endPageNumber = beginPageNumber+ PAGE_NUMBER_COUNT_PER_PAGE-1;
			if(endPageNumber > viewData.getPageTotalCount()){
				endPageNumber = viewData.getPageTotalCount();
			}
			model.addAttribute("perPage", PAGE_NUMBER_COUNT_PER_PAGE);	//페이지 번호의 갯수
			model.addAttribute("end", viewData.getDocumentList().size()-1);//마지막 페이지
			model.addAttribute("beginPage", beginPageNumber);	//보여줄 페이지 번호의 시작
			model.addAttribute("endPage", endPageNumber);		//보여줄 페이지 번호의 끝
		}	
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
		System.out.println(principal.getName()+"22222222222222222222222222222222222222222");
		dutyDocument.setMem_name(member.getMem_name());
		Common_CodeVO common = codeManagerDao.common_selectCodeNameByDocument(dutyDocument.getDd_code_number());
		dutyDocument.setDd_name(common.getCode_name());
		if(dutyDocument.getDd_public().equals("y")){
			dutyDocument.setDd_select_name("부서일지");
		}else{
			dutyDocument.setDd_select_name("개인일지");
		}
		//댓글용
		List<Duty_Document_CommentVO> comment = dutyDocumentDao.selectDutyComment(dd_number);
		MemberVO commentMember = null;
		for (int i = 0; i < comment.size(); i++) {
			commentMember = codeManagerDao.common_selectMemberByMemNumber(comment.get(i).getDdc_mem_number());
			comment.get(i).setDdc_name(commentMember.getMem_name());
		}
		select.put("reportType", reportType);
		select.put("titleType", titleType);
		select.put("val", val);
		select.put("pageNumber", pageNumber);
		select.put("searchDate", searchDate);
		model.addAttribute("user", member.getMem_number());
		model.addAttribute("dutyDocument",dutyDocument);
		model.addAttribute("select",select);
		model.addAttribute("comment",comment);
	}
	
	@Override
	public void departmentCommentWrite(String id, String comment_content, Principal principal) {
		MemberVO member = codeManagerDao.common_selectMemberByMemID(principal.getName());
		Date dt = new Date();
		Duty_Document_CommentVO comment = new Duty_Document_CommentVO();
		comment.setDdc_dd_number(id);
		comment.setDdc_content(comment_content);
		comment.setDdc_mem_number(member.getMem_number());
		comment.setDdc_date(dt);
		dutyDocumentDao.dutyCommentInsert_DD(comment);
	}
	@Override
	public void departmentCommentDelete(String id) {
		dutyDocumentDao.dutyCommentDelete_DD(id);
	}
	@Override
	public void departmentCommentUpdate(String ddc_number, String ddc_mem_number, String ddc_content, String dd_number) {
		Duty_Document_CommentVO comment = new Duty_Document_CommentVO();
		Date dt = new Date();
		comment.setDdc_number(ddc_number);
		comment.setDdc_date(dt);
		comment.setDdc_mem_number(ddc_mem_number);
		comment.setDdc_content(ddc_content);
		comment.setDdc_dd_number(dd_number);
		dutyDocumentDao.dutyCommentUpdate_DD(comment);
	}
}
