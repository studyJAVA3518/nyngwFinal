package com.nyngw.dto;

import java.util.Date;

/**
 * 게시판
 * @author pc09
 *
 */
public class BoardVO {
	private String board_number;		//게시글번호
	private String board_code_number;	//게시판구분번호
	private String board_title;			//제목
	private String board_content;		//내용
	private String board_file_name;		//파일이름
	private String board_count;			//조회수
	private Date board_date;			//작성날짜
	private String board_mem_number;	//작성자
	public String getBoard_number() {
		return board_number;
	}
	public void setBoard_number(String board_number) {
		this.board_number = board_number;
	}
	public String getBoard_code_number() {
		return board_code_number;
	}
	public void setBoard_code_number(String board_code_number) {
		this.board_code_number = board_code_number;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_file_name() {
		return board_file_name;
	}
	public void setBoard_file_name(String board_file_name) {
		this.board_file_name = board_file_name;
	}
	public String getBoard_count() {
		return board_count;
	}
	public void setBoard_count(String board_count) {
		this.board_count = board_count;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_mem_number() {
		return board_mem_number;
	}
	public void setBoard_mem_number(String board_mem_number) {
		this.board_mem_number = board_mem_number;
	}
}
