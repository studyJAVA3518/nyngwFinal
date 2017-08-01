package com.nyngw.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 휴가
 * @author pc09
 *
 */
public class VacationVO {
	private String vacation_number;		//휴가번호
	private Date vacation_start;		//휴가시작일
	private Date vacation_end;			//휴가종료일
	private Date vacation_end_duedate;  //휴가종료예정일
	private String vacation_mem_number;	//사원번호
	private String vacation_vp_number;	//휴가정책번호
	private String vp_kind;
	
	public String getVp_kind() {
		return vp_kind;
	}
	public void setVp_kind(String vp_kind) {
		this.vp_kind = vp_kind;
	}
	public Date getVacation_end_duedate() {
		return vacation_end_duedate;
	}
	public void setVacation_end_duedate(Date vacation_end_duedate) {
		this.vacation_end_duedate = vacation_end_duedate;
	}
	public String getVacation_number() {
		return vacation_number;
	}
	public void setVacation_number(String vacation_number) {
		this.vacation_number = vacation_number;
	}
	public Date getVacation_start() {
		return vacation_start;
	}
	public void setVacation_start(Date vacation_start) {
		this.vacation_start = vacation_start;
	}
	public String getVacation_end() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String to = transFormat.format(vacation_end);
		return to;
	}
	public void setVacation_end(Date vacation_end) {
		this.vacation_end = vacation_end;
	}
	public String getVacation_mem_number() {
		return vacation_mem_number;
	}
	public void setVacation_mem_number(String vacation_mem_number) {
		this.vacation_mem_number = vacation_mem_number;
	}
	public String getVacation_vp_number() {
		return vacation_vp_number;
	}
	public void setVacation_vp_number(String vacation_vp_number) {
		this.vacation_vp_number = vacation_vp_number;
	}
}
