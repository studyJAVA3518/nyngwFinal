package com.nyngw.dto;

import java.util.Date;

/**
 * 근태관리
 * @author pc09
 *
 */
public class Diligence_And_LazinessVO {
	private String dal_number;		//근태현황번호
	private Date dal_date;			//날짜
	private Date dal_attend_time;	//출근시간
	private Date dal_off_time;		//퇴근시간
	private String dal_content;		//사유
	private String dal_mem_number;	//사원번호
	private String dal_sf_number;   //특이사항번호
	
	public String getDal_sf_number() {
		return dal_sf_number;
	}
	public void setDal_sf_number(String dal_sf_number) {
		this.dal_sf_number = dal_sf_number;
	}
	public String getDal_number() {
		return dal_number;
	}
	public void setDal_number(String dal_number) {
		this.dal_number = dal_number;
	}
	public Date getDal_date() {
		return dal_date;
	}
	public void setDal_date(Date dal_date) {
		this.dal_date = dal_date;
	}
	public Date getDal_attend_time() {
		return dal_attend_time;
	}
	public void setDal_attend_time(Date dal_attend_time) {
		this.dal_attend_time = dal_attend_time;
	}
	public Date getDal_off_time() {
		return dal_off_time;
	}
	public void setDal_off_time(Date dal_off_time) {
		this.dal_off_time = dal_off_time;
	}
	public String getDal_content() {
		return dal_content;
	}
	public void setDal_content(String dal_content) {
		this.dal_content = dal_content;
	}
	public String getDal_mem_number() {
		return dal_mem_number;
	}
	public void setDal_mem_number(String dal_mem_number) {
		this.dal_mem_number = dal_mem_number;
	}
}
