package com.nyngw.dto;

/**
 * 사원상세정보
 * @author pc09
 *
 */
public class Member_Detail_InformationVO {
	private String mdi_mem_number;		//사원번호
	private String mdi_carear;			//경력사항
	private String mdi_bank;			//거래은행
	private String mdi_bank_account;	//계좌번호
	private String mdi_account_holder;	//예금주
	private String mdi_degree;			//최종학력
	public String getMdi_mem_number() {
		return mdi_mem_number;
	}
	public void setMdi_mem_number(String mdi_mem_number) {
		this.mdi_mem_number = mdi_mem_number;
	}
	public String getMdi_carear() {
		return mdi_carear;
	}
	public void setMdi_carear(String mdi_carear) {
		this.mdi_carear = mdi_carear;
	}
	public String getMdi_bank() {
		return mdi_bank;
	}
	public void setMdi_bank(String mdi_bank) {
		this.mdi_bank = mdi_bank;
	}
	public String getMdi_bank_account() {
		return mdi_bank_account;
	}
	public void setMdi_bank_account(String mdi_bank_account) {
		this.mdi_bank_account = mdi_bank_account;
	}
	public String getMdi_account_holder() {
		return mdi_account_holder;
	}
	public void setMdi_account_holder(String mdi_account_holder) {
		this.mdi_account_holder = mdi_account_holder;
	}
	public String getMdi_degree() {
		return mdi_degree;
	}
	public void setMdi_degree(String mdi_degree) {
		this.mdi_degree = mdi_degree;
	}
}
