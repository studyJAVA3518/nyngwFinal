package com.nyngw.dto;

import java.util.Date;

/**
 * 업무일지
 * @author pc09
 *
 */
public class Duty_DocumentVO {
	private String dd_number;		//업무일지번호
	private String dd_title;		//제목
	private String dd_content;		//내용
	private Date dd_date;			//날짜
	private String dd_public;		//공개여부
	private String dd_mem_number;	//작성자
	private String dd_code_number;	//업무일지구분번호
	
	public String getDd_public() {
		return dd_public;
	}
	public void setDd_public(String dd_public) {
		this.dd_public = dd_public;
	}
	public String getDd_number() {
		return dd_number;
	}
	public void setDd_number(String dd_number) {
		this.dd_number = dd_number;
	}
	public String getDd_title() {
		return dd_title;
	}
	public void setDd_title(String dd_title) {
		this.dd_title = dd_title;
	}
	public String getDd_content() {
		return dd_content;
	}
	public void setDd_content(String dd_content) {
		this.dd_content = dd_content;
	}
	public Date getDd_date() {
		return dd_date;
	}
	public void setDd_date(Date dd_date) {
		this.dd_date = dd_date;
	}
	public String getDd_mem_number() {
		return dd_mem_number;
	}
	public void setDd_mem_number(String dd_mem_number) {
		this.dd_mem_number = dd_mem_number;
	}
	public String getDd_code_number() {
		return dd_code_number;
	}
	public void setDd_code_number(String dd_code_number) {
		this.dd_code_number = dd_code_number;
	}
}
