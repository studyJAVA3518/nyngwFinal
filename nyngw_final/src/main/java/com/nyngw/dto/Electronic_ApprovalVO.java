package com.nyngw.dto;

import java.util.Date;

/**
 * 전자결재
 * @author pc09
 *
 */
public class Electronic_ApprovalVO {
	private String ea_number;		//전자결재번호
	private String ea_title;		//제목
	private String ea_content;		//내용
	private Date ea_startdate;		//시행일
	private Date ea_enddate;		//마감일
	private String ea_doc_number;	//문서번호
	private String ea_mem_number;	//시행자
	private String ea_al_number;	//결재라인번호
	private String ea_status;	//결재라인번호
	private Date ea_approvaldate;	//결재일
	private Date ea_refusaldate;	//반려일
	
	
	public Date getEa_refusaldate() {
		return ea_refusaldate;
	}
	public void setEa_refusaldate(Date ea_refusaldate) {
		this.ea_refusaldate = ea_refusaldate;
	}
	public Date getEa_approvaldate() {
		return ea_approvaldate;
	}
	public void setEa_approvaldate(Date ea_approvaldate) {
		this.ea_approvaldate = ea_approvaldate;
	}
	public String getEa_status() {
		return ea_status;
	}
	public void setEa_status(String ea_status) {
		this.ea_status = ea_status;
	}
	public String getEa_number() {
		return ea_number;
	}
	public void setEa_number(String ea_number) {
		this.ea_number = ea_number;
	}
	public String getEa_title() {
		return ea_title;
	}
	public void setEa_title(String ea_title) {
		this.ea_title = ea_title;
	}
	public String getEa_content() {
		return ea_content;
	}
	public void setEa_content(String ea_content) {
		this.ea_content = ea_content;
	}
	public Date getEa_startdate() {
		return ea_startdate;
	}
	public void setEa_startdate(Date ea_startdate) {
		this.ea_startdate = ea_startdate;
	}
	public Date getEa_enddate() {
		return ea_enddate;
	}
	public void setEa_enddate(Date ea_enddate) {
		this.ea_enddate = ea_enddate;
	}
	public String getEa_doc_number() {
		return ea_doc_number;
	}
	public void setEa_doc_number(String ea_doc_number) {
		this.ea_doc_number = ea_doc_number;
	}
	public String getEa_mem_number() {
		return ea_mem_number;
	}
	public void setEa_mem_number(String ea_mem_number) {
		this.ea_mem_number = ea_mem_number;
	}
	public String getEa_al_number() {
		return ea_al_number;
	}
	public void setEa_al_number(String ea_al_number) {
		this.ea_al_number = ea_al_number;
	}
}
