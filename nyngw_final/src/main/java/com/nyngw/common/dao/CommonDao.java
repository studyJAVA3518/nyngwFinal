package com.nyngw.common.dao;

import java.util.List;

import com.nyngw.dto.Common_CodeVO;

public interface CommonDao {
	public Common_CodeVO selectCodeNameByDocNumber(String ea_doc_number);
}
