package com.nyngw.dto;

import java.util.Date;

public class Duty_ReportVO {
	private String dr_number;
	private String dr_code_number;
	private String dr_mem_number;
	private String dr_yesno;
	private String dr_title;
	private String dr_content;
	private Date dr_date;
	
	public String getDr_number() {
		return dr_number;
	}
	public void setDr_number(String dr_number) {
		this.dr_number = dr_number;
	}
	public String getDr_code_number() {
		return dr_code_number;
	}
	public void setDr_code_number(String dr_code_number) {
		this.dr_code_number = dr_code_number;
	}
	public String getDr_mem_number() {
		return dr_mem_number;
	}
	public void setDr_mem_number(String dr_mem_number) {
		this.dr_mem_number = dr_mem_number;
	}
	public String getDr_yesno() {
		return dr_yesno;
	}
	public void setDr_yesno(String dr_yesno) {
		this.dr_yesno = dr_yesno;
	}
	public String getDr_title() {
		return dr_title;
	}
	public void setDr_title(String dr_title) {
		this.dr_title = dr_title;
	}
	public String getDr_content() {
		return dr_content;
	}
	public void setDr_content(String dr_content) {
		this.dr_content = dr_content;
	}
	public Date getDr_date() {
		return dr_date;
	}
	public void setDr_date(Date dr_date) {
		this.dr_date = dr_date;
	}
}
