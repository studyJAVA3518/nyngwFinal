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

import com.nyngw.dto.FN_GETDALCNT;

public class FN_GETDALCNTExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fileName = URLEncoder.encode("부서별 출근 사항.xls","utf-8");

		response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\";");

		HSSFSheet sheet = createFirstSheet(workbook);
		createColumLabel(sheet);

		List<FN_GETDALCNT> pageRanks = (List<FN_GETDALCNT>) model.get("memberList");

		int rowNum = 1;
		for (FN_GETDALCNT rank : pageRanks) {
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
		cell.setCellValue("부서");

		cell = firstRow.createCell(1);
		cell.setCellValue("직원수");

		cell = firstRow.createCell(2);
		cell.setCellValue("휴가");

		cell = firstRow.createCell(3);
		cell.setCellValue("조퇴");
		
		cell = firstRow.createCell(4);
		cell.setCellValue("외근");
		
		cell = firstRow.createCell(5);
		cell.setCellValue("야근");
		
		cell = firstRow.createCell(6);
		cell.setCellValue("결근");
		
	}

	private void createPageRankRow(HSSFSheet sheet, FN_GETDALCNT rank, int rownum) {
		HSSFRow row = sheet.createRow(rownum);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getDept_number());
		
		cell = row.createCell(1);
		cell.setCellValue(rank.getDept_total());
		
		cell = row.createCell(2);
		cell.setCellValue(rank.getDept_sf1());
		
		cell = row.createCell(3);
		cell.setCellValue(rank.getDept_sf2());
		
		cell = row.createCell(4);
		cell.setCellValue(rank.getDept_sf3());
		
		cell = row.createCell(5);
		cell.setCellValue(rank.getDept_sf4());
		
		cell = row.createCell(6);
		cell.setCellValue(rank.getDept_sf5());
		
		
	}
}
