package com.nyngw.electronicApproval.approvalLineManagement.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.DepartmentVO;

@Repository
public class ApprovalLineManagementDaoImpl implements ApprovalLineManagementDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<DepartmentVO> EA_selectDepartmentList() {
		return sqlSession.selectList("EA_selectDepartmentList");
	}
}
