package com.nyngw.electronicApproval.draft.dao;

import java.util.List;

import com.nyngw.dto.DocumentSearchVO;
import com.nyngw.dto.DocumentVO;

public interface DraftDao {
	
	public List<DocumentVO> selectDocumentList();

	//둘 다 선택했을 때
	//문서 이름으로 검색
	public List<DocumentVO> selectDocumentListByDocName(DocumentSearchVO documentSearchVO);
	//문서설명으로 검색
	public List<DocumentVO> selectDocumentListByDocExplanation(DocumentSearchVO documentSearchVO);
	//문서작성자로 검색
	public List<DocumentVO> selectDocumentListByDocMemNumber(DocumentSearchVO documentSearchVO);
	//문서작성일로 검색
	public List<DocumentVO> selectDocumentListByDocDate(DocumentSearchVO documentSearchVO);

	//문서함 선택 안 했을 때
	//문서 이름으로 검색
	public List<DocumentVO> selectDocumentListOnlyByDocName(DocumentSearchVO documentSearchVO);
	//문서설명으로 검색
	public List<DocumentVO> selectDocumentListOnlyByDocExplanation(DocumentSearchVO documentSearchVO);
	//문서작성일로 검색
	public List<DocumentVO> selectDocumentListOnlyByDocMemNumber(DocumentSearchVO documentSearchVO);
	//문서작성자로 검색
	public List<DocumentVO> selectDocumentListOnlyByDocDate(DocumentSearchVO documentSearchVO);

	//기안문서함만 선택했을 때
	public List<DocumentVO> selectDocumentListOnlyByDraftBoxOption(DocumentSearchVO documentSearchVO);

	//둘다 선택 안 했을 때
	public List<DocumentVO> selectDocumentListOnlyBySearchText(DocumentSearchVO documentSearchVO);
}
