package com.nyngw.dto;

import java.util.List;

public class Electronic_ApprovalViewVO {
	private List<Electronic_ApprovalVO> sangsinList;
	private int documentTotalCount;
	private int currentPageNumber;
	private int pageTotalCount;
	private int documentCountPerPage;
	private int firstRow;
	private int endRow;
	
	public List<Electronic_ApprovalVO> getSangsinList() {
		return sangsinList;
	}

	public void setSangsinList(List<Electronic_ApprovalVO> sangsinList) {
		this.sangsinList = sangsinList;
	}

	public Electronic_ApprovalViewVO(List<Electronic_ApprovalVO> sangsinList, int documentTotalCount,
			int currentPageNumber, int documentCountPerPage, int startRow,
			int endRow) {
		this.sangsinList = sangsinList;
		this.documentTotalCount = documentTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.documentCountPerPage = documentCountPerPage;
		this.firstRow = startRow;
		this.endRow = endRow;

		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if (documentTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = documentTotalCount / documentCountPerPage;
			if (documentTotalCount % documentCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}
	public int getDocumentTotalCount() {
		return documentTotalCount;
	}
	public void setDocumentTotalCount(int documentTotalCount) {
		this.documentTotalCount = documentTotalCount;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
	public int getDocumentCountPerPage() {
		return documentCountPerPage;
	}
	public void setDocumentCountPerPage(int documentCountPerPage) {
		this.documentCountPerPage = documentCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
}
