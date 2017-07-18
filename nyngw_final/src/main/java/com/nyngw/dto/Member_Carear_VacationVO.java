package com.nyngw.dto;

public class Member_Carear_VacationVO {
	private String mem_number;
	private String mem_name;
	private String mem_id;
	private String dept_name; 
	private String mem_dept_number;
	private String position_name;
	private String mem_carear;
	private String vp_totalday;
	private String year;
	private int nouse_vacation;
	private int use_vacation;
	private int startPage;
	private int endPage;
	
	public int getNouse_vacation() {
		return nouse_vacation;
	}
	public void setNouse_vacation(int nouse_vacation) {
		this.nouse_vacation = nouse_vacation;
	}
	public int getUse_vacation() {
		return use_vacation;
	}
	public void setUse_vacation(int use_vacation) {
		this.use_vacation = use_vacation;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getMem_dept_number() {
		return mem_dept_number;
	}
	public void setMem_dept_number(String mem_dept_number) {
		this.mem_dept_number = mem_dept_number;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getMem_carear() {
		return mem_carear;
	}
	public void setMem_carear(String mem_carear) {
		this.mem_carear = mem_carear;
	}
	public String getVp_totalday() {
		return vp_totalday;
	}
	public void setVp_totalday(String vp_totalday) {
		this.vp_totalday = vp_totalday;
	}
	@Override
	public String toString() {
		return "Member_Carear_VacationVO [mem_number=" + mem_number
				+ ", mem_name=" + mem_name + ", mem_id=" + mem_id
				+ ", dept_name=" + dept_name + ", mem_dept_number="
				+ mem_dept_number + ", position_name=" + position_name
				+ ", mem_carear=" + mem_carear + ", vp_totalday=" + vp_totalday
				+ ", year=" + year + ", nouse_vacation=" + nouse_vacation
				+ ", use_vacation=" + use_vacation + ", startPage=" + startPage
				+ ", endPage=" + endPage + "]";
	}

	
	
}
