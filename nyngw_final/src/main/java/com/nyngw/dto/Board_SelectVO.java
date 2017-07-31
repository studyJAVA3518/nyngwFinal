package com.nyngw.dto;

public class Board_SelectVO {
	private String index;
	private String val;
	private String searchDate;
	private String reportType;
	private String mem_code;
	private String mem_number;
	private String mem_id;
	
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_number() {
		return mem_number;
	}
	public void setMem_number(String mem_number) {
		this.mem_number = mem_number;
	}
	public String getMem_code() {
		return mem_code;
	}
	public void setMem_code(String mem_code) {
		this.mem_code = mem_code;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "Board_SelectVO [index=" + index + ", val=" + val
				+ ", searchDate=" + searchDate + ", reportType=" + reportType
				+ ", mem_code=" + mem_code + ", mem_number=" + mem_number
				+ ", mem_id=" + mem_id + "]";
	}
	
}
