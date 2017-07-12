package com.nyngw.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.common.dao.CommonDaoImpl;
import com.nyngw.dto.Common_CodeVO;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDaoImpl codeManagerDao;
	
	@Override
	public Common_CodeVO findCodeNameByDocNumber(String ea_doc_number) {
		return codeManagerDao.selectCodeNameByDocNumber(ea_doc_number);
	}

}
