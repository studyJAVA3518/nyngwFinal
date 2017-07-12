package com.nyngw.dto;

/**
 * 참석
 * @author pc09
 *
 */
public class AttendanceVO {
	private String ad_mem_number;	//회원번호
	private String ad_mt_number;	//회의번호
	public String getAd_mem_number() {
		return ad_mem_number;
	}
	public void setAd_mem_number(String ad_mem_number) {
		this.ad_mem_number = ad_mem_number;
	}
	public String getAd_mt_number() {
		return ad_mt_number;
	}
	public void setAd_mt_number(String ad_mt_number) {
		this.ad_mt_number = ad_mt_number;
	}
}
