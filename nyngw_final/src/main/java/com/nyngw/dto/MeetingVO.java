package com.nyngw.dto;

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
}
