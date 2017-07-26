package com.nyngw.environmentSetting.planPublicRelationsSetting.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.dto.Diligence_And_LazinessVO;
import com.nyngw.dto.Pay_PolicyVO;
import com.nyngw.dto.Pay_PolicyViewVO;
import com.nyngw.dto.Pay_kindVO;
import com.nyngw.dto.PositionVO;
import com.nyngw.dto.Work_TimeVO;

@Repository
public class PlanPublicRelationsSettingDaoImpl implements
		PlanPublicRelationsSettingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	MongoTemplate mongoTemplate;
	private static String COLLECTION_NAME = "Company";
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @param vo
	 * @return int
	 * @throws SQLException
	 */
	public int insertCompanyInfo(CompanyVO vo) throws SQLException {
		int result=(Integer)sqlSession.update("insertCompanyInfo",vo);
		
		vo.setId(0);
		mongoTemplate.insert(vo, COLLECTION_NAME);

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

		vo.setId(0);
		Query query = new Query();
		
		Update update = new Update();
		update.set("company_name", vo.getCompany_name());
		update.set("company_tel",vo.getCompany_tel());
		update.set("company_zip", vo.getCompany_zip());
		update.set("company_addr1",vo.getCompany_addr1());
		update.set("company_addr2",vo.getCompany_addr2());
		
		mongoTemplate.updateFirst(query, update, COLLECTION_NAME);	
		
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
		
		CompanyVO vo = new CompanyVO();
		vo.setId(0);
		vo.setCompany_logo(company_logo);
		mongoTemplate.insert(vo, COLLECTION_NAME);
		
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
		CompanyVO vo = new CompanyVO();
		vo.setId(0);
		Query query = new Query();
		
		Update update = new Update();
		update.set("company_logo", company_logo);
		mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("company_logo", company_logo);
		paramMap.put("company_number", company_number);
		int result=(Integer)sqlSession.update("updateCompanyLogo",paramMap);
		return result;
	}
	
	/**
	 * 회사의 기본적인 업무시간을 가져오는 메서드
	 * @return wtList
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Work_TimeVO> selectWorkTime() throws SQLException{
		ArrayList<Work_TimeVO> wtList = (ArrayList<Work_TimeVO>) sqlSession.selectList("esSelectWorking","");
		return wtList;
	}
	
	/**
	 * 회사의 기본 업무시간을 수정하는 메서드
	 */
	@Override
	public int updateWorkTime(Work_TimeVO vo) throws SQLException{
		int result = sqlSession.update("esUpdateWorkingTime",vo);
		return result;
	}
	
	/**
	 * 회사의 부서에 대한 모든 정보를 조회하는 메서드
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
	
	/**
	 * 회사 직급 개수 세주는 메서드
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int selectPositionCount() throws SQLException {
		int count = (int) sqlSession.selectOne("esSelectPositionCount", "");
		return count;
	}
	
	/**
	 * 회사 직급 정보 조회하는 메서드
	 * @return ArrayList<PositionVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<PositionVO> selectPositionList() throws SQLException {
		ArrayList<PositionVO> positionList = (ArrayList<PositionVO>) sqlSession.selectList("esSelectPosition", "");
		return positionList;
	}
	
	/**
	 * 회사 직급 정보 등록하는 메서드
	 * @param PositionVO vo
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int insertPosition(PositionVO vo) throws SQLException {
		int result = sqlSession.update("esInsertPosition", vo);
		return result;
	}
	
	/**
	 * 회사 하나의 직급 정보 조회하는 메서드
	 * @param String position_number
	 * @return PositionVO vo
	 * @throws SQLException
	 */
	@Override
	public PositionVO selectOnePosition(String position_number) throws SQLException {
		PositionVO vo = (PositionVO) sqlSession.selectOne("esSelectOnePosition", position_number);
		return vo;
	}
	
	/**
	 * 회사 하나의 직급 정보 수정하는 메서드
	 * @param PositionVO vo
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updatePosition(PositionVO vo) throws SQLException {
		int result = sqlSession.update("esUpdatePosition", vo);
		return result;
	}
	
	/**
	 * 회사 하나의 직급 정보 삭제하는 메서드
	 * @param String deletePositionNum
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int deletePosition(String deletePositionNum) throws SQLException {
		int result = sqlSession.update("esDeletePosition",deletePositionNum);
		return result;
	}
	
	/**
	 * 회사 하나의 직급 레벨 수정하는 메서드
	 * @param PositionVO vo
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updatePositionLevel(PositionVO vo) throws SQLException{
		int result = sqlSession.update("esUpdatePositionLevel", vo);
		return result;
	}
	
	/**
	 * 회사 하나의 근무시간 등록하는 메서드
	 * @param List<Diligence_And_LazinessVO> dalList
	 * @return int 
	 * @throws SQLException
	 */
	@Override
	public int insertDAL(List<Diligence_And_LazinessVO> dalList) throws SQLException{
		int count = 0;
		for (Diligence_And_LazinessVO dalVO : dalList) {
			sqlSession.update("esInsertDAL", dalVO);
			count++;
		}
		return count;
	}
	
	
	/**
	 * 회사 급여종류 정보 리스트를 조회하는 메서드
	 * @return ArrayList<Pay_kindVO> 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Pay_kindVO> selectPayKindList() throws SQLException{
		ArrayList<Pay_kindVO> payKindList = (ArrayList<Pay_kindVO>) sqlSession.selectList("esSelectPayKindList","");
		return payKindList;
	}
	
	/**
	 * 회사 급여종류 정보 하나를 조회하는 메서드
	 * @return Pay_kindVO 
	 * @throws SQLException
	 */
	@Override
	public Pay_kindVO selectPayKindOne(String pk_number) throws SQLException{
		Pay_kindVO payKind = (Pay_kindVO) sqlSession.selectOne("esSelectPayKindOne",pk_number);
		return payKind;
	}
	
	/**
	 * 회사 급여관련 모든 정보 리스트를 조회하는 메서드
	 * @return ArrayList<Pay_PolicyViewVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Pay_PolicyViewVO> selectPayInfoList() throws SQLException{
		ArrayList<Pay_PolicyViewVO> payInfoList = (ArrayList<Pay_PolicyViewVO>) sqlSession.selectList("esSelectPayInfoList","");
		return payInfoList;
	}
	
	/**
	 * 회사 급여관련 모든 정보 중 시간당시급 리스트를 조회하는 메서드
	 * @return ArrayList<Pay_PolicyViewVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Pay_PolicyViewVO> selectPayInfoHourList() throws SQLException{
		ArrayList<Pay_PolicyViewVO> payInfoHourList = (ArrayList<Pay_PolicyViewVO>) sqlSession.selectList("esSelectPayHourInfoList","");
		return payInfoHourList;
	}
	
	/**
	 * 회사 급여관련 모든 정보 중 기본급 리스트를 조회하는 메서드
	 * @return ArrayList<Pay_PolicyViewVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Pay_PolicyViewVO> selectPayInfoBasicList() throws SQLException{
		ArrayList<Pay_PolicyViewVO> payInfoBasicList = (ArrayList<Pay_PolicyViewVO>) sqlSession.selectList("esSelectPayInfoBasicList","");
		return payInfoBasicList;
	}
	
	/**
	 * 회사 급여관련 모든 정보 중 직책수당 리스트를 조회하는 메서드
	 * @return ArrayList<Pay_PolicyViewVO>
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Pay_PolicyViewVO> selectPayInfoPositionList() throws SQLException{
		ArrayList<Pay_PolicyViewVO> payInfoPositionList = (ArrayList<Pay_PolicyViewVO>) sqlSession.selectList("esSelectPayInfoPositionList","");
		return payInfoPositionList;
	}
	
	/**
	 * 급여 종류 등록하는 메서드
	 * @param PositionVO vo
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int insertPayKind(Pay_kindVO vo) throws SQLException{
		int result = sqlSession.update("esInsertPayKind", vo);
		return result;
	}
	
	/**
	 * 급여 정책 등록하는 메서드
	 * @param PositionVO vo
	 * @return int
	 * @throws SQLException
	 */
	public int insertPayPolicy(Pay_PolicyVO vo) throws SQLException{
		int result = sqlSession.update("esInsertPayPolicy", vo);
		return result;
	}
	
	/**
	 * 급여 종류 수정하는 메서드
	 * @param PositionVO vo
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int updatePayKind(Pay_kindVO vo) throws SQLException{
		int result = sqlSession.update("esUpdatePayKind", vo);
		return result;
	}
	
	/**
	 * 급여 종류 삭제하는 메서드
	 * @param String pk_number
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int deletePayKind(String pk_number) throws SQLException{
		int result = sqlSession.update("esDeletePayKind", pk_number);
		return result;
	}
	
	/**
	 * 급여 정책 삭제하는 메서드
	 * @param String pp_number
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public int deletePayPolicy(String pp_number) throws SQLException{
		int result = sqlSession.update("esDeletePayPolicy", pp_number);
		return result;
	}
	
	/**
	 * 급여정책 금액 수정
	 */
	@Override
	public int updatePayPolicyPrice(Pay_PolicyVO ppOriginvo) throws SQLException{
		int result = sqlSession.update("esUpdatePayPolicyPrice",ppOriginvo);
		return result;
		
	}
	
	/**
	 * 업데이트한 시간당급여의 직책을 조회
	 * @param pp_number
	 * @return
	 */
	public Pay_PolicyVO selectPayPolicyOne(String pp_number) {
		Pay_PolicyVO vo = (Pay_PolicyVO) sqlSession.selectOne("esSelectPayPolicyOne",pp_number);
		return vo;
	}
	
	/**
	 * 업데이트한 시간당급여의 직책을 조회하여 기본급 중 같은 직책의 데이터 하나를 조회
	 */
	@Override
	public Pay_PolicyViewVO selectPayPolicyViewOne(String up_pp_position_number) throws SQLException {
		Pay_PolicyViewVO vo = (Pay_PolicyViewVO) sqlSession.selectOne("esSelectPayPolicyViewOne",up_pp_position_number);
		return vo;
		
	}

	
}
