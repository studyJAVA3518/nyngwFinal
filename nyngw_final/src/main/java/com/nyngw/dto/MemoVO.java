package com.nyngw.dto;

/**
 * 메모
 * @author pc09
 *
 */
public class MemoVO {
	private String memo_number;		//메모번호
	private String memo_title;		//메모제목
	private String mem0_content;	//메모내용
	private String memo_mem_number;	//작성자
	public String getMemo_number() {
		return memo_number;
	}
	public void setMemo_number(String memo_number) {
		this.memo_number = memo_number;
	}
	public String getMemo_title() {
		return memo_title;
	}
	public void setMemo_title(String memo_title) {
		this.memo_title = memo_title;
	}
	public String getMem0_content() {
		return mem0_content;
	}
	public void setMem0_content(String mem0_content) {
		this.mem0_content = mem0_content;
	}
	public String getMemo_mem_number() {
		return memo_mem_number;
	}
	public void setMemo_mem_number(String memo_mem_number) {
		this.memo_mem_number = memo_mem_number;
	}
}
