package com.nyngw.dto;

public class Member_payViewVO {
	
	private String mp_number;			//사원 급여정보 고유번호
	private String mp_mem_number;		//사원 고유번호
	private int mp_basic_pay;			//직책별 급여 : 사원 기본급+직책수당+모든직책수당
	private int mp_bonus;				//사원 개별 수당
	private int mp_insurance;			//4대보험료 : (직책별 급여+사원 개별 수당)*4대보험율
	private int mp_final_salary;		//직책별 급여+사원 개별 수당-4대보험료
		
	private String mem_number;			//사원 고유번호		
	private String mem_name;			//사원이름
	private String mem_dept_number;		//부서 고유번호
	private String mem_position_number;	//직책 고유번호
	
	private String dept_number;			//부서 고유번호
	private String dept_name;			//부서 이름
	
	private String position_number;		//직책 고유번호
	private String position_name;		//직책 이름
	
	public String getMp_number() {
		return mp_number;
	}
	public void setMp_number(String mp_number) {
		this.mp_number = mp_number;
	}
	public String getMp_mem_number() {
		return mp_mem_number;
	}
	public void setMp_mem_number(String mp_mem_number) {
		this.mp_mem_number = mp_mem_number;
	}
	public int getMp_basic_pay() {
		return mp_basic_pay;
	}
	public void setMp_basic_pay(int mp_basic_pay) {
		this.mp_basic_pay = mp_basic_pay;
	}
	public int getMp_bonus() {
		return mp_bonus;
	}
	public void setMp_bonus(int mp_bonus) {
		this.mp_bonus = mp_bonus;
	}
	public int getMp_insurance() {
		return mp_insurance;
	}
	public void setMp_insurance(int mp_insurance) {
		this.mp_insurance = mp_insurance;
	}
	public int getMp_final_salary() {
		return mp_final_salary;
	}
	public void setMp_final_salary(int mp_final_salary) {
		this.mp_final_salary = mp_final_salary;
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
	public String getMem_dept_number() {
		return mem_dept_number;
	}
	public void setMem_dept_number(String mem_dept_number) {
		this.mem_dept_number = mem_dept_number;
	}
	public String getMem_position_number() {
		return mem_position_number;
	}
	public void setMem_position_number(String mem_position_number) {
		this.mem_position_number = mem_position_number;
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
	
}
