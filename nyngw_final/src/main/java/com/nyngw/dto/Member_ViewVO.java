package com.nyngw.dto;

public class Member_ViewVO {
	
	private String mem_number;	//사원번호
	private String mem_id;    //회원 아이디
	private String mem_name;  //회원 이름
	private String mem_position_number;  //회원 직책 번호
	private String mem_dept_number;  //회원 직책 번호
	private String position_name; //직책
	private String dept_name; //부서명
	private String dal_date;			//날짜
	private String startdal_date;			//날짜
	private String enddal_date;			//날짜
	private String dal_content;			//사유
	private String mdi_bank;			//거래은행
	private String mdi_bank_account;	//계좌번호
	private String mdi_account_holder;	//예금주
	private int startPage;
	private int endPage;
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getMem_number() {
		return mem_number;
	}
	public void setMem_number(String mem_number) {
		this.mem_number = mem_number;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDal_date() {
		return dal_date;
	}
	public void setDal_date(String dal_date) {
		this.dal_date = dal_date;
	}
	public String getStartdal_date() {
		return startdal_date;
	}
	public void setStartdal_date(String startdal_date) {
		this.startdal_date = startdal_date;
	}
	public String getEnddal_date() {
		return enddal_date;
	}
	public void setEnddal_date(String enddal_date) {
		this.enddal_date = enddal_date;
	}
	public String getDal_content() {
		return dal_content;
	}
	public void setDal_content(String dal_content) {
		this.dal_content = dal_content;
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
	public String getMem_position_number() {
		return mem_position_number;
	}
	public void setMem_position_number(String mem_position_number) {
		this.mem_position_number = mem_position_number;
	}
	public String getMem_dept_number() {
		return mem_dept_number;
	}
	public void setMem_dept_number(String mem_dept_number) {
		this.mem_dept_number = mem_dept_number;
	}
}
