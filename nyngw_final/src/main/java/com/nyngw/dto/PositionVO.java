package com.nyngw.dto;

/**
 * 직급
 * @author pc09
 *
 */
public class PositionVO {
	private String position_number;	//직급번호
	private String position_name;	//직급명
	
	//수정
	private int position_level;
	public int getPosition_level() {
		return position_level;
	}
	public void setPosition_level(int position_level) {
		this.position_level = position_level;
	}
	//수정완료
	
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
