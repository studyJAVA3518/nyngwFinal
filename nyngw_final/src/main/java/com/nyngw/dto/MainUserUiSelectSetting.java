package com.nyngw.dto;

import java.util.List;

public class MainUserUiSelectSetting {
	private List<String> title1;//제목을 갖고있는 리스트 5개의 이름을 갖는다
	private List<String> title2;//제목을 갖고있는 리스트 3개의 이름을 갖는다
	private List<String> title3;//제목을 갖고있는 리스트 3개의 이름을 갖는다
	private List<MainUserUiSelectVO> content1;//내용을 가지고 있는 리스트 5개의 내용을 갖는다
	private List<MainUserUiSelectVO> content2;//내용을 가지고 있는 리스트 3개의 이름을 갖는다
	private List<MainUserUiSelectVO> content3;//내용을 가지고 있는 리스트 3개의 이름을 갖는다
	private String menu1; //화면에 뿌려지는 테이블이름
	private String menu2;//화면에 뿌려지는 테이블이름
	private String menu3;//화면에 뿌려지는 테이블이름
	private String uriAddr1;//화면에 More에 들어가는 값
	private String uriAddr2;//화면에 More에 들어가는 값
	private String uriAddr3;//화면에 More에 들어가는 값
	public List<String> getTitle1() {
		return title1;
	}
	public void setTitle1(List<String> title1) {
		this.title1 = title1;
	}
	public List<String> getTitle2() {
		return title2;
	}
	public void setTitle2(List<String> title2) {
		this.title2 = title2;
	}
	public List<String> getTitle3() {
		return title3;
	}
	public void setTitle3(List<String> title3) {
		this.title3 = title3;
	}
	public List<MainUserUiSelectVO> getContent1() {
		return content1;
	}
	public void setContent1(List<MainUserUiSelectVO> content1) {
		this.content1 = content1;
	}
	public List<MainUserUiSelectVO> getContent2() {
		return content2;
	}
	public void setContent2(List<MainUserUiSelectVO> content2) {
		this.content2 = content2;
	}
	public List<MainUserUiSelectVO> getContent3() {
		return content3;
	}
	public void setContent3(List<MainUserUiSelectVO> content3) {
		this.content3 = content3;
	}
	public String getMenu1() {
		return menu1;
	}
	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}
	public String getMenu2() {
		return menu2;
	}
	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}
	public String getMenu3() {
		return menu3;
	}
	public void setMenu3(String menu3) {
		this.menu3 = menu3;
	}
	public String getUriAddr1() {
		return uriAddr1;
	}
	public void setUriAddr1(String uriAddr1) {
		this.uriAddr1 = uriAddr1;
	}
	public String getUriAddr2() {
		return uriAddr2;
	}
	public void setUriAddr2(String uriAddr2) {
		this.uriAddr2 = uriAddr2;
	}
	public String getUriAddr3() {
		return uriAddr3;
	}
	public void setUriAddr3(String uriAddr3) {
		this.uriAddr3 = uriAddr3;
	}
}
