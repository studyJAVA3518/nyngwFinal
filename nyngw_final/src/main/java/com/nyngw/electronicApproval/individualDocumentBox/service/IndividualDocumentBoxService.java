package com.nyngw.electronicApproval.individualDocumentBox.service;

import java.util.List;

import com.nyngw.dto.Electronic_ApprovalVO;

public interface IndividualDocumentBoxService {

	public List<Electronic_ApprovalVO> defaultSAB(String mem_id);

	public List<Electronic_ApprovalVO> defaultCAB(String string);

	public List<Electronic_ApprovalVO> defaultRAB(String string);

}
