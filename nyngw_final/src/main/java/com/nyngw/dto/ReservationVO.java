package com.nyngw.dto;

import java.util.Date;

/**
 * 예약
 * @author pc09
 *
 */
public class ReservationVO {
	private String rv_number;		//예약번호
	private String rv_time;			//예약시간
	private String rv_mem_number;	//예약자
	private String rv_mr_number;	//회의실번호
	private String rv_date;	//예약날짜
	
	
	public String getRv_date() {
		return rv_date;
	}
	public void setRv_date(String rv_date) {
		this.rv_date = rv_date;
	}
	public String getRv_time() {
		return rv_time;
	}
	public void setRv_time(String rv_time) {
		this.rv_time = rv_time;
	}
	public String getRv_number() {
		return rv_number;
	}
	public void setRv_number(String rv_number) {
		this.rv_number = rv_number;
	}
	public String getRv_mem_number() {
		return rv_mem_number;
	}
	public void setRv_mem_number(String rv_mem_number) {
		this.rv_mem_number = rv_mem_number;
	}
	public String getRv_mr_number() {
		return rv_mr_number;
	}
	public void setRv_mr_number(String rv_mr_number) {
		this.rv_mr_number = rv_mr_number;
	}
}
