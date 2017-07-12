package com.nyngw.dto;

/**
 * 회의실
 * @author pc09
 *
 */
public class MeetingRoomVO {
	private String mr_number;		//회의실번호
	private String mr_name; 		//회의실명
	private String mr_roomnumber;	//회의실호수
	public String getMr_number() {
		return mr_number;
	}
	public void setMr_number(String mr_number) {
		this.mr_number = mr_number;
	}
	public String getMr_name() {
		return mr_name;
	}
	public void setMr_name(String mr_name) {
		this.mr_name = mr_name;
	}
	public String getMr_roomnumber() {
		return mr_roomnumber;
	}
	public void setMr_roomnumber(String mr_roomnumber) {
		this.mr_roomnumber = mr_roomnumber;
	}
}
