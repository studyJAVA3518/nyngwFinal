package com.nyngw.environmentSetting.planPublicRelationsSetting.service;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.DepartmentViewVO;
import com.nyngw.dto.Diligence_And_LazinessVO;
import com.nyngw.dto.Pay_PolicyVO;
import com.nyngw.dto.Pay_PolicyViewVO;
import com.nyngw.dto.Pay_kindVO;
import com.nyngw.dto.PositionVO;
import com.nyngw.dto.Work_TimeVO;
import com.nyngw.environmentSetting.planPublicRelationsSetting.dao.PlanPublicRelationsSettingDaoImpl;
import com.nyngw.environmentSetting.planPublicRelationsSetting.util.ExcelRead;
import com.nyngw.environmentSetting.planPublicRelationsSetting.util.ExcelReadOption;

@Service
public class PlanPublicRelationsSettingServiceImpl implements
		PlanPublicRelationsSettingService {
	
	@Autowired
	PlanPublicRelationsSettingDaoImpl planPublicRelationsSettingDao;
	
	public void viewWorkTime(Model model) throws SQLException {
		
		ArrayList<Work_TimeVO> wtList = planPublicRelationsSettingDao.selectWorkTime();
		model.addAttribute("wtList", wtList);
		
	}
	
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
	
	/**
	 * 회사 부서 정보를 불러오는 메서드
	 */
	@Override
	public void viewDeptInfo(Model model) throws SQLException {
		
		//회사부서정보 
		ArrayList<DepartmentViewVO> dvList = planPublicRelationsSettingDao.selectDepartmentView();
		ArrayList<DepartmentViewVO> upperMemList = planPublicRelationsSettingDao.selectUpperMember();
		int DeptCount = planPublicRelationsSettingDao.selectDeptCount();
		
		model.addAttribute("dvList",dvList);
		model.addAttribute("upperMemList",upperMemList);
		model.addAttribute("DeptCount",DeptCount);
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
	 * 부서정보 업데이트 전 부서의 하나의 정보를 가져오는 메서드
	 * @param up_dept_number
	 * @return
	 * @throws SQLException
	 */
	public DepartmentVO getDeptOne(String up_dept_number) throws SQLException{
		DepartmentVO dvo = planPublicRelationsSettingDao.selectDepartOne(up_dept_number);
		return dvo;
	}
	
	/**
	 * 부서 정보 수정하는 메서드
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
	
	
	/**
	 * 회사 직급정보 등록하는 메서드
	 * @param model
	 * @throws SQLException
	 */
	@Override
	public void viewPositionInfo(Model model) throws SQLException {
		
		//회사직급정보 
		ArrayList<PositionVO> posList = planPublicRelationsSettingDao.selectPositionList();
		int positionCount = planPublicRelationsSettingDao.selectPositionCount();
		
		model.addAttribute("posList",posList);
		model.addAttribute("positionCount",positionCount);
	}
	
	/**
	 * 직급정보 등록하는 메서드
	 * @param model
	 * @param PositionVO pvo
	 * @throws SQLException
	 */
	@Override
	public void enrollPosition(Model model, PositionVO pvo) throws SQLException {
		
		int result = planPublicRelationsSettingDao.insertPosition(pvo);
		model.addAttribute("resultInserPosition",result);
		
	}
	
	/**
	 * 직급정보 업데이트 전 부서의 하나의 정보를 가져오는 메서드
	 * @param up_dept_number
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PositionVO getPositiontOne(String up_position_number) throws SQLException{
		PositionVO pvo = planPublicRelationsSettingDao.selectOnePosition(up_position_number);
		return pvo;
	}
	
	/**
	 * 직급 정보 수정하는 메서드
	 * @param model
	 * @param String up_position_number
	 * @param String up_position_name
	 * @param String up_position_level
	 */
	public void modifyPosition(Model model, 
			String up_position_number, 
			String up_position_name) throws SQLException {
		
		PositionVO pvo = new PositionVO();
		
		pvo.setPosition_name(up_position_name);
		pvo.setPosition_number(up_position_number);
		
		int result = planPublicRelationsSettingDao.updatePosition(pvo);
		model.addAttribute("resultUpdatePosition",result);
	}
	
	/**
	 * 직급 정보 레벨 상위로 수정하는 메서드
	 * @param model
	 * @param String tmp_position_number
	 */
	public void modifyPositionLevelUp(Model model,
			String tmp_position_number) throws SQLException {
		
		//회사직급정보 
		ArrayList<PositionVO> posList = planPublicRelationsSettingDao.selectPositionList();
		String tmp = "";
		for(int i = 0; i<posList.size();i++){
			if(tmp_position_number.equals(posList.get(i).getPosition_number())){
				tmp = posList.get(i-1).getPosition_number();
			}
		}
		
		PositionVO pvo = getPositiontOne(tmp);
		int levelUp = pvo.getPosition_level()-1;
		PositionVO vo = new PositionVO();
		vo.setPosition_level(levelUp);
		vo.setPosition_number(tmp_position_number);
		int result = planPublicRelationsSettingDao.updatePositionLevel(vo);
		model.addAttribute("updatePositionLevelUp",result);
	}
	
	/**
	 * 직급 정보 레벨 하위로 수정하는 메서드
	 * @param model
	 * @param String tmp_position_number
	 */
	public void modifyPositionLevelDown(Model model, 
			String tmp_position_number) throws SQLException{
		
		//회사직급정보 
		ArrayList<PositionVO> posList = planPublicRelationsSettingDao.selectPositionList();
		String tmp = "";
		for(int i = 0; i<posList.size();i++){
			if(tmp_position_number.equals(posList.get(i).getPosition_number())){
				tmp = posList.get(i+1).getPosition_number();
			}
		}
		
		PositionVO pvo = getPositiontOne(tmp);
		int levelUp = pvo.getPosition_level()+1;
		PositionVO vo = new PositionVO();
		vo.setPosition_level(levelUp);
		vo.setPosition_number(tmp_position_number);
		int result = planPublicRelationsSettingDao.updatePositionLevel(vo);
		model.addAttribute("updatePositionLevelUp",result);
		
	}
	
	/**
	 * 직급 정보 삭제하는 메서드
	 * @param model
	 * @param deletePositionNum
	 */
	public void removePosition(Model model, String deletePositionNum) throws SQLException{
		int result = planPublicRelationsSettingDao.deletePosition(deletePositionNum);
		model.addAttribute("resultDeletePosition",result);
	}
	
	/**
	 * 업로드한 엑셀파일을 읽어들이는 메서드
	 * @param destFile
	 */
	public void excelUpload(File destFile, Model model) throws SQLException{
		
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D");
        excelReadOption.setStartRow(2);
        
        List<Map<String, String>>excelContent =ExcelRead.read(excelReadOption);
        List<Diligence_And_LazinessVO> dalList = new ArrayList<Diligence_And_LazinessVO>();
        for(Map<String, String> article: excelContent){
        	
        	Diligence_And_LazinessVO dalVO = new Diligence_And_LazinessVO();
        	
        	dalVO.setDal_date(article.get("A"));
        	dalVO.setDal_attend_time(article.get("B"));
        	dalVO.setDal_off_time(article.get("C"));
        	dalVO.setDal_mem_number(article.get("D"));
        	dalList.add(dalVO);
        	
            System.out.println(article.get("A"));
            System.out.println(article.get("B"));
            System.out.println(article.get("C"));
            System.out.println(article.get("D"));
        }
        int result = planPublicRelationsSettingDao.insertDAL(dalList);
        model.addAttribute("resultDAL",result);
	}
	
	/**
	 * 근무시간을 수정하는 메서드
	 * @param model
	 * @param wt_number
	 * @param wt_attend_time_hour
	 * @param wt_attend_time_minute
	 * @param wt_end_time_hour
	 * @param wt_end_time_minute
	 */
	public int modifyWorkingTime(Model model, String wt_number, String wt_attend_time_hour,
			String wt_attend_time_minute, String wt_end_time_hour,
			String wt_end_time_minute) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		String startTime = dateStr+" "+wt_attend_time_hour+":"+wt_attend_time_minute+":00";
		String endTime = dateStr+" "+wt_end_time_hour+":"+wt_end_time_minute+":00";
		
		Work_TimeVO wtvo = new Work_TimeVO();
		wtvo.setWt_number(wt_number);
		wtvo.setWt_start_time(startTime);
		wtvo.setWt_end_time(endTime);
		int result = planPublicRelationsSettingDao.updateWorkTime(wtvo);
		return result;
	}
	
	/**
	 * 급여정보설정 페이지에 필요한 정보 보여주기 위한 메서드
	 * @param model
	 * @throws SQLException
	 */
	public void viewPay(Model model) throws SQLException {
		ArrayList<Pay_kindVO> payKindList = planPublicRelationsSettingDao.selectPayKindList();
		ArrayList<Pay_PolicyViewVO> payViewList = planPublicRelationsSettingDao.selectPayInfoList();
		ArrayList<Pay_PolicyViewVO> payViewHourList = planPublicRelationsSettingDao.selectPayInfoHourList();
		ArrayList<Pay_PolicyViewVO> payViewBasicList = planPublicRelationsSettingDao.selectPayInfoBasicList();
		ArrayList<Pay_PolicyViewVO> payViewPositionList = planPublicRelationsSettingDao.selectPayInfoPositionList();
		ArrayList<PositionVO> positionList = planPublicRelationsSettingDao.selectPositionList();
		ArrayList<Work_TimeVO> wtList = planPublicRelationsSettingDao.selectWorkTime();
		
		
		model.addAttribute("payKindList",payKindList);
		model.addAttribute("payViewList",payViewList);
		model.addAttribute("payViewHourList",payViewHourList);
		model.addAttribute("payViewBasicList",payViewBasicList);
		model.addAttribute("payViewPositionList",payViewPositionList);
		model.addAttribute("positionList",positionList);
		model.addAttribute("wtList",wtList);
	}
	
	/**
	 * 급여종류 등록
	 * @param model
	 * @param vo
	 * @throws SQLException
	 */
	public void enrollPayKind(Model model, Pay_kindVO vo) throws SQLException {
		int result = planPublicRelationsSettingDao.insertPayKind(vo);
		model.addAttribute("resultPKInsert",result);
	}
	
	/**
	 * 급여정책 등록
	 * @param model
	 * @param vo
	 * @throws SQLException
	 */
	public void enrollPayPolicy(Model model, Pay_PolicyVO vo) throws SQLException {
		int result = planPublicRelationsSettingDao.insertPayPolicy(vo);
		model.addAttribute("resultPPInsert",result);
		
	}
	
	/**
	 * 급여 종류 수정 전에 급여종류번호 하나 가져오는 메서드
	 * @param pk_number
	 * @return Pay_kindVO
	 * @throws SQLException
	 */
	public Map getPayKindOne(String pk_number) throws SQLException {
		
		Pay_kindVO vo = planPublicRelationsSettingDao.selectPayKindOne(pk_number);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("pk_number", vo.getPk_number());
		map.put("pk_name", vo.getPk_name());
		map.put("pk_tax", vo.getPk_tax());
		
		return map;
	}
	
	/**
	 * 급여종류 수정
	 * @param model
	 * @param vo
	 * @throws SQLException
	 */
	public void modifyPayKind(Model model, String up_pk_number, String up_pk_name, String up_pk_tax) throws SQLException {
		Pay_kindVO vo = new Pay_kindVO();
		vo.setPk_number(up_pk_number);
		vo.setPk_name(up_pk_name);
		vo.setPk_tax(up_pk_tax);
		int result = planPublicRelationsSettingDao.updatePayKind(vo);
		model.addAttribute("resultPKUpdate",result);
	}
	
	/**
	 * 급여종류 삭제
	 * @param model
	 * @param vo
	 * @throws SQLException
	 */
	public void removePayKind(Model model, String pk_number) throws SQLException {
		int result = planPublicRelationsSettingDao.deletePayKind(pk_number);
		model.addAttribute("resultPKDelete",result);
	}

	

	
}
