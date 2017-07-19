package com.nyngw.electronicApproval.draft.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DocumentSearchVO;
import com.nyngw.dto.DocumentVO;
import com.nyngw.dto.MemberVO;

@Repository
public class DraftDaoImpl implements DraftDao {

	@Autowired
	private SqlSession sqlSession;
	
	//모든 문서 검색
	@Override
	public List<DocumentVO> selectDocumentList() {
		return sqlSession.selectList("selectDocumentList");
	}

	//둘 다 선택했을 때
	//문서 이름으로 검색
	@Override
	public List<DocumentVO> selectDocumentListByDocName(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListByDocName", documentSearchVO);
	}
	//문서설명으로 검색
	@Override
	public List<DocumentVO> selectDocumentListByDocExplanation(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListByDocExplanation", documentSearchVO);
	}
	//문서작성자로 검색
	@Override
	public List<DocumentVO> selectDocumentListByDocMemNumber(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListByDocMemNumber", documentSearchVO);
	}
	//문서작성일로 검색
	@Override
	public List<DocumentVO> selectDocumentListByDocDate(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListByDocDate", documentSearchVO);
	}

	//문서함 선택 안 했을 때
	//문서 이름으로 검색
	@Override
	public List<DocumentVO> selectDocumentListOnlyByDocName(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListOnlyByDocName", documentSearchVO);
	}
	//문서설명으로 검색
	@Override
	public List<DocumentVO> selectDocumentListOnlyByDocExplanation(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListOnlyByDocExplanation", documentSearchVO);
	}
	//문서작성일로 검색
	@Override
	public List<DocumentVO> selectDocumentListOnlyByDocMemNumber(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListOnlyByDocMemNumber", documentSearchVO);
	}
	//문서작성자로 검색
	@Override
	public List<DocumentVO> selectDocumentListOnlyByDocDate(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListOnlyByDocDate", documentSearchVO);
	}

	//기안문서함만 선택했을 때
	@Override
	public List<DocumentVO> selectDocumentListOnlyByDraftBoxOption(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListOnlyByDraftBoxOption", documentSearchVO);
	}

	//둘다 선택 안 했을 때
	@Override
	public List<DocumentVO> selectDocumentListOnlyBySearchText(DocumentSearchVO documentSearchVO) {
		return sqlSession.selectList("selectDocumentListOnlyBySearchText", documentSearchVO);
	}

	public List<DepartmentVO> draft_selectDepartmentList() {
		return sqlSession.selectList("draft_selectDepartmentList");
	}

	public List<MemberVO> dreat_selectMemberListByDepartment(String dept_number) {
		return sqlSession.selectList("dreat_selectMemberListByDepartment", dept_number);
	}

	public List<MemberVO> dreat_selectMemberListByDepartmentMemberName(Map paramMap) {
		return sqlSession.selectList("dreat_selectMemberListByDepartmentMemberName", paramMap);
	}

}
