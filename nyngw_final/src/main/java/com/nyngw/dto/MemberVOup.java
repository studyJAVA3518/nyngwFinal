package com.nyngw.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * 사원 테이블
 * @author pc09
 *
 */
public class MemberVOup {
	
	private MultipartFile mem_imgup;		//사진
	private MultipartFile mem_signup;	//서명
	
	
	public MultipartFile getMem_imgup() {
		return mem_imgup;
	}
	public void setMem_imgup(MultipartFile mem_imgup) {
		this.mem_imgup = mem_imgup;
	}
	public MultipartFile getMem_signup() {
		return mem_signup;
	}
	public void setMem_signup(MultipartFile mem_signup) {
		this.mem_signup = mem_signup;
	}
	
	
	public MemberVO toMemberVO(){
		MemberVO mem = new MemberVO();
		mem.setMem_img(mem_imgup.getName());
		mem.setMem_sign(mem_signup.getName());
		return mem;
	}
}
