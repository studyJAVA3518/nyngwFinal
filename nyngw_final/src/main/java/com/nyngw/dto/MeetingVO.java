package com.nyngw.dto;

import java.util.Date;

/**
 * 회의
 * @author pc09
 *
 */
public class MeetingVO {
	private String mt_number;		//회의번호
	private String mt_reader;		//회의주최자
	private String mt_md_number;	//회의록번호
	private String mt_rv_number;	//예약번호
	private Date mt_date;		//회의일자
	private String mt_title;	//회의제목
	private String mt_content;	//회의내용
	public String getMt_number() {
		return mt_number;
	}
	public void setMt_number(String mt_number) {
		this.mt_number = mt_number;
	}
	public String getMt_reader() {
		return mt_reader;
	}
	public void setMt_reader(String mt_reader) {
		this.mt_reader = mt_reader;
	}
	public String getMt_md_number() {
		return mt_md_number;
	}
	public void setMt_md_number(String mt_md_number) {
		this.mt_md_number = mt_md_number;
	}
	public String getMt_rv_number() {
		return mt_rv_number;
	}
	public void setMt_rv_number(String mt_rv_number) {
		this.mt_rv_number = mt_rv_number;
	}
	public Date getMt_date() {
		return mt_date;
	}
	public void setMt_date(Date mt_date) {
		this.mt_date = mt_date;
	}
	public String getMt_title() {
		return mt_title;
	}
	public void setMt_title(String mt_title) {
		this.mt_title = mt_title;
	}
	public String getMt_content() {
		return mt_content;
	}
	public void setMt_content(String mt_content) {
		this.mt_content = mt_content;
	}
	
}
