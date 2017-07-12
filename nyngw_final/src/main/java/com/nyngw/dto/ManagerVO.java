package com.nyngw.dto;

/**
 * 관리자설정
 * @author pc09
 *
 */
public class ManagerVO {
	private String mngr_number; //관리자설정번호
	private String mngr_name; //관리자명
	public String getMngr_number() {
		return mngr_number;
	}
	public void setMngr_number(String mngr_number) {
		this.mngr_number = mngr_number;
	}
	public String getMngr_name() {
		return mngr_name;
	}
	public void setMngr_name(String mngr_name) {
		this.mngr_name = mngr_name;
	}
}
