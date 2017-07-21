package com.nyngw.dto;

import java.util.Date;

public class Duty_Report_CommentVO {
	private String drc_number;
	private String drc_dr_number;
	private String drc_content;
	private String drc_mem_number;
	private Date drc_date;
	private String drc_mem_name;
	public String getDrc_mem_name() {
		return drc_mem_name;
	}
	public void setDrc_mem_name(String drc_mem_name) {
		this.drc_mem_name = drc_mem_name;
	}
	public String getDrc_number() {
		return drc_number;
	}
	public void setDrc_number(String drc_number) {
		this.drc_number = drc_number;
	}
	public String getDrc_dr_number() {
		return drc_dr_number;
	}
	public void setDrc_dr_number(String drc_dr_number) {
		this.drc_dr_number = drc_dr_number;
	}
	public String getDrc_content() {
		return drc_content;
	}
	public void setDrc_content(String drc_content) {
		this.drc_content = drc_content;
	}
	public String getDrc_mem_number() {
		return drc_mem_number;
	}
	public void setDrc_mem_number(String drc_mem_number) {
		this.drc_mem_number = drc_mem_number;
	}
	public Date getDrc_date() {
		return drc_date;
	}
	public void setDrc_date(Date drc_date) {
		this.drc_date = drc_date;
	}
	
}
