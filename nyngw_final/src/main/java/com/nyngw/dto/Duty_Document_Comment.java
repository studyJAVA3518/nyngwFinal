package com.nyngw.dto;

public class Duty_Document_Comment {
	private String ddc_number; 	  //댓글번호
	private String ddc_mem_number;//작성자번호 ex) mem1....
	private String ddc_dd_number; //게시글번호
	private String ddc_content;   //내용
	private String ddc_date;      //날짜
	public String getDdc_number() {
		return ddc_number;
	}
	public void setDdc_number(String ddc_number) {
		this.ddc_number = ddc_number;
	}
	public String getDdc_mem_number() {
		return ddc_mem_number;
	}
	public void setDdc_mem_number(String ddc_mem_number) {
		this.ddc_mem_number = ddc_mem_number;
	}
	public String getDdc_dd_number() {
		return ddc_dd_number;
	}
	public void setDdc_dd_number(String ddc_dd_number) {
		this.ddc_dd_number = ddc_dd_number;
	}
	public String getDdc_content() {
		return ddc_content;
	}
	public void setDdc_content(String ddc_content) {
		this.ddc_content = ddc_content;
	}
	public String getDdc_date() {
		return ddc_date;
	}
	public void setDdc_date(String ddc_date) {
		this.ddc_date = ddc_date;
	}
}
