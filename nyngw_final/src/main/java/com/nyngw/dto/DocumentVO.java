package com.nyngw.dto;

import java.util.Date;

/**
 * 문서
 * @author pc09
 *
 */
public class DocumentVO {
	private String doc_number;		//문서번호
	private String doc_name;		//문서명
	private Date doc_date;			//등록일
	private String doc_file_name;	//파일이름
	private String doc_explanation;	//문서설명
	private Date doc_lifetime; 		//보존기간
	private String doc_code_number;	//문서구분번호
	private String doc_mem_number;	//등록자
	private String doc_eadoc;       //전자결재문서 유무
	private String mem_name;        // 사원명
	
	
	
	public String getDoc_eadoc() {
		return doc_eadoc;
	}
	public void setDoc_eadoc(String doc_eadoc) {
		this.doc_eadoc = doc_eadoc;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public Date getDoc_lifetime() {
		return doc_lifetime;
	}
	public void setDoc_lifetime(Date doc_lifetime) {
		this.doc_lifetime = doc_lifetime;
	}
	public String getDoc_number() {
		return doc_number;
	}
	public void setDoc_number(String doc_number) {
		this.doc_number = doc_number;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public Date getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}
	public String getDoc_file_name() {
		return doc_file_name;
	}
	public void setDoc_file_name(String doc_file_name) {
		this.doc_file_name = doc_file_name;
	}
	public String getDoc_explanation() {
		return doc_explanation;
	}
	public void setDoc_explanation(String doc_explanation) {
		this.doc_explanation = doc_explanation;
	}
	public String getDoc_code_number() {
		return doc_code_number;
	}
	public void setDoc_code_number(String doc_code_number) {
		this.doc_code_number = doc_code_number;
	}
	public String getDoc_mem_number() {
		return doc_mem_number;
	}
	public void setDoc_mem_number(String doc_mem_number) {
		this.doc_mem_number = doc_mem_number;
	}
}
