package com.nyngw.dto;

/**
 * 메모
 * @author pc09
 *
 */
public class MemoVO {
	private String memo_number;		//메모번호
	private String memo_title;		//메모제목
	private String memo_content;	//메모내용
	private String memo_mem_number;	//작성자
	private int startPage;
	private int endPage;
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
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
	public String getMemo_content() {
		return memo_content;
	}
	public void setMemo_content(String memo_content) {
		this.memo_content = memo_content;
	}
	public String getMemo_mem_number() {
		return memo_mem_number;
	}
	public void setMemo_mem_number(String memo_mem_number) {
		this.memo_mem_number = memo_mem_number;
	}
	@Override
	public String toString() {
		return "MemoVO [memo_number=" + memo_number + ", memo_title="
				+ memo_title + ", memo_content=" + memo_content
				+ ", memo_mem_number=" + memo_mem_number + ", startPage="
				+ startPage + ", endPage=" + endPage + "]";
	}
	
}
