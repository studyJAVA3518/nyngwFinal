package com.nyngw.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MemoVO;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDaoImpl codeManagerDao;
	
	@Override
	public Common_CodeVO findCodeNameByDocNumber(String ea_doc_number) {
		return codeManagerDao.selectCodeNameByDocNumber(ea_doc_number);
	}

	public MemberVO findMemberByMemId(String mem_id){
		return codeManagerDao.common_selectMemberByMemID(mem_id);
	} 
	
	public MemberVO findMemberByMemNumber(String mem_number){
		return codeManagerDao.common_selectMemberByMemNumber(mem_number);
	} 
	
	public Common_CodeVO common_selectCodeNameByDocument(String code_number) {
		return codeManagerDao.common_selectCodeNameByDocument(code_number);
	}

	public void addMemo(MemoVO memo) {
		codeManagerDao.addMemo_CS(memo);
	}

	public List<MemoVO> getMemoList(MemoVO memo) {
		return codeManagerDao.getMemoList_CS(memo);
	}

	public void deleteMemo(MemoVO memo) {
		codeManagerDao.deleteMemo_CS(memo);
	}

	public MemoVO getMemo(MemoVO memo) {
		return codeManagerDao.getMemo_CS(memo);
	}

	public void modifyMemo(MemoVO memo) {
		codeManagerDao.modifyMemo_CS(memo);
	}

	public int countMemo(MemoVO memo) {
		return codeManagerDao.countMemo_CS(memo);
	}

	public MemberVO findMemIdByMemPwd(String mem_pwd) {
		return codeManagerDao.selectMemIdByMemPwd(mem_pwd);
	}
}
