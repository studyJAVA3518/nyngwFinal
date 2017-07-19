package com.nyngw.dto;

/**
 * 급여정책
 * @author pc09
 *
 */
public class Pay_PolicyVO {
	
	//전체적으로 수정했어요~
	private String pp_number;			//급여정책번호
	private float pp_pay;					//수당비율
	private String pp_position_number;	//직급번호
	private String pp_wt_number;		//근무시간번호
	private String pp_pk_number;		//급여종류번호
	
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
	
}
