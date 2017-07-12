package com.nyngw.dto;

import java.util.Date;

/**
 * 일정관리
 * @author pc09
 *
 */
public class ScheduleVO {
	private String sc_number;		//일정관리번호
	private String sc_title;		//제목
	private String sc_content;		//내용
	private Date sc_date;			//일정날짜
	private Date sc_time;			//일정시간
	private String sc_aram;			//알람설정
	private String sc_mem_number;	//사원번호
	private String sc_code_number;	//일정관리구분번호
	public String getSc_number() {
		return sc_number;
	}
	public void setSc_number(String sc_number) {
		this.sc_number = sc_number;
	}
	public String getSc_title() {
		return sc_title;
	}
	public void setSc_title(String sc_title) {
		this.sc_title = sc_title;
	}
	public String getSc_content() {
		return sc_content;
	}
	public void setSc_content(String sc_content) {
		this.sc_content = sc_content;
	}
	public Date getSc_date() {
		return sc_date;
	}
	public void setSc_date(Date sc_date) {
		this.sc_date = sc_date;
	}
	public Date getSc_time() {
		return sc_time;
	}
	public void setSc_time(Date sc_time) {
		this.sc_time = sc_time;
	}
	public String getSc_aram() {
		return sc_aram;
	}
	public void setSc_aram(String sc_aram) {
		this.sc_aram = sc_aram;
	}
	public String getSc_mem_number() {
		return sc_mem_number;
	}
	public void setSc_mem_number(String sc_mem_number) {
		this.sc_mem_number = sc_mem_number;
	}
	public String getSc_code_number() {
		return sc_code_number;
	}
	public void setSc_code_number(String sc_code_number) {
		this.sc_code_number = sc_code_number;
	}
}
