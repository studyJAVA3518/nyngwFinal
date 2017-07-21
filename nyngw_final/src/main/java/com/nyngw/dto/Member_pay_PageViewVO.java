package com.nyngw.dto;

import java.util.List;

public class Member_pay_PageViewVO {
	
	private List<Member_payViewVO> memberPayViewList;
	private int memberTotalCount;
	private int currentPageNumber;
	private int pageTotalCount;
	private int memberPayViewCountPerPage;
	private int firstRow;
	private int endRow;

	public Member_pay_PageViewVO(List<Member_payViewVO> memberPayViewList,
			int memberTotalCount, int currentPageNumber,
			int memberPayViewCountPerPage, int firstRow, int endRow) {
		this.memberPayViewList = memberPayViewList;
		this.memberTotalCount = memberTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.memberPayViewCountPerPage = memberPayViewCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	
	private void calculatePageTotalCount() {
		if (memberTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = memberTotalCount / memberPayViewCountPerPage;
			if (memberTotalCount % memberPayViewCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}
	
	
	public List<Member_payViewVO> getMemberPayViewList() {
		return memberPayViewList;
	}

	public void setMemberPayViewList(List<Member_payViewVO> memberPayViewList) {
		this.memberPayViewList = memberPayViewList;
	}

	public int getMemberPayViewCountPerPage() {
		return memberPayViewCountPerPage;
	}
	public void setMemberPayViewCountPerPage(int memberPayViewCountPerPage) {
		this.memberPayViewCountPerPage = memberPayViewCountPerPage;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
	public int getMemberTotalCount() {
		return memberTotalCount;
	}
	public void setMemberTotalCount(int memberTotalCount) {
		this.memberTotalCount = memberTotalCount;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
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
