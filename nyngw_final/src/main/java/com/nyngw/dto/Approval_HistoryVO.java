package com.nyngw.dto;

import java.util.Date;

public class Approval_HistoryVO {
	private String ah_number;
	private String ah_code_number;
	private String ah_ast_number;
	private String ah_ea_number;
	private String ah_comment;
	private Date ah_time;//결재한 시간
	private String ah_status;//결재상태
	
	public Date getAh_time() {
		return ah_time;
	}
	public String getAh_status() {
		return ah_status;
	}
	public void setAh_status(String ah_status) {
		this.ah_status = ah_status;
	}
	public void setAh_time(Date ah_time) {
		this.ah_time = ah_time;
	}
	public String getAh_number() {
		return ah_number;
	}
	public void setAh_number(String ah_number) {
		this.ah_number = ah_number;
	}
	public String getAh_code_number() {
		return ah_code_number;
	}
	public void setAh_code_number(String ah_code_number) {
		this.ah_code_number = ah_code_number;
	}
	public String getAh_ast_number() {
		return ah_ast_number;
	}
	public void setAh_ast_number(String ah_ast_number) {
		this.ah_ast_number = ah_ast_number;
	}
	public String getAh_ea_number() {
		return ah_ea_number;
	}
	public void setAh_ea_number(String ah_ea_number) {
		this.ah_ea_number = ah_ea_number;
	}
	public String getAh_comment() {
		return ah_comment;
	}
	public void setAh_comment(String ah_comment) {
		this.ah_comment = ah_comment;
	}
}
