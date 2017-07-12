package com.nyngw.electronicApproval.draft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.dto.DocumentSearchVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.electronicApproval.draft.dao.DraftDaoImpl;

@Service
public class DraftServiceImpl implements DraftService {

	@Autowired
	private DraftDaoImpl draftDao;
	
	//기안문서함을 처음 열었을 때 모든 document를 차례대로 보여줄 메서드
	//무엇을 기준으로 정렬하여 보여줄지 고민해봐야한다.
	@Override
	public List<DocumentVO> defaultDocumentList() {
		return draftDao.selectDocumentList();
	}

	//문서검색 메서드
	
	public List<DocumentVO> searchDocument(String draftBoxOption, String searchOption, String searchText) {
		List<DocumentVO> documentList = null;
		DocumentSearchVO documentSearchVO = new DocumentSearchVO();
		int whatToRun = 0;
		//0 둘 다 선택 했을 때
		//1 기안문서함만 선택했 안 했을 때 (+1)
		//2 검색어 구분만 선택 안 했을 때(+2)
		//3 둘다 선택 안 했을 때(+1+2)
		
		//내용 등록
		if (searchText==null||searchText.equals("")) {
			documentSearchVO.setSearchText("");
		}else{
			documentSearchVO.setSearchText(searchText);
		}
		//문서함구분에 따라 draftBoxOption을 DB가 알아들을 수 있도록 설정해준다.
		//기안문서A~ 등도 DB에 있는 column이기는 하지만 document 테이블에는 fk로 code8~ 등이 참조되어 있다.
		if (draftBoxOption==null&&draftBoxOption.equals("")){
			documentSearchVO.setDraftBoxOption("");
			whatToRun+=1;
		}else{
			documentSearchVO.setDraftBoxOption(draftBoxOption);
		}
		
		//검색어 분류를 선택하지 않았을 시
		if (searchOption.equals("--선택--")){
			whatToRun +=2;
		}
		
		System.out.println(documentSearchVO.getSearchText()+"****"+ documentSearchVO.getDraftBoxOption());
		switch(whatToRun){
		case 0://둘 다 선택했을 때
			switch(searchOption){
			case "doc_name":
				documentList= draftDao.selectDocumentListByDocName(documentSearchVO);
				break;
			case "doc_explanation":
				documentList= draftDao.selectDocumentListByDocExplanation(documentSearchVO);
				break;
			case "doc_mem_number":
				documentList= draftDao.selectDocumentListByDocMemNumber(documentSearchVO);
				break;
			}
			break;
		case 1://검색어 분류만 선택했을 때 (+1)
			switch(searchOption){
			case "doc_name":
				documentList= draftDao.selectDocumentListOnlyByDocName(documentSearchVO);
				break;
			case "doc_explanation":
				documentList= draftDao.selectDocumentListOnlyByDocExplanation(documentSearchVO);
				break;
			case "doc_mem_number":
				documentList= draftDao.selectDocumentListOnlyByDocMemNumber(documentSearchVO);
				break;
			case "문서등록일":
				documentList= draftDao.selectDocumentListOnlyByDocDate(documentSearchVO);
				break;
			}
			break;
		case 2://2 기안문서함만 선택했을 때(+2)
			documentList= draftDao.selectDocumentListOnlyByDraftBoxOption(documentSearchVO);
			break;
		case 3://3 둘다 선택 안 했을 때(+1+2)
			documentSearchVO.setDoc_name("");
			documentSearchVO.setDoc_explanation("");
			documentSearchVO.setDoc_mem_number("");
			documentSearchVO.setDoc_date("");
			documentList= draftDao.selectDocumentListOnlyBySearchText(documentSearchVO);
			break;
		}
			
		return documentList;
	}

}
