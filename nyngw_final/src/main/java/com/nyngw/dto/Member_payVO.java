package com.nyngw.dto;

public class Member_payVO {
	
	private String mp_number;		//사원 급여정보 고유번호
	private String mp_mem_number;	//사원 고유번호
	private int mp_basic_pay;		//직책별 급여 : 사원 기본급+직책수당+모든직책수당
	private int mp_bonus;			//사원 개별 수당
	private int mp_insurance;		//4대보험료 : (직책별 급여+사원 개별 수당)*4대보험율
	private int mp_final_salary;	//직책별 급여+사원 개별 수당-4대보험료
	
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
	
}
