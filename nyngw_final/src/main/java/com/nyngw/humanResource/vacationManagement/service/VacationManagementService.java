package com.nyngw.humanResource.vacationManagement.service;

import java.util.List;

import com.nyngw.dto.Member_Vacation_FN_GETCNTVO;

public interface VacationManagementService {
	public List<Member_Vacation_FN_GETCNTVO> getMemberVacation(Member_Vacation_FN_GETCNTVO mvfg);
	public List<Member_Vacation_FN_GETCNTVO> getDeptVaction(Member_Vacation_FN_GETCNTVO mvfg);
	public int countTotalMember(Member_Vacation_FN_GETCNTVO mvfg);
}
