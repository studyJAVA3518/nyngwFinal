package com.nyngw.dto;


/**
 * 부서
 * @author pc09
 *
 */
public class DepartmentVO {
	private String dept_number;			//부서번호
	private String dept_name;			//부서명
	private String dept_membernumber;	//부서장사원번호
	private String dept_tel;			//부서연락처
	private String dept_level;			//부서등급
	private String dept_addr;			//주소
	private String dept_parents;		//부모부서명
	public String getDept_number() {
		return dept_number;
	}
	public void setDept_number(String dept_number) {
		this.dept_number = dept_number;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_membernumber() {
		return dept_membernumber;
	}
	public void setDept_membernumber(String dept_membernumber) {
		this.dept_membernumber = dept_membernumber;
	}
	public String getDept_tel() {
		return dept_tel;
	}
	public void setDept_tel(String dept_tel) {
		this.dept_tel = dept_tel;
	}
	public String getDept_level() {
		return dept_level;
	}
	public void setDept_level(String dept_level) {
		this.dept_level = dept_level;
	}
	public String getDept_addr() {
		return dept_addr;
	}
	public void setDept_addr(String dept_addr) {
		this.dept_addr = dept_addr;
	}
	public String getDept_parents() {
		return dept_parents;
	}
	public void setDept_parents(String dept_parents) {
		this.dept_parents = dept_parents;
	}
}
