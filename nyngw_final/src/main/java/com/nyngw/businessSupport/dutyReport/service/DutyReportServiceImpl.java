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

import com.nyngw.businessSupport.dutyReport.dao.DutyReportDaoImpl;
import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.Duty_ReportVO;
import com.nyngw.dto.Duty_Report_ListView;
import com.nyngw.dto.MemberVO;

@Service
public class DutyReportServiceImpl implements DutyReportService {
	@Autowired
	private DutyReportDaoImpl dutyReportDao;
	
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
	
}
