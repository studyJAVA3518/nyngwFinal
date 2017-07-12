package com.nyngw.dto;

/**
 * 휴가정책
 * @author pc09
 *
 */
public class Vacation_PolicyVO {
	private String vp_number;	//휴가정책번호
	private String vp_kind;		//휴가종류
	private String vp_totalday;	//휴가일수
	private String vp_payonoff;	//급여유무
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
}
