package com.nyngw.humanResource.dalManagement.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.nyngw.dto.Member_ViewVO;

public class Member_ViewVOExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fileName = URLEncoder.encode("사원 특이 사항.xls","utf-8");

		response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\";");

		HSSFSheet sheet = createFirstSheet(workbook);
		createColumLabel(sheet);

		List<Member_ViewVO> pageRanks = (List<Member_ViewVO>) model.get("memberList");

		int rowNum = 1;
		for (Member_ViewVO rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);
		}
	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "페이지순위");
		sheet.setColumnWidth(1, 256*20);
		return sheet;
	}

	private void createColumLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);

		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("사원번호");

		cell = firstRow.createCell(1);
		cell.setCellValue("사원아이디");

		cell = firstRow.createCell(2);
		cell.setCellValue("사원명");

		cell = firstRow.createCell(3);
		cell.setCellValue("부서명");
		
		cell = firstRow.createCell(4);
		cell.setCellValue("직책");
		
		cell = firstRow.createCell(5);
		cell.setCellValue("날짜");
		
		cell = firstRow.createCell(6);
		cell.setCellValue("사유");
		
	}

	private void createPageRankRow(HSSFSheet sheet, Member_ViewVO rank, int rownum) {
		HSSFRow row = sheet.createRow(rownum);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getMem_number());
		
		cell = row.createCell(1);
		cell.setCellValue(rank.getMem_id());
		
		cell = row.createCell(2);
		cell.setCellValue(rank.getMem_name());
		
		cell = row.createCell(3);
		cell.setCellValue(rank.getDept_name());
		
		cell = row.createCell(4);
		cell.setCellValue(rank.getPosition_name());
		
		cell = row.createCell(5);
		cell.setCellValue(rank.getDal_date());
		
		cell = row.createCell(6);
		cell.setCellValue(rank.getDal_content());
		
		
	}
}
