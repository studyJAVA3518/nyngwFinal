package com.nyngw.dto;

/**
 * 공통코드
 * @author pc09
 *
 */
public class Common_CodeVO {
	private String code_number;		//코드번호
	private String code_name;		//코드명
	private String code_ck_number;	//코드종류번호
	
	public String getCode_number() {
		return code_number;
	}
	public void setCode_number(String code_number) {
		this.code_number = code_number;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_ck_number() {
		return code_ck_number;
	}
	public void setCode_ck_number(String code_ck_number) {
		this.code_ck_number = code_ck_number;
	}
	@Override
	public String toString() {
		return "Common_CodeVO [code_number=" + code_number + ", code_name="
				+ code_name + ", code_ck_number=" + code_ck_number + "]";
	}
	
}
