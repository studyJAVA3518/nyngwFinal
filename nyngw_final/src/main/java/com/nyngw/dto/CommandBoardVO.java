package com.nyngw.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 게시판
 * @author pc09
 *
 */
public class CommandBoardVO {
	private String board_number;		//게시글번호
	private String board_code_number;	//게시판구분번호
	private String board_title;			//제목
	private String board_content;		//내용
	private MultipartFile board_file_name;		//파일이름
	private String board_count;			//조회수
	private Date board_date;			//작성날짜
	private String board_mem_number;	//작성자
	private String mem_name;//이름
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
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
	public MultipartFile getBoard_file_name() {
		return board_file_name;
	}
	public void setBoard_file_name(MultipartFile board_file_name) {
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
	
	public BoardVO toBoardVO(){
		BoardVO board = new BoardVO();
		board.setBoard_code_number(board_code_number);
		board.setBoard_content(board_content);
		board.setBoard_count(board_count);
		board.setBoard_date(board_date);
		board.setBoard_file_name(board_file_name.getName());
		board.setBoard_mem_number(board_mem_number);
		board.setBoard_number(board_number);
		board.setBoard_title(board_title);
		board.setMem_name(mem_name);
		return board;
	}
}
