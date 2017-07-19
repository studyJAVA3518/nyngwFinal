package com.nyngw.dto;

/**
 * 급여 종류
 * @author pc07
 *
 */
public class Pay_kindVO {
	private String pk_number;	//급여 종류 번호
	private String pk_name;		//급여 이름
	private String pk_tax;		//세금유무(y:계산시 -, n:계산시 +또는 *)
	public String getPk_number() {
		return pk_number;
	}
	public void setPk_number(String pk_number) {
		this.pk_number = pk_number;
	}
	public String getPk_name() {
		return pk_name;
	}
	public void setPk_name(String pk_name) {
		this.pk_name = pk_name;
	}
	public String getPk_tax() {
		return pk_tax;
	}
	public void setPk_tax(String pk_tax) {
		this.pk_tax = pk_tax;
	}
	
	
}
