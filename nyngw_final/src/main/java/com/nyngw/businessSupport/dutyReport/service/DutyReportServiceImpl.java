package com.nyngw.businessSupport.dutyReport.service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.businessSupport.dutyDocument.dao.DutyDocumentDaoImpl;
import com.nyngw.businessSupport.dutyReport.dao.DutyReportDaoImpl;
import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_DocumentVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.Duty_Report_CommentVO;
import com.nyngw.dto.Duty_Report_ListView;
import com.nyngw.dto.MemberVO;

@Service
public class DutyReportServiceImpl implements DutyReportService {
	@Autowired
	private DutyReportDaoImpl dutyReportDao;
	@Autowired
	private DutyDocumentDaoImpl dutyDocumentDao;
	@Autowired
	private CommonDaoImpl commonDao;
	private static final int PAGE_NUMBER_COUNT_PER_PAGE = 5;
	private static final int BOARD_COUNT_PER_PAGE = 5;
	
	@Override
	public void dutyReportList(String searchDate, String reportType,
			String titleType, String val, Model model, Principal principal, int pageNumber){
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
		int currentPageNumber = pageNumber;
		int documentTotalCount = dutyReportDao.dutyReportmentCount(select);
		List<Duty_ReportVO> dutyReportList = new ArrayList<Duty_ReportVO>();
		int firstRow = 0;
		int endRow = 0;
		if (documentTotalCount > 0) {
			firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
			endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
			dutyReportList = dutyReportDao.selectDutyReportList(firstRow, endRow, select);
		}else{
			currentPageNumber = 0;
			dutyReportList = Collections.emptyList();
		}
		Duty_Report_ListView viewData = new Duty_Report_ListView(dutyReportList, documentTotalCount, currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
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
		List<Duty_ReportVO> list = viewData.getDocumentList();
		for(int i = 0; i < list.size(); i++){
			Common_CodeVO comm = commonDao.common_selectCodeNameByDocument(list.get(i).getDr_code_number());  
			MemberVO member = commonDao.common_selectMemberByMemNumber(list.get(i).getDr_mem_number());
			viewData.getDocumentList().get(i).setDr_code_name(comm.getCode_name());
			viewData.getDocumentList().get(i).setDr_mem_number(member.getMem_number());
		}			
			
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("select",select);
	}

	@Override
	public List<Common_CodeVO> dutyReportCodeSelect() {
		List<Common_CodeVO> codeList = dutyReportDao.dutyReportCodeSelect();
		return codeList;
	}
	
	@Override
	public void dutyReportDelete(String dr_number){
		dutyReportDao.dutyReportDelete(dr_number);
	}
	
	@Override
	public void dutyReportWrite(Duty_ReportVO dutyReportVO){
		if(dutyReportVO.getDr_yesno()==null){
			dutyReportVO.setDr_yesno("n");
		}else{
			Date dt = new Date();
			Duty_DocumentVO dutyDocument = new Duty_DocumentVO();
			dutyDocument.setDd_code_number(dutyReportVO.getDr_code_number());
			dutyDocument.setDd_content(dutyReportVO.getDr_content());
			dutyDocument.setDd_date(dt);
			dutyDocument.setDd_start_date(dutyReportVO.getDr_date());
			dutyDocument.setDd_title(dutyReportVO.getDr_title());
			dutyDocument.setDd_public("n");
			dutyDocument.setDd_mem_number(dutyReportVO.getDr_mem_number());
			
			dutyDocumentDao.dutyDocumentInsert_DD(dutyDocument);
		}
		dutyReportDao.dutyReportWrite(dutyReportVO);
	}
	
	@Override
	public void dutyReportDetail(int pageNumber, String searchDate,
			String reportType, String titleType, String val, Model model,
			Principal principal, String dr_number){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Map<String, Object> select = new HashMap<String, Object>();
		Duty_ReportVO dutyReportVO = dutyReportDao.dutyReportSelect(dr_number);
		Common_CodeVO common = commonDao.common_selectCodeNameByDocument(dutyReportVO.getDr_code_number());
		MemberVO member = commonDao.common_selectMemberByMemNumber(dutyReportVO.getDr_mem_number());//작성자의 번호로 멤버의 정보를 조회함.
		MemberVO toMember = commonDao.common_selectMemberByMemNumber(dutyReportVO.getDr_to_mem_number());//보고대상의 번호로 멤버의 정보를 조회함.
		dutyReportVO.setDr_code_name(common.getCode_name());
		//댓글용
		List<Duty_Report_CommentVO> comment = dutyReportDao.selectDutyReportComment(dr_number);
		MemberVO commentMember = null;
		for(int i =0; i< comment.size(); i++){
			commentMember = commonDao.common_selectMemberByMemNumber(comment.get(i).getDrc_mem_number());
			comment.get(i).setDrc_mem_name(commentMember.getMem_name());
		}
		MemberVO commentUser = commonDao.common_selectMemberByMemID(principal.getName());
		select.put("reportType", reportType);
		select.put("titleType", titleType);
		select.put("val", val);
		select.put("pageNumber", pageNumber);
		select.put("searchDate", searchDate);
		model.addAttribute("mem_id",member.getMem_id());//작성자의 ID
		model.addAttribute("loginUser",principal.getName());//현재 로그인한 유저
		model.addAttribute("loginUserID",commentUser.getMem_number());
		model.addAttribute("dr_writedate",dateformat.format(dutyReportVO.getDr_writedate()));
		model.addAttribute("dr_date",dateformat.format(dutyReportVO.getDr_date()));
		model.addAttribute("username",member.getMem_name());//작성자의 이름
		model.addAttribute("toUser",toMember.getMem_name());//보고대상의 이름
		model.addAttribute("dutyReportVO",dutyReportVO);
		model.addAttribute("select",select);
		model.addAttribute("comment",comment);
	}

	@Override
	public void getDutyReportselect(String searchDate, String reportType,
			String titleType, String val, Model model, Principal principal,
			int pageNumber) {
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
		int currentPageNumber = pageNumber;
		int documentTotalCount = dutyReportDao.dutyReportCount(select);
		List<Duty_ReportVO> dutyReportList = new ArrayList<Duty_ReportVO>();
		int firstRow = 0;
		int endRow = 0;
		if (documentTotalCount > 0) {
			firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
			endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
			dutyReportList = dutyReportDao.selectDutyReportList2(firstRow, endRow, select);
		}else{
			currentPageNumber = 0;
			dutyReportList = Collections.emptyList();
		}
		Duty_Report_ListView viewData = new Duty_Report_ListView(dutyReportList, documentTotalCount, currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
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
		List<Duty_ReportVO> list = viewData.getDocumentList();
		for(int i = 0; i < list.size(); i++){
			Common_CodeVO comm = commonDao.common_selectCodeNameByDocument(list.get(i).getDr_code_number());  
			MemberVO member = commonDao.common_selectMemberByMemNumber(list.get(i).getDr_mem_number());
			viewData.getDocumentList().get(i).setDr_code_name(comm.getCode_name());
			viewData.getDocumentList().get(i).setDr_mem_number(member.getMem_number());
			viewData.getDocumentList().get(i).setDr_mem_name(member.getMem_name());
		}
		
		model.addAttribute("viewData",viewData);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("select",select);
	}

	@Override
	public void dutyReportCommentWrite(String drc_content, String id, Principal principal) {
		Duty_Report_CommentVO drcVO= new Duty_Report_CommentVO();
		MemberVO member = commonDao.common_selectMemberByMemID(principal.getName());
		drcVO.setDrc_content(drc_content);
		drcVO.setDrc_dr_number(id);
		drcVO.setDrc_mem_number(member.getMem_number());
		
		dutyReportDao.dutyReportCommentWrite(drcVO);
		
	}
	
	@Override
	public void dutyReportCommentDelete(String id){
		dutyReportDao.dutyReportCommentDelete(id);
	}
}
