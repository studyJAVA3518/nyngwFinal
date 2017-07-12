package com.nyngw.dto;

import java.util.Date;

/**
 * 회의록
 * @author pc09
 *
 */
public class Meeting_DocumentVO {
	private String md_number;	//회의록번호
	private String md_name;		//회의록명
	private String md_content;	//내용
	private Date md_date;		//작성일
	private String md_writer;	//작성자
	public String getMd_number() {
		return md_number;
	}
	public void setMd_number(String md_number) {
		this.md_number = md_number;
	}
	public String getMd_name() {
		return md_name;
	}
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	public String getMd_content() {
		return md_content;
	}
	public void setMd_content(String md_content) {
		this.md_content = md_content;
	}
	public Date getMd_date() {
		return md_date;
	}
	public void setMd_date(Date md_date) {
		this.md_date = md_date;
	}
	public String getMd_writer() {
		return md_writer;
	}
	public void setMd_writer(String md_writer) {
		this.md_writer = md_writer;
	}
}
