package com.nyngw.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.Common_CodeVO;
import com.nyngw.dto.MemberVO;

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
}
