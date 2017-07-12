package com.nyngw.dto;

import java.sql.Timestamp;

/**
 * 근무시간
 * @author pc09
 *
 */
public class Work_TimeVO {
	private String wt_number;			//근무시간번호
	private Timestamp wt_start_time;	//근무시작시간
	private Timestamp wt_end_time;		//근무종료시간
	private String wt_name;				//근무형태명
	private String wt_day;				//근무요일
	private String wt_redday;			//공휴일유무
	public String getWt_number() {
		return wt_number;
	}
	public void setWt_number(String wt_number) {
		this.wt_number = wt_number;
	}
	public Timestamp getWt_start_time() {
		return wt_start_time;
	}
	public void setWt_start_time(Timestamp wt_start_time) {
		this.wt_start_time = wt_start_time;
	}
	public Timestamp getWt_end_time() {
		return wt_end_time;
	}
	public void setWt_end_time(Timestamp wt_end_time) {
		this.wt_end_time = wt_end_time;
	}
	public String getWt_name() {
		return wt_name;
	}
	public void setWt_name(String wt_name) {
		this.wt_name = wt_name;
	}
	public String getWt_day() {
		return wt_day;
	}
	public void setWt_day(String wt_day) {
		this.wt_day = wt_day;
	}
	public String getWt_redday() {
		return wt_redday;
	}
	public void setWt_redday(String wt_redday) {
		this.wt_redday = wt_redday;
	}
}
