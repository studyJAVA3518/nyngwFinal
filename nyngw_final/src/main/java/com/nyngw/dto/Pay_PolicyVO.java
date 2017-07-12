package com.nyngw.dto;

/**
 * 급여정책
 * @author pc09
 *
 */
public class Pay_PolicyVO {
	private String pp_number;			//급여정책번호
	private String pp_pay;				//수당비율
	private String pp_name;				//급여정책명
	private String pp_position_number;	//직급번호
	private String pp_wt_number;		//근무시간번호
	public String getPp_number() {
		return pp_number;
	}
	public void setPp_number(String pp_number) {
		this.pp_number = pp_number;
	}
	public String getPp_pay() {
		return pp_pay;
	}
	public void setPp_pay(String pp_pay) {
		this.pp_pay = pp_pay;
	}
	public String getPp_name() {
		return pp_name;
	}
	public void setPp_name(String pp_name) {
		this.pp_name = pp_name;
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
}
