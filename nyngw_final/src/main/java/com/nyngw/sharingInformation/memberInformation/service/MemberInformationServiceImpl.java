package com.nyngw.sharingInformation.memberInformation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.AddressBookVO;
import com.nyngw.dto.AddressBookViewVO;
import com.nyngw.dto.BirthdayVO;
import com.nyngw.dto.BirthdayViewVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.sharingInformation.memberInformation.dao.MemberInformationDaoImpl;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {
	@Autowired
	private MemberInformationDaoImpl memberInformationDao;

	private static final int ADDRESSBOOK_COUNT_PER_PAGE = 20;
	private static final int BIRTHDAY_COUNT_PER_PAGE = 20;
	
	public List<Map> getAllDepartment() {
		List<DepartmentVO> departmentList = memberInformationDao.SI_selectDepartment();

		List<Map> treeJsonList = new ArrayList<Map>();
		
		for (DepartmentVO department : departmentList) {
			Map<String,String> treejsonMap = new HashMap<String,String>();
			treejsonMap.put("label", department.getDept_name());
			treejsonMap.put("itemId", department.getDept_number());
			treejsonMap.put("parentId", department.getDept_parents());
			
			treeJsonList.add(treejsonMap);
		}
		return treeJsonList;
	}

	public void getMemberInfo(int pageNumber,Model model,String mem_name) {
		List<AddressBookVO> addressBookList = new ArrayList<AddressBookVO>();
		int currentPageNumber = pageNumber;
		int memberTotalCount = 0;
		if(mem_name.equals("")){
			memberTotalCount = memberInformationDao.SI_selectAllMemberCount();
		}else{
			memberTotalCount = memberInformationDao.SI_selectMemberCount(mem_name);
		}
		int firstRow = 0;
		int endRow = 0;
		if (memberTotalCount > 0) {
			firstRow = (pageNumber - 1) * ADDRESSBOOK_COUNT_PER_PAGE + 1;
			endRow = firstRow + ADDRESSBOOK_COUNT_PER_PAGE - 1;
			int offset = firstRow - 1;
			int limit = endRow - firstRow + 1;
			RowBounds rowBounds = new RowBounds(offset, limit);
			addressBookList = memberInformationDao.SI_selectAllMember(mem_name, rowBounds);
			/*if(select.getVal()!=null && !select.getVal().equals("")){
				MemberTotalCount = memberInformationDao.SI_MemberSelectCount(select);
			}	검색할 때 사용할 부분*/
		} else {
			currentPageNumber = 0;
			addressBookList = Collections.emptyList();
		}
		model.addAttribute("addressBookViewVO", new AddressBookViewVO(addressBookList, memberTotalCount,
				currentPageNumber, ADDRESSBOOK_COUNT_PER_PAGE, firstRow, endRow));
	}
	
	private List<BirthdayVO> birthdayList;
	private int memberTotalCount;
	private int currentPageNumber;
	private int pageTotalCount;
	private int birthdayCountPerPage;
	private int firstRow;
	private int endRow;
	
	public void getBirthdayMember(int pageNumber,Model model,String month) {
		month = (Integer.parseInt(month) < 10 ? "0" : "") + month;
		List<BirthdayVO> birthdayList = new ArrayList<BirthdayVO>();
		int currentPageNumber = pageNumber;
		int memberTotalCount = 0;
		memberTotalCount = memberInformationDao.SI_selectBirthdayMemberCount(month);
		int firstRow = 0;
		int endRow = 0;
		if (memberTotalCount > 0) {
			firstRow = (pageNumber - 1) * BIRTHDAY_COUNT_PER_PAGE + 1;
			endRow = firstRow + BIRTHDAY_COUNT_PER_PAGE - 1;
			int offset = firstRow - 1;
			int limit = endRow - firstRow + 1;
			RowBounds rowBounds = new RowBounds(offset, limit);
			birthdayList = memberInformationDao.SI_selectBirthdayMember(month, rowBounds);
			/*if(select.getVal()!=null && !select.getVal().equals("")){
				MemberTotalCount = memberInformationDao.SI_MemberSelectCount(select);
			}	검색할 때 사용할 부분*/
		} else {
			currentPageNumber = 0;
			birthdayList = Collections.emptyList();
		}
		model.addAttribute("birthdayViewVO", new BirthdayViewVO(birthdayList, memberTotalCount,
				currentPageNumber, BIRTHDAY_COUNT_PER_PAGE, firstRow, endRow));
	}
	
	
}
