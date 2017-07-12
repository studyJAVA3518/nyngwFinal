package com.nyngw.common.service;

import java.util.List;

import com.nyngw.dto.Common_CodeVO;

public interface CommonService {
	public Common_CodeVO findCodeNameByDocNumber(String ea_doc_number);
}
