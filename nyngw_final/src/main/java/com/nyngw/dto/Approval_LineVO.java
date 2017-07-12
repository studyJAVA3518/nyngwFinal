package com.nyngw.dto;

/**
 * 결재라인
 * @author pc09
 *
 */
public class Approval_LineVO {
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
	public String getAl_number() {
		return al_number;
	}
	public void setAl_number(String al_number) {
		this.al_number = al_number;
	}
	public String getAl_name() {
		return al_name;
	}
	public void setAl_name(String al_name) {
		this.al_name = al_name;
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
	public String getAl_mem_number() {
		return al_mem_number;
	}
	public void setAl_mem_number(String al_mem_number) {
		this.al_mem_number = al_mem_number;
	}
}
