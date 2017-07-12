package com.nyngw.dto;


public class DocumentViewVO {
	private String dv_code_name; //문서종류
	private	String dv_doc_date;//등록일
	private String dv_doc_name;//문서명
	private String dv_doc_number;//문서번호
	private String dv_mem_name;//등록자
	public String getDv_code_name() {
		return dv_code_name;
	}
	public void setDv_code_name(String dv_code_name) {
		this.dv_code_name = dv_code_name;
	}
	public String getDv_doc_date() {
		return dv_doc_date;
	}
	public void setDv_doc_date(String dv_doc_date) {
		this.dv_doc_date = dv_doc_date;
	}
	public String getDv_doc_name() {
		return dv_doc_name;
	}
	public void setDv_doc_name(String dv_doc_name) {
		this.dv_doc_name = dv_doc_name;
	}
	public String getDv_doc_number() {
		return dv_doc_number;
	}
	public void setDv_doc_number(String dv_doc_number) {
		this.dv_doc_number = dv_doc_number;
	}
	public String getDv_mem_name() {
		return dv_mem_name;
	}
	public void setDv_mem_name(String dv_mem_name) {
		this.dv_mem_name = dv_mem_name;
	}
	
	
}
