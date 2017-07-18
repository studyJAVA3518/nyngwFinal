package com.nyngw.dto;

import java.util.Date;

public class Vacation_TotalVO {
	private String vp_number;	//휴가정책번호
	private String vp_kind;		//휴가종류
	private String vp_totalday;	//휴가일수
	private String vp_payonoff;	//급여유무
	private String vacation_number;		//휴가번호
	private String vacation_start;		//휴가시작일
	private String vacation_end;			//휴가종료일
	private String vacation_end_duedate;  //휴가종료예정일
	private String vacation_mem_number;	//사원번호
	private String vacation_vp_number;	//휴가정책번호
	private int vacation_during;
	
	public int getVacation_during() {
		return vacation_during;
	}
	public void setVacation_during(int vacation_during) {
		this.vacation_during = vacation_during;
	}
	public String getVp_number() {
		return vp_number;
	}
	public void setVp_number(String vp_number) {
		this.vp_number = vp_number;
	}
	public String getVp_kind() {
		return vp_kind;
	}
	public void setVp_kind(String vp_kind) {
		this.vp_kind = vp_kind;
	}
	public String getVp_totalday() {
		return vp_totalday;
	}
	public void setVp_totalday(String vp_totalday) {
		this.vp_totalday = vp_totalday;
	}
	public String getVp_payonoff() {
		return vp_payonoff;
	}
	public void setVp_payonoff(String vp_payonoff) {
		this.vp_payonoff = vp_payonoff;
	}
	public String getVacation_number() {
		return vacation_number;
	}
	public void setVacation_number(String vacation_number) {
		this.vacation_number = vacation_number;
	}
	public String getVacation_start() {
		return vacation_start;
	}
	public void setVacation_start(String vacation_start) {
		this.vacation_start = vacation_start;
	}
	public String getVacation_end() {
		return vacation_end;
	}
	public void setVacation_end(String vacation_end) {
		this.vacation_end = vacation_end;
	}
	public String getVacation_end_duedate() {
		return vacation_end_duedate;
	}
	public void setVacation_end_duedate(String vacation_end_duedate) {
		this.vacation_end_duedate = vacation_end_duedate;
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
	@Override
	public String toString() {
		return "Vacation_TotalVO [vp_number=" + vp_number + ", vp_kind="
				+ vp_kind + ", vp_totalday=" + vp_totalday + ", vp_payonoff="
				+ vp_payonoff + ", vacation_number=" + vacation_number
				+ ", vacation_start=" + vacation_start + ", vacation_end="
				+ vacation_end + ", vacation_end_duedate="
				+ vacation_end_duedate + ", vacation_mem_number="
				+ vacation_mem_number + ", vacation_vp_number="
				+ vacation_vp_number + "]";
	}
	
}
