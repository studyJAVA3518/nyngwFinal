package com.nyngw.dto;

import java.util.Date;

/**
 * 댓글
 * @author pc09
 *
 */
public class Board_CommentVO {
	private String comment_number;		//댓글번호
	private String comment_board_number;//게시판번호
	private String comment_content;		//내용
	private Date comment_date;			//작성날짜
	private String comment_mem_number;	//작성자
	public String getComment_number() {
		return comment_number;
	}
	public void setComment_number(String comment_number) {
		this.comment_number = comment_number;
	}
	public String getComment_board_number() {
		return comment_board_number;
	}
	public void setComment_board_number(String comment_board_number) {
		this.comment_board_number = comment_board_number;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_mem_number() {
		return comment_mem_number;
	}
	public void setComment_mem_number(String comment_mem_number) {
		this.comment_mem_number = comment_mem_number;
	}
}
