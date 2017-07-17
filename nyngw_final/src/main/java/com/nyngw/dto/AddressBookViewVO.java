package com.nyngw.dto;

import java.util.List;

public class AddressBookViewVO {
	private List<AddressBookVO> addressBookList;
	private int memberTotalCount;
	private int currentPageNumber;
	private int pageTotalCount;
	private int addressBookCountPerPage;
	private int firstRow;
	private int endRow;

	public AddressBookViewVO(List<AddressBookVO> addressBookList,
			int memberTotalCount, int currentPageNumber,
			int addressBookCountPerPage, int firstRow, int endRow) {
		this.addressBookList = addressBookList;
		this.memberTotalCount = memberTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.addressBookCountPerPage = addressBookCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		calculatePageTotalCount();
	}
	
	private void calculatePageTotalCount() {
		if (memberTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = memberTotalCount / addressBookCountPerPage;
			if (memberTotalCount % addressBookCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}
	
	public int getAddressBookCountPerPage() {
		return addressBookCountPerPage;
	}

	public void setAddressBookCountPerPage(int addressBookCountPerPage) {
		this.addressBookCountPerPage = addressBookCountPerPage;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
	public List<AddressBookVO> getAddressBookList() {
		return addressBookList;
	}
	public void setAddressBookList(List<AddressBookVO> addressBookList) {
		this.addressBookList = addressBookList;
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
