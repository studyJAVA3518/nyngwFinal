package com.nyngw.environmentSetting.planPublicRelationsSetting.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.environmentSetting.planPublicRelationsSetting.dao.PlanPublicRelationsSettingDaoImpl;

@Service
public class PlanPublicRelationsSettingServiceImpl implements
		PlanPublicRelationsSettingService {
	
	@Autowired
	PlanPublicRelationsSettingDaoImpl planPublicRelationsSettingDao;
	
	/**
	 * 회사 정보를 입력하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	@Override
	public int joinCompanyInfo(CompanyVO vo) throws SQLException {
		int result = planPublicRelationsSettingDao.insertCompanyInfo(vo);
		return result;
	}
	
	/**
	 * 회사 정보를 수정하는 메서드
	 * @return CompanyVO
	 * @throws SQLException
	 */
	@Override
	public int modifyCompanyInfo(CompanyVO vo) throws SQLException {
		int result = planPublicRelationsSettingDao.updateCompanyInfo(vo);
		return result;
	}
	
	/**
	 * 회사 로고를 입력하는 메서드
	 * @param String(업로드된 로고의 패스)
	 * @return int
	 * @throws SQLException
	 */
	public int joinCompanyLogo(String company_logo) throws SQLException {
		int result = planPublicRelationsSettingDao.insertCompanyLogo(company_logo);
		return result;
	}
	
	/**
	 * 회사 로고를 업데이트하는 메서드
	 * @param String(업로드된 로고의 패스),String(회사번호)
	 * @return int
	 * @throws SQLException
	 */
	public int modifyCompanyLogo(String company_logo, String company_number) throws SQLException {
		int result = planPublicRelationsSettingDao.updateCompanyLogo(company_logo, company_number);
		return result;
	}

	@Override
	public void viewDeptInfo(Model model) throws SQLException {
		
		//회사 직급정보 
		ArrayList<DepartmentViewVO> dvList = planPublicRelationsSettingDao.selectDepartmentView();
		ArrayList<DepartmentViewVO> upperMemList = planPublicRelationsSettingDao.selectUpperMember();
		
		model.addAttribute("dvList",dvList);
		model.addAttribute("upperMemList",upperMemList);
	}
	
	/**
	 * 부서 정보 등록하는 메서드
	 * @param dvVO
	 * @return int
	 * @throws SQLException
	 */
	@Override
	public void enrollDept(Model model, DepartmentVO dvo) throws SQLException {
		
		long parentLevel = planPublicRelationsSettingDao.selectDeptLevel(dvo.getDept_parents());
		dvo.setDept_level(parentLevel+1L);
		int result = planPublicRelationsSettingDao.insertDepartment(dvo);
		model.addAttribute("resultInserDept",result);
		
	}
	
	/**
	 * 부서 정보 삭제하는 메서드
	 * @param model
	 * @param DepartmentVO dvo
	 */
	public void modifyDept(Model model, 
			String up_dept_number, 
			String up_dept_name, 
			String up_dept_membernumber, 
			String up_dept_tel, 
			String up_dept_addr) throws SQLException {
		
		DepartmentVO dvo = new DepartmentVO();
		
		dvo.setDept_number(up_dept_number);
		dvo.setDept_name(up_dept_name);
		dvo.setDept_membernumber(up_dept_membernumber);
		dvo.setDept_tel(up_dept_tel);
		dvo.setDept_addr(up_dept_addr);
		
		int result = planPublicRelationsSettingDao.updateDepartment(dvo);
		model.addAttribute("resultUpdateDept",result);
	}
	
	/**
	 * 부서 정보 삭제하는 메서드
	 * @param model
	 * @param deleteDeptNum
	 */
	public void removeDept(Model model, String deleteDeptNum) throws SQLException{
		int result = planPublicRelationsSettingDao.deleteDepartment(deleteDeptNum);
		model.addAttribute("resultDeleteDept",result);
	}

	public DepartmentVO getDeptOne(String up_dept_number) throws SQLException{
		DepartmentVO dvo = planPublicRelationsSettingDao.selectDepartOne(up_dept_number);
		return dvo;
	}

}
