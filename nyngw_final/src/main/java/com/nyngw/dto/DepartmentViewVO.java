package com.nyngw.dto;


/**
 * 부서관련 뷰
 * @author jiho
 *
 */
public class DepartmentViewVO {
	private String dept_number;			//부서번호
	private String dept_name;			//부서명
	private String dept_membernumber;	//부서장사원번호
	private String dept_tel;			//부서연락처
	private String dept_addr;			//주소
	private String dept_parents;		//부모부서명
	
	private long dept_level;				//부서등급
	private String mem_number;			//사원번호
	private String mem_name;			//사원이름
	private String mem_position_number;	//사원이름
	private String position_number;		//부서번호
	private String position_name;		//부서 이름
	
	
	public long getDept_level() {
		return dept_level;
	}
	public void setDept_level(long dept_level) {
		this.dept_level = dept_level;
	}
	public String getMem_number() {
		return mem_number;
	}
	public void setMem_number(String mem_number) {
		this.mem_number = mem_number;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_position_number() {
		return mem_position_number;
	}
	public void setMem_position_number(String mem_position_number) {
		this.mem_position_number = mem_position_number;
	}
	public String getPosition_number() {
		return position_number;
	}
	public void setPosition_number(String position_number) {
		this.position_number = position_number;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	
	
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
