package com.nyngw.dto;

import java.io.Serializable;

/**
 * 회사정보
 * @author pc09
 *
 */
public class CompanyVO implements Serializable {
	private static final long serialVersionUID = -7667147479819193393L;
	private long id;
	private String company_name;	//회사명
	private String company_logo;	//회사로고
	private String company_tel;		//전화번호
	
	//수정됨
	private String company_addr1;	//주소1
	private String company_addr2;	//주소2
	private String company_zip;		//우편번호
	//수정됨
	private String company_number;	//회사번호
	//수정됨 --끝
	
	
	public String getCompany_name() {
		return company_name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_logo() {
		return company_logo;
	}
	public void setCompany_logo(String company_logo) {
		this.company_logo = company_logo;
	}
	public String getCompany_tel() {
		return company_tel;
	}
	public void setCompany_tel(String company_tel) {
		this.company_tel = company_tel;
	}
	public String getCompany_addr1() {
		return company_addr1;
	}
	
	//수정됨
	public void setCompany_addr1(String company_addr1) {
		this.company_addr1 = company_addr1;
	}
	public String getCompany_addr2() {
		return company_addr2;
	}
	public void setCompany_addr2(String company_addr2) {
		this.company_addr2 = company_addr2;
	}
	public String getCompany_zip() {
		return company_zip;
	}
	public void setCompany_zip(String company_zip) {
		this.company_zip = company_zip;
	}
	//수정됨
	public String getCompany_number() {
		return company_number;
	}
	public void setCompany_number(String company_number) {
		this.company_number = company_number;
	}
	//수정됨 --끝
	@Override
	public String toString() {
		return "CompanyVO [id=" + id + ", company_name=" + company_name
				+ ", company_logo=" + company_logo + ", company_tel="
				+ company_tel + ", company_addr1=" + company_addr1
				+ ", company_addr2=" + company_addr2 + ", company_zip="
				+ company_zip + ", company_number=" + company_number + "]";
	}
	
	
}
