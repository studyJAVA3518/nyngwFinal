package com.nyngw.dto;

import java.util.List;

public class BirthdayViewVO {
	private List<BirthdayVO> birthdayList;
	private int memberTotalCount;
	private int currentPageNumber;
	private int pageTotalCount;
	private int birthdayCountPerPage;
	private int firstRow;
	private int endRow;

	public BirthdayViewVO(List<BirthdayVO> birthdayList,
			int memberTotalCount, int currentPageNumber,
			int birthdayCountPerPage, int firstRow, int endRow) {
		this.birthdayList = birthdayList;
		this.memberTotalCount = memberTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.birthdayCountPerPage = birthdayCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	
	private void calculatePageTotalCount() {
		if (memberTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = memberTotalCount / birthdayCountPerPage;
			if (memberTotalCount % birthdayCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}

	public List<BirthdayVO> getBirthdayList() {
		return birthdayList;
	}

	public void setBirthdayList(List<BirthdayVO> birthdayList) {
		this.birthdayList = birthdayList;
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

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getBirthdayCountPerPage() {
		return birthdayCountPerPage;
	}

	public void setBirthdayCountPerPage(int birthdayCountPerPage) {
		this.birthdayCountPerPage = birthdayCountPerPage;
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
