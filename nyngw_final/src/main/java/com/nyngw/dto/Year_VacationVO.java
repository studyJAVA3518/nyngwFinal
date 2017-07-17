package com.nyngw.dto;

public class Year_VacationVO {
	private String yv_year;
	private String yv_vacation_day;
	
	public String getYv_year() {
		return yv_year;
	}
	public void setYv_year(String yv_year) {
		this.yv_year = yv_year;
	}
	public String getYv_vacation_day() {
		return yv_vacation_day;
	}
	public void setYv_vacation_day(String yv_vacation_day) {
		this.yv_vacation_day = yv_vacation_day;
	}
	@Override
	public String toString() {
		return "Year_VacationVO [yv_year=" + yv_year + ", yv_vacation_day="
				+ yv_vacation_day + "]";
	}
	
}
