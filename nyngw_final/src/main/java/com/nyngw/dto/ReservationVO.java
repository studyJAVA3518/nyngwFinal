package com.nyngw.dto;

import java.util.Date;

/**
 * 예약
 * @author pc09
 *
 */
public class ReservationVO {
	private String rv_number;		//예약번호
	private Date rv_date;			//예약날짜시간
	private String rv_mem_numbeer;	//예약자
	private String rv_mr_number;	//회의실번호
	public String getRv_number() {
		return rv_number;
	}
	public void setRv_number(String rv_number) {
		this.rv_number = rv_number;
	}
	public Date getRv_date() {
		return rv_date;
	}
	public void setRv_date(Date rv_date) {
		this.rv_date = rv_date;
	}
	public String getRv_mem_numbeer() {
		return rv_mem_numbeer;
	}
	public void setRv_mem_numbeer(String rv_mem_numbeer) {
		this.rv_mem_numbeer = rv_mem_numbeer;
	}
	public String getRv_mr_number() {
		return rv_mr_number;
	}
	public void setRv_mr_number(String rv_mr_number) {
		this.rv_mr_number = rv_mr_number;
	}
}
