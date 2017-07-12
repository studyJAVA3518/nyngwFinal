package com.nyngw.dto;

import java.util.Date;

/**
 * 사원 테이블
 * @author pc09
 *
 */
public class MemberVO {
	private String mem_number;	//사원번호
	private String mem_id;		//사원id
	private String mem_pwd;		//패스워드
	private String mem_name;	//이름
	private Date mem_birthday;//생일
	private String mem_reg;		//주민번호
	private String mem_addr1;	//주소
	private String mem_addr2;	//상세주소
	private String mem_zip;		//우편번호
	private String mem_tel;		//연락처
	private String mem_email;	//이메일
	private String mem_img;		//사진
	private String mem_sign;	//서명
	private String mem_retirement;//퇴사여부
	private String mem_favorites;//즐겨찾기
	private String mem_dept_number;//부서번호
	private String mem_mngr_number;//관리자설정번호
	private String mem_position_number;//직급번호
	
	//-->추가
	private String dept_name;			//부서 이름
	private String position_name;		//직급 이름
	
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
	//-->추가 끝
	
	public String getMem_number() {
		return mem_number;
	}
	public void setMem_number(String mem_number) {
		this.mem_number = mem_number;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public Date getMem_birthday() {
		return mem_birthday;
	}
	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}
	public String getMem_reg() {
		return mem_reg;
	}
	public void setMem_reg(String mem_reg) {
		this.mem_reg = mem_reg;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public String getMem_zip() {
		return mem_zip;
	}
	public void setMem_zip(String mem_zip) {
		this.mem_zip = mem_zip;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_img() {
		return mem_img;
	}
	public void setMem_img(String mem_img) {
		this.mem_img = mem_img;
	}
	public String getMem_sign() {
		return mem_sign;
	}
	public void setMem_sign(String mem_sign) {
		this.mem_sign = mem_sign;
	}
	public String getMem_retirement() {
		return mem_retirement;
	}
	public void setMem_retirement(String mem_retirement) {
		this.mem_retirement = mem_retirement;
	}
	public String getMem_favorites() {
		return mem_favorites;
	}
	public void setMem_favorites(String mem_favorites) {
		this.mem_favorites = mem_favorites;
	}
	public String getMem_dept_number() {
		return mem_dept_number;
	}
	public void setMem_dept_number(String mem_dept_number) {
		this.mem_dept_number = mem_dept_number;
	}
	public String getMem_mngr_number() {
		return mem_mngr_number;
	}
	public void setMem_mngr_number(String mem_mngr_number) {
		this.mem_mngr_number = mem_mngr_number;
	}
	public String getMem_position_number() {
		return mem_position_number;
	}
	public void setMem_position_number(String mem_position_number) {
		this.mem_position_number = mem_position_number;
	}
}
