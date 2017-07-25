package com.nyngw.dto;

import java.util.Date;

public class CommonApproval_TOTALVO {
	private String ah_number;
	private String ah_code_number;
	private String ah_ast_number;
	private String ah_ea_number;
	private String ah_comment;
	private Date ah_time;//결재한 시간
	private String ah_status;//결재상태
	private String al_number;			//결재라인번호
	private String al_mem_number;		//사원번호
	private String al_name;				//결재라인명
	private String al_approvalmember1;	//결재자1
	private String al_approvalmember2;	//결재자2
	private String al_approvalmember3;	//결재자3
	private String al_approvalmember4;	//결재자4
	private String al_approvalmember5;	//결재자5
	private String al_receive;			//수신자
	private String al_reference;		//참조자
	private String al_enforcement;		//시행자
	private String ast_number;
	private String ast_ea_number;
	private int ast_priority;
	private String ast_al_number;
	private String ast_mem_number;
	private String ea_number;		//전자결재번호
	private String ea_title;		//제목
	private String ea_content;		//내용
	private Date ea_startdate;		//시행일,기안일
	private Date ea_enddate;		//마감일
	private String ea_doc_number;	//문서번호
	private String ea_mem_number;	//시행자
	private Date ea_writedate;		//기안일
	private Date ea_ah_time; //결재이력 날짜..
	private String mem_name;
	private String doc_name;
	private String mem_number;
	private String dept_name;
	private String position_name;
	
	public String getMem_number() {
		return mem_number;
	}
	public void setMem_number(String mem_number) {
		this.mem_number = mem_number;
	}
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
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	public Date getAh_time() {
		return ah_time;
	}
	public void setAh_time(Date ah_time) {
		this.ah_time = ah_time;
	}
	public String getAh_status() {
		return ah_status;
	}
	public void setAh_status(String ah_status) {
		this.ah_status = ah_status;
	}
	public String getAl_number() {
		return al_number;
	}
	public void setAl_number(String al_number) {
		this.al_number = al_number;
	}
	public String getAl_mem_number() {
		return al_mem_number;
	}
	public void setAl_mem_number(String al_mem_number) {
		this.al_mem_number = al_mem_number;
	}
	public String getAl_name() {
		return al_name;
	}
	public void setAl_name(String al_name) {
		this.al_name = al_name;
	}
	public String getAl_approvalmember1() {
		return al_approvalmember1;
	}
	public void setAl_approvalmember1(String al_approvalmember1) {
		this.al_approvalmember1 = al_approvalmember1;
	}
	public String getAl_approvalmember2() {
		return al_approvalmember2;
	}
	public void setAl_approvalmember2(String al_approvalmember2) {
		this.al_approvalmember2 = al_approvalmember2;
	}
	public String getAl_approvalmember3() {
		return al_approvalmember3;
	}
	public void setAl_approvalmember3(String al_approvalmember3) {
		this.al_approvalmember3 = al_approvalmember3;
	}
	public String getAl_approvalmember4() {
		return al_approvalmember4;
	}
	public void setAl_approvalmember4(String al_approvalmember4) {
		this.al_approvalmember4 = al_approvalmember4;
	}
	public String getAl_approvalmember5() {
		return al_approvalmember5;
	}
	public void setAl_approvalmember5(String al_approvalmember5) {
		this.al_approvalmember5 = al_approvalmember5;
	}
	public String getAl_receive() {
		return al_receive;
	}
	public void setAl_receive(String al_receive) {
		this.al_receive = al_receive;
	}
	public String getAl_reference() {
		return al_reference;
	}
	public void setAl_reference(String al_reference) {
		this.al_reference = al_reference;
	}
	public String getAl_enforcement() {
		return al_enforcement;
	}
	public void setAl_enforcement(String al_enforcement) {
		this.al_enforcement = al_enforcement;
	}
	public String getAst_number() {
		return ast_number;
	}
	public void setAst_number(String ast_number) {
		this.ast_number = ast_number;
	}
	public String getAst_ea_number() {
		return ast_ea_number;
	}
	public void setAst_ea_number(String ast_ea_number) {
		this.ast_ea_number = ast_ea_number;
	}
	public int getAst_priority() {
		return ast_priority;
	}
	public void setAst_priority(int ast_priority) {
		this.ast_priority = ast_priority;
	}
	public String getAst_al_number() {
		return ast_al_number;
	}
	public void setAst_al_number(String ast_al_number) {
		this.ast_al_number = ast_al_number;
	}
	public String getAst_mem_number() {
		return ast_mem_number;
	}
	public void setAst_mem_number(String ast_mem_number) {
		this.ast_mem_number = ast_mem_number;
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
	public Date getEa_writedate() {
		return ea_writedate;
	}
	public void setEa_writedate(Date ea_writedate) {
		this.ea_writedate = ea_writedate;
	}
	public Date getEa_ah_time() {
		return ea_ah_time;
	}
	public void setEa_ah_time(Date ea_ah_time) {
		this.ea_ah_time = ea_ah_time;
	}
	
	
}
