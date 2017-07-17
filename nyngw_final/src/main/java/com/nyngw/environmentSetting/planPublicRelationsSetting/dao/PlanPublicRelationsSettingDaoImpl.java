package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.dto.PositionVO;

@Repository
public class PlanPublicRelationsSettingDaoImpl implements
		PlanPublicRelationsSettingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int insertCompanyInfo(CompanyVO vo) throws SQLException {
		int result=(Integer)sqlSession.update("insertCompanyInfo",vo);
		return result;
	}
	
	/**
	 * 회사 정보를 업데이트하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int updateCompanyInfo(CompanyVO vo) throws SQLException {
		int result=(Integer)sqlSession.update("updateCompanyInfo",vo);
		return result;
	}
	
	/**
	 * 회사 로고를 입력하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int insertCompanyLogo(String company_logo) throws SQLException {
		int result=(Integer)sqlSession.update("inserttCompanyInfo",company_logo);
		return result;
	}
	
	/**
	 * 회사 로고를 업데이트하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updateCompanyLogo(String company_logo, String company_number) throws SQLException {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("company_logo", company_logo);
		paramMap.put("company_number", company_number);
		int result=(Integer)sqlSession.update("updateCompanyLogo",paramMap);
		return result;
	}
	
	/**
	 * 회사의 직급에 대한 모든 정보를 조회하는 메서드
	 * @return ArrayList<DepartmentViewVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<DepartmentViewVO> selectDepartmentView() throws SQLException {
		ArrayList<DepartmentViewVO>dvVoList 
			= (ArrayList<DepartmentViewVO>) sqlSession.selectList("esSelectDepartmentView", "");
		return dvVoList;
	}
	
	/**
	 * 상위 직급의 멤버 일부 정보를 조회하는 메서드
	 * @return ArrayList<DepartmentViewVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<DepartmentViewVO> selectUpperMember() throws SQLException {
		ArrayList<DepartmentViewVO>dvMemList 
		= (ArrayList<DepartmentViewVO>) sqlSession.selectList("esSelectUpperMember", "");
		return dvMemList;
	}

	/**
	 * 부서 정보 등록하는 메서드
	 * @param dvVO
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int insertDepartment(DepartmentVO dvo) throws SQLException {
		int result = sqlSession.update("esInsertDepartment", dvo);
		return result;
	}

	/**
	 * 등록하려는 부모부서의 레벨 가져오는 메서드
	 * @param dvVO
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public long selectDeptLevel(String dept_number) throws SQLException {
		DepartmentVO vo = (DepartmentVO) sqlSession.selectOne("esSelectDeptLevel",dept_number);
		long level = vo.getDept_level();
		return level;
	}
	
	/**
	 * 하나 부서의 정보를 가져오는 메서드
	 * @param dept_id
	 * @return DepartmentVO
	 * @throws SQLException
	 */
	@Override
	public DepartmentVO selectDepartOne(String dept_id) throws SQLException {
		DepartmentVO dvo = (DepartmentVO) sqlSession.selectOne("esSelectOneDept",dept_id);
		return dvo;
	}

	/**
	 * 회사 부서 수정하는 메서드
	 * @param String deleteNumber
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updateDepartment(DepartmentVO dvo) throws SQLException {
		int result = sqlSession.update("esUpdateDepartment", dvo);
		return result;
	}
	/**
	 * 회사 부서 삭제하는 메서드
	 * @param String deleteNumber
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int deleteDepartment(String deleteDeptNum) throws SQLException{
		int result = sqlSession.update("esDeleteDepartment",deleteDeptNum);
		return result;
	}
	
	/**
	 * 회사 부서 개수 세주는 메서드
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int selectDeptCount() throws SQLException {
		int result = (int) sqlSession.selectOne("esSelectDeptCount","");
		return result;
	}
	
	@Override
	public int selectPositionCount() throws SQLException {
		int count = (int) sqlSession.selectOne("esSelectPositionCount", "");
		return count;
	}
	
	@Override
	public ArrayList<PositionVO> selectPositionList() throws SQLException {
		ArrayList<PositionVO> positionList = (ArrayList<PositionVO>) sqlSession.selectList("esSelectPosition", "");
		return positionList;
	}
	
	@Override
	public int insertPosition(PositionVO vo) throws SQLException {
		int result = sqlSession.update("esInsertPosition", vo);
		return result;
	}
	
	@Override
	public PositionVO selectOnePosition(String position_number) throws SQLException {
		PositionVO vo = (PositionVO) sqlSession.selectOne("esSelectOnePosition", position_number);
		return vo;
	}
	
	@Override
	public int updatePosition(PositionVO vo) throws SQLException {
		int result = sqlSession.update("esUpdatePosition", vo);
		return result;
	}
	
	@Override
	public int deletePosition(String deletePositionNum) throws SQLException {
		int result = sqlSession.update("esDeletePosition",deletePositionNum);
		return result;
	}
	
	

	
}
