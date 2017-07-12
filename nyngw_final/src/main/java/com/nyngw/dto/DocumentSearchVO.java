package com.nyngw.dto;


public class DocumentSearchVO {
	
	private String doc_name;
	private String doc_explanation;
	private String doc_mem_number;
	private String doc_date;
	private String searchText;
	private String draftBoxOption;
	
	
	public String getDraftBoxOption() {
		return draftBoxOption;
	}
	public void setDraftBoxOption(String draftBoxOption) {
		this.draftBoxOption = draftBoxOption;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_explanation() {
		return doc_explanation;
	}
	public void setDoc_explanation(String doc_explanation) {
		this.doc_explanation = doc_explanation;
	}
	public String getDoc_mem_number() {
		return doc_mem_number;
	}
	public void setDoc_mem_number(String doc_mem_number) {
		this.doc_mem_number = doc_mem_number;
	}
	public String getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
