package com.nyngw.dto;

import java.util.Date;

public class JoinMemberVO {
	
	private String mem_number;
	private String mem_id;
	private String mem_pwd;
	private String mem_name;
	private Date mem_birthday;
	private String mem_reg;
	private String mem_zip;
	private String mem_addr1;
	private String mem_addr2;
	private String mem_email;
	private String mem_retirement;	
	private String mem_tel;
	private String mdi_bank;
	private String mdi_bank_account;
	private String mem_dept_number;
	private String mem_position_number;
	private String dept_name;
	private String position_name;	//직급명
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_reg() {
		return mem_reg;
	}
	public void setMem_reg(String mem_reg) {
		this.mem_reg = mem_reg;
	}
	public String getMem_zip() {
		return mem_zip;
	}
	public void setMem_zip(String mem_zip) {
		this.mem_zip = mem_zip;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_retirement() {
		return mem_retirement;
	}
	public void setMem_retirement(String mem_retirement) {
		this.mem_retirement = mem_retirement;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
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
	public String getMem_dept_number() {
		return mem_dept_number;
	}
	public void setMem_dept_number(String mem_dept_number) {
		this.mem_dept_number = mem_dept_number;
	}
	public String getMem_position_number() {
		return mem_position_number;
	}
	public void setMem_position_number(String mem_position_number) {
		this.mem_position_number = mem_position_number;
	}
	
	public Date getMem_birthday() {
		return mem_birthday;
	}
	public void setMem_birthday(Date date) {
		this.mem_birthday = date;
	}
	@Override
	public String toString() {
		return "JoinMemberVO [mem_number=" + mem_number + ", mem_id=" + mem_id
				+ ", mem_pwd=" + mem_pwd + ", mem_name=" + mem_name
				+ ", mem_birthday=" + mem_birthday + ", mem_reg=" + mem_reg
				+ ", mem_zip=" + mem_zip + ", mem_addr1=" + mem_addr1
				+ ", mem_addr2=" + mem_addr2 + ", mem_email=" + mem_email
				+ ", mem_retirement=" + mem_retirement + ", mem_tel=" + mem_tel
				+ ", mdi_bank=" + mdi_bank + ", mdi_bank_account="
				+ mdi_bank_account + ", mem_dept_number=" + mem_dept_number
				+ ", mem_position_number=" + mem_position_number + "]";
	}

}
