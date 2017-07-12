package com.nyngw.electronicApproval.draft.service;

import java.util.List;

import com.nyngw.dto.DocumentVO;

public interface DraftService {
	public List<DocumentVO> defaultDocumentList();

	//문서검색 메서드
	public List<DocumentVO> searchDocument(String draftBoxOption, String searchOption, String searchText);
}
