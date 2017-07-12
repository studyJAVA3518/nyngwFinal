package com.nyngw.dto;

/**
 * 회사정보
 * @author pc09
 *
 */
public class CompanyVO {
	private String company_name;	//회사명
	private String company_logo;	//회사로고
	private String company_tel;		//전화번호
	private String company_addr;	//주소
	public String getCompany_name() {
		return company_name;
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
	public String getCompany_addr() {
		return company_addr;
	}
	public void setCompany_addr(String company_addr) {
		this.company_addr = company_addr;
	}
}
