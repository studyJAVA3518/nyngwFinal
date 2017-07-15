package com.nyngw.humanResource.vacationManagement.dao;

import java.util.List;

import com.nyngw.dto.Member_Vacation_FN_GETCNTVO;

public interface VacationManagementDao {
	public List<Member_Vacation_FN_GETCNTVO> getMemberVactionList_VM(Member_Vacation_FN_GETCNTVO mvfg);
	public List<Member_Vacation_FN_GETCNTVO> getDeptVactionList_VM(Member_Vacation_FN_GETCNTVO mvfg);
	public int countTotalMember(Member_Vacation_FN_GETCNTVO mvfg);
}
