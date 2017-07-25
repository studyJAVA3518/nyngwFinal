package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.Approval_HistoryVO;
import com.nyngw.dto.Board_SelectVO;
import com.nyngw.dto.Electronic_ApprovalVO;
import com.nyngw.dto.Electronic_ApprovalViewVO;
import com.nyngw.dto.MeetingListViewVO;
import com.nyngw.dto.MeetingVO;
import com.nyngw.electronicApproval.approvalProgress.dao.ApprovalProgressDaoImpl;
import com.nyngw.electronicApproval.individualDocumentBox.dao.IndividualDocumentBoxDaoImpl;

@Service
public class IndividualDocumentBoxServiceImpl implements IndividualDocumentBoxService {
	
	@Autowired
	private IndividualDocumentBoxDaoImpl individualDocumentBoxDao;
	@Autowired
	ApprovalProgressDaoImpl approvalProgressDao;
	private static final int BOARD_COUNT_PER_PAGE = 5;
	
	@Override
	public Electronic_ApprovalViewVO sangsinList(int pageNumber,
			Board_SelectVO select) {
		int currentPageNumber = pageNumber;
		int boardTotalCount = individualDocumentBoxDao.selectsangsinCount(select.getMem_number());
			List<Electronic_ApprovalVO> sangsinList = null;
			int firstRow = 0;
			int endRow = 0;
			if (boardTotalCount > 0) {
				firstRow = (pageNumber - 1) * BOARD_COUNT_PER_PAGE + 1;
				endRow = firstRow + BOARD_COUNT_PER_PAGE - 1;
				sangsinList = individualDocumentBoxDao.selectSAB(firstRow, endRow, select);
				if(select.getVal()!=null && !select.getVal().equals("")){
					boardTotalCount = individualDocumentBoxDao.boardsangsinCount(select);
				}
			} else {
				currentPageNumber = 0;
				sangsinList = Collections.emptyList();
			}
			return  new Electronic_ApprovalViewVO(sangsinList, boardTotalCount,
					currentPageNumber, BOARD_COUNT_PER_PAGE, firstRow, endRow);
	}
	
	

	@Override
	public List<Electronic_ApprovalVO> defaultCAB(String mem_id) {
		return individualDocumentBoxDao.selectCAB(mem_id);
	}

	@Override
	public List<Electronic_ApprovalVO> defaultRAB(String mem_id) {
		return individualDocumentBoxDao.selectRAB(mem_id);
	}
//-----------------------------------------------------------------------
	@Override
	public List<String> ap_selectEaNumberByMemId(String mem_number){
		return approvalProgressDao.ap_selectEaNumberByMemId(mem_number);
	}

	@Override
	public int selectLastAstPriority(String ast_ea_number) {
		return approvalProgressDao.selectLastAstPriority(ast_ea_number);
	}

	@Override
	public int selectOneAstPriority(Map paramMap) {
		return approvalProgressDao.selectOneAstPriority(paramMap);
	}

	@Override
	public int selectLastApprovalHistory(String ah_ea_number) {
		return approvalProgressDao.selectLastApprovalHistory(ah_ea_number);
	}

	@Override
	public Approval_HistoryVO selectAhMember(Map param) {
		return individualDocumentBoxDao.selectAhMember(param);
	}

	@Override
	public List<Approval_HistoryVO> selectAhAll(String ea_number) {
		return individualDocumentBoxDao.selectAhAll(ea_number);
	}

	@Override
	public String selectMemberDept(String mem_number) {
		return individualDocumentBoxDao.selectMemberDept(mem_number);
	}
	
	@Override
	public String selectEaStepMember(Map map){
		return individualDocumentBoxDao.selectEaStepMember(map);
	}
	
	@Override
	public String selectDeptName(String dept_number){
		return individualDocumentBoxDao.selectDeptName(dept_number);
	}

	@Override
	public String selectMemberPosition(String mem_number) {
		return individualDocumentBoxDao.selectMemberPosition(mem_number);
	}

	@Override
	public String selectPositionName(String position_number) {
		return individualDocumentBoxDao.selectPositionName(position_number);
	}



	public String findLastAhStatus(String ea_number) {
		return individualDocumentBoxDao.ID_selectAhStatus(ea_number);
	}
	
	public String findLastAstMember(String ea_number) {
		return individualDocumentBoxDao.ID_selectAllAS(ea_number);
	}
	
}
