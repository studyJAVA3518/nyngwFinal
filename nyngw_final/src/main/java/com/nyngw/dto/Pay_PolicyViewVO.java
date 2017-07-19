package com.nyngw.dto;

/**
 * 급여정책 뷰
 * @author pc07
 *
 */
public class Pay_PolicyViewVO {
	
	private String pp_number;			//급여정책번호
	private float pp_pay;				//수당비율
	private String pp_position_number;	//직급번호
	private String pp_wt_number;		//근무시간번호
	private String pp_pk_number;		//급여종류번호
	private String pk_number;			//급여종류번호
	private String pk_name;				//급여종류이름
	private String pk_tax;				//급여텍스유무
	private String position_number;		//직급번호
	private String position_name;		//직급이름
	private String wt_number;			//근무시간번호
	private String wt_name;				//근무시간이름
	
	public String getPp_number() {
		return pp_number;
	}
	public void setPp_number(String pp_number) {
		this.pp_number = pp_number;
	}
	public float getPp_pay() {
		return pp_pay;
	}
	public void setPp_pay(float pp_pay) {
		this.pp_pay = pp_pay;
	}
	public String getPp_position_number() {
		return pp_position_number;
	}
	public void setPp_position_number(String pp_position_number) {
		this.pp_position_number = pp_position_number;
	}
	public String getPp_wt_number() {
		return pp_wt_number;
	}
	public void setPp_wt_number(String pp_wt_number) {
		this.pp_wt_number = pp_wt_number;
	}
	public String getPp_pk_number() {
		return pp_pk_number;
	}
	public void setPp_pk_number(String pp_pk_number) {
		this.pp_pk_number = pp_pk_number;
	}
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
	public String getWt_number() {
		return wt_number;
	}
	public void setWt_number(String wt_number) {
		this.wt_number = wt_number;
	}
	public String getWt_name() {
		return wt_name;
	}
	public void setWt_name(String wt_name) {
		this.wt_name = wt_name;
	}
	
}
