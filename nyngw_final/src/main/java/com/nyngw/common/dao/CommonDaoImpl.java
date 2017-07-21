package com.nyngw.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MemoVO;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Common_CodeVO selectCodeNameByDocNumber(String ea_doc_number) {
		return (Common_CodeVO) sqlSession.selectOne("selectCodeNameByDocNumber", ea_doc_number);
	}

	public MemberVO common_selectMemberByMemID(String mem_id) {
		return (MemberVO) sqlSession.selectOne("common_selectMemberByMemID", mem_id);
	}
	
	public MemberVO common_selectMemberByMemNumber(String mem_number) {
		return (MemberVO) sqlSession.selectOne("common_selectMemberByMemNumber", mem_number);
	}
	
	public Common_CodeVO common_selectCodeNameByDocument(String code_number) {
		return (Common_CodeVO) sqlSession.selectOne("common_selectCodeNameByDocument", code_number);
	}

	public void addMemo_CS(MemoVO memo) {
		sqlSession.insert("addMemo_CS", memo);
	}

	public List<MemoVO> getMemoList_CS(MemoVO memo) {
		return sqlSession.selectList("getMemoList_CS", memo);
	}

	public void deleteMemo_CS(MemoVO memo) {
		sqlSession.delete("deleteMemo_CS", memo);
	}

	public MemoVO getMemo_CS(MemoVO memo) {
		return (MemoVO) sqlSession.selectOne("getMemo_CS", memo);
	}

	public void modifyMemo_CS(MemoVO memo) {
		sqlSession.update("modifyMemo_CS", memo);
	}

	public int countMemo_CS(MemoVO memo) {
		int result=0;
		if(sqlSession.selectOne("countMemo_CS", memo)!=null){
			result=(int) sqlSession.selectOne("countMemo_CS",memo);
		}
		return result;
	}

	public MemberVO selectMemIdByMemPwd(String mem_pwd) {
		return (MemberVO) sqlSession.selectOne("selectMemIdByMemPwd", mem_pwd);
	}

}
