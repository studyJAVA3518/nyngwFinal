package com.nyngw.environmentSetting.planPublicRelationsSetting.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.nyngw.dto.CompanyVO;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.PositionVO;
import com.nyngw.environmentSetting.planPublicRelationsSetting.service.PlanPublicRelationsSettingServiceImpl;
import com.nyngw.environmentSetting.planPublicRelationsSetting.util.ExcelRead;
import com.nyngw.environmentSetting.planPublicRelationsSetting.util.ExcelReadOption;
import com.nyngw.homeMain.appointedUI.service.AppointedUIServiceImpl;

/**
 * 기획홍보부 설정 컨트롤러
 * @author pc07
 *
 */
@Controller
@RequestMapping("enovironmentSetting/planPublicRelationsSetting")
public class PlanPublicRelationsSettingController {
	
	@Autowired
	AppointedUIServiceImpl appointedUIService;
	@Autowired
	PlanPublicRelationsSettingServiceImpl planPublicRelationsSettingService; 
	
	
	/**
	 * 근무일 및 출결정보 등록시간 설정 화면으로 이동
	 */
	@RequestMapping("/workingDayForm")
	public String workingDaysForm(Model model){
		String url = "enovironmentSetting/planPublicRelationsSetting/workingDay";
		try {
			planPublicRelationsSettingService.viewWorkTime(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}

	/**
	 * 출결정보 예시 엑셀파일 다운로드
	 */
	@RequestMapping("/excelDownload")
	public ModelAndView excelDownload(HttpServletRequest request){
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/workingDayForm";
		//파일을 가져올 경로를 적어주고 + 가져올 파일 이름을 받아옴. 
		String fullPath = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/WEB-INF/download/worktimeExample.xls";
		
		 File file = new File(fullPath);
         
		 return new ModelAndView("download", "downloadFile", file);

	}
	
	/**
	 * 회사 기본 근무시간 수정하는 컨트롤러
	 * @param request
	 * @param model
	 * @param wt_number
	 * @param wt_attend_time_hour
	 * @param wt_attend_time_minute
	 * @param wt_end_time_hour
	 * @param wt_end_time_minute
	 * @return
	 */
	@RequestMapping("/updateWorkingTime")
	public @ResponseBody Map<String,Object> updateWorkingTime(Model model,
			String wt_number,
			String wt_attend_time_hour,
			String wt_attend_time_minute,
			String wt_end_time_hour,
			String wt_end_time_minute){
		
		Map<String, Object> map = new HashMap<String,Object>();
		int result = -1;
		try {
			result = planPublicRelationsSettingService.modifyWorkingTime(model, wt_number,wt_attend_time_hour,wt_attend_time_minute,wt_end_time_hour,wt_end_time_minute);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		map.put("result", result);
		return map;
	}
	
//	@RequestMapping("/checkDeptOne")
//	public @ResponseBody Map<String,Object> selectDeptOne(String tmp_dept_number){
//		Map<String,Object> map = new HashMap<String, Object>();
//		DepartmentVO dvo = null;
//		try {
//			dvo = planPublicRelationsSettingService.getDeptOne(tmp_dept_number);
//			System.out.println("업데이트할 부서 이름 : "+dvo.getDept_name());
//			map.put("dept_number", dvo.getDept_number());
//			map.put("dept_name", dvo.getDept_name());
//			map.put("dept_membernumber", dvo.getDept_membernumber());
//			map.put("dept_tel", dvo.getDept_tel());
//			map.put("dept_addr", dvo.getDept_addr());
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
	
	

	
	/**
	 * 출근정보가 입력된 엑셀파일을 업로드해서 엑셀파일을 읽어오는 메서드
	 * @param multipartFile - 받아온 엑셀파일
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/excelUpload",method=RequestMethod.POST)
	public String uploadlFile(
			MultipartHttpServletRequest request,
			Model model) throws IOException{
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/workingDayForm";
		System.out.println("엑셀 파일 업로드 컨트롤러");

		MultipartFile excelFile = request.getFile("excelFile");
		
		if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택해 주세요.");
        }
		
		//파일 경로
		String uploadFilePath = null;
		//업로드 경로 지정
		String upload = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/resources/upload/excel";
		File destFile = null;
		
		//멀티파트파일이 비어있지 않다면-> 파일 생성해서 업로드
		if(!excelFile.isEmpty()){
			//파일생성
			destFile = new File(upload,excelFile.getOriginalFilename());
			//업로드한다!!
			excelFile.transferTo(destFile);
			uploadFilePath = "resources/upload/"+excelFile.getOriginalFilename();
		}
        
        try {
			planPublicRelationsSettingService.excelUpload(destFile, model);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return url;
	}
	
	
	/**
	 * 회사 정보 설정 화면으로 이동
	 */
	@RequestMapping("/companyInfoForm")
	public String companyInfoForm(Model model){
		String url = "enovironmentSetting/planPublicRelationsSetting/companyInfo";
		CompanyVO company = null;
		try {
			company = appointedUIService.checkCompany();
			model.addAttribute("companyInfo",company);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * 회사정보수정에서 그림파일을 수동으로 업로드하는 메서드
	 * @param multipartFile - 받아온 그림파일 정보
	 * @param company_number2 - 회사번호
	 * @param logo - radio tag에서 가져온 선택값
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/updateLogoFile",method=RequestMethod.POST)
	public String uploadlFile(
			@RequestParam("logoFile") MultipartFile multipartFile,
			@RequestParam("company_number2") String company_number2,
			@RequestParam("logo") String logo,
			Model model,
			HttpServletRequest request,
			HttpSession session
			) throws IOException{
		
		//update 결과값 저장하는 변수
		int resultComLogo = -1;
		//company객체 생성
		CompanyVO company = null;
		//파일 경로
		String uploadFilePath = null;
		//basic : 로고를 변경하지 않고 업체에서 제공하는 기본로고를 사용함
		if (logo.equals("basic")) {
			
			uploadFilePath = "resources/images/basic_logo.jpg";
		
		// custom : 원하는 파일을 업로드하여 로고를 변경한다
		}else if(logo.equals("custom")){	
			
			//업로드 경로 지정
			String upload = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/resources/upload";
			File file = null;
			
			//멀티파트파일이 비어있지 않다면-> 파일 생성해서 업로드
			if(!multipartFile.isEmpty()){
				//파일생성
				file = new File(upload,multipartFile.getOriginalFilename());
				//업로드한다!!
				multipartFile.transferTo(file);
				uploadFilePath = "resources/upload/"+multipartFile.getOriginalFilename();
			}
		}
		
		//파일 경로를 update한다
		try {
			//파일경로를 업데이트하고 결과값을 int로 저장
			resultComLogo = planPublicRelationsSettingService.modifyCompanyLogo(uploadFilePath, session.getAttribute("companyNumber").toString());
			//company
			company = appointedUIService.checkCompany();
			//session에 logo경로 저장
			session.setAttribute("companyLogo",company.getCompany_logo());
			model.addAttribute("resultComLogo",resultComLogo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "enovironmentSetting/planPublicRelationsSetting/companyInfo";
	}
	
	/**
	 * 회사 로고 제외한 정보 수정을 처리하는 메서드
	 */
	@RequestMapping("/companyInfo")
	public String companyInfoForm(
			@RequestParam("zipNo") String company_zip,
			@RequestParam("roadAddrPart1") String compay_addr1,
			@RequestParam("addrDetail") String compay_addr2,
			CompanyVO companyVO,
			Model model){
		
		String url = "enovironmentSetting/planPublicRelationsSetting/companyInfo";
		int result = -1;
		
		companyVO.setCompany_zip(company_zip);
		companyVO.setCompany_addr1(compay_addr1);
		companyVO.setCompany_addr2(compay_addr2);
		
		//회사 정보가 있는지 확인하기 위해서 select로 불러온다
		CompanyVO companyInit = null;
		try {
			companyInit = appointedUIService.checkCompany();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//companyVO 가 null이 아니다->해당 정보를 수정해준다
		if(companyInit != null){
			try {
				result = planPublicRelationsSettingService.modifyCompanyInfo(companyVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("resultCompanyInfo",result);
		}else {	//companyVO 가 null이다-> 회사정보를 insert해준다
			try {
				result = planPublicRelationsSettingService.joinCompanyInfo(companyVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//insert,update를 완료하고 나서 해당 변경된 회사 정보를 다시 select로 불러와서 화면에 보여지게 한다
		CompanyVO companyView = null;
		try {
			companyView = appointedUIService.checkCompany();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("companyInfo",companyView);
		
		return url;
	}
	
	
	/**
	 * 회사 부서 설정 화면으로 이동
	 */
	@RequestMapping("/companyDepartForm")
	public String companyDepartForm(Model model){
		
		String url = "enovironmentSetting/planPublicRelationsSetting/companyDepart";
		try {
			planPublicRelationsSettingService.viewDeptInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	/**
	 * 회사 부서 등록 컨트롤러
	 */
	@RequestMapping(value="/companyDepartInsert",method=RequestMethod.POST)
	public String companyDepartInsert(Model model, HttpServletRequest request, 
			DepartmentVO deptVO){
		System.out.println("부서 컨트롤러 진입!!");
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyDepartForm";

		try {
			//부서등록
			planPublicRelationsSettingService.enrollDept(model, deptVO);
			//변경된 부서 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewDeptInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	/**
	 * 부서하나의 정보를 가져오는 컨트롤러(ajax)
	 */
	@RequestMapping("/checkDeptOne")
	public @ResponseBody Map<String,Object> selectDeptOne(String tmp_dept_number){
		Map<String,Object> map = new HashMap<String, Object>();
		DepartmentVO dvo = null;
		try {
			dvo = planPublicRelationsSettingService.getDeptOne(tmp_dept_number);
			System.out.println("업데이트할 부서 이름 : "+dvo.getDept_name());
			map.put("dept_number", dvo.getDept_number());
			map.put("dept_name", dvo.getDept_name());
			map.put("dept_membernumber", dvo.getDept_membernumber());
			map.put("dept_tel", dvo.getDept_tel());
			map.put("dept_addr", dvo.getDept_addr());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 회사 부서 수정하는 컨트롤러
	 */
	@RequestMapping("/companyDepartmentUpdate")
	public String companyDepartmentUpdate(Model model,
			@RequestParam("up_dept_number") String up_dept_number,
			@RequestParam("up_dept_name") String up_dept_name,
			@RequestParam("up_dept_membernumber") String up_dept_membernumber,
			@RequestParam("up_dept_membernumber_origin") String up_dept_membernumber_origin,
			@RequestParam("up_dept_tel") String up_dept_tel,
			@RequestParam("up_dept_addr") String up_dept_addr ,
			HttpServletRequest request){
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyDepartForm";
		
		try {
			//수정할 멤버를 선택하지 않았다면~!!(셀렉트박스에서 선택하지 않았다면)
			String inputMember = "";
			if(up_dept_membernumber.equals("selectDept")){
				inputMember = up_dept_membernumber_origin;
			}else{
				inputMember = up_dept_membernumber;
			}
			//부서 수정
			planPublicRelationsSettingService
				.modifyDept(model, up_dept_number, up_dept_name, inputMember, up_dept_tel, up_dept_addr);
			//변경된 부서 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewDeptInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	
	/**
	 * 회사 부서 삭제하는 컨트롤러
	 */
	@RequestMapping("/companyDepartDelete")
	public String companyDepartDelete(Model model,
			@RequestParam("deleteDeptNum") String deleteDeptNum){
		
		String url = "enovironmentSetting/planPublicRelationsSetting/companyDepart";
		
		try {
			//부서 삭제
			planPublicRelationsSettingService.removeDept(model,deleteDeptNum);
			//변경된 부서 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewDeptInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * 회사 직급 설정 화면으로 이동
	 */
	@RequestMapping("/companyPositionForm")
	public String companyPositionForm(Model model){
		String url = "enovironmentSetting/planPublicRelationsSetting/companyPosition";
		try {
			//회사 직급 정보를 가져온다
			planPublicRelationsSettingService.viewPositionInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * 회사 직급 등록 컨트롤러
	 */
	@RequestMapping("/companyPositionInsert")
	public String companyDepartInsert(Model model, HttpServletRequest request,
			PositionVO positionVO){
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyPositionForm";

		try {
			//직급등록
			planPublicRelationsSettingService.enrollPosition(model, positionVO);
			//회사 직급 정보를 가져온다
			planPublicRelationsSettingService.viewPositionInfo(model);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	
	
	/**
	 * 직급하나의 정보를 가져오는 컨트롤러(ajax)
	 */
	@RequestMapping("/checkPositionOne")
	public @ResponseBody Map<String,Object> selectPositionOne(String tmp_position_number){
		Map<String,Object> map = new HashMap<String, Object>();
		PositionVO pvo = null;
		try {
			pvo = planPublicRelationsSettingService.getPositiontOne(tmp_position_number);
			map.put("position_number", pvo.getPosition_number());
			map.put("position_name", pvo.getPosition_name());
			map.put("position_level", pvo.getPosition_level());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 직급 수정하는 컨트롤러
	 */
	@RequestMapping(value="/updateLevelUp")
	public String companyPositionUpdateLevelUp(Model model,
			@RequestParam("tmp_position_number") String tmp_position_number,
			HttpServletRequest request
			){
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyPositionForm";
		
		try {
			//직급 수정
			planPublicRelationsSettingService
				.modifyPositionLevelUp(model, tmp_position_number);
			//변경된 직급 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewPositionInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	/**
	 * 직급 레벨 하위로 수정하는 컨트롤러
	 */
	@RequestMapping(value="/updateLevelDown")
	public String companyPositionUpdateLevelDown(Model model,
			@RequestParam("tmp_position_number") String tmp_position_number,
			HttpServletRequest request
			){
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyPositionForm";
		
		try {
			//직급 수정
			planPublicRelationsSettingService
				.modifyPositionLevelDown(model, tmp_position_number);
			//변경된 직급 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewPositionInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	/**
	 * 직급 레벨 상위로 수정하는 컨트롤러
	 */
	@RequestMapping(value="/companyPositionUpdate",method=RequestMethod.POST)
	public String companyPositionUpdate(Model model,
			@RequestParam("up_position_number") String up_position_number,
			@RequestParam("up_position_name") String up_position_name,
			HttpServletRequest request
			){
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyPositionForm";
		
		try {
			//직급 수정
			planPublicRelationsSettingService
			.modifyPosition(model, up_position_number, up_position_name);
			//변경된 직급 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewPositionInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	
	/**
	 * 회사 직급 삭제하는 컨트롤러
	 */
	@RequestMapping("/companyPositionDelete")
	public String companyPositionDelete(Model model, HttpServletRequest request, 
			@RequestParam("tmp_position_number") String deletePositionNum){
		
		String url = "redirect:"+request.getContextPath()+"/enovironmentSetting/planPublicRelationsSetting/companyPositionForm";
		
		try {
			//직급 삭제
			planPublicRelationsSettingService.removePosition(model,deletePositionNum);
			//변경된 직급 정보를 다시 가져온다~
			planPublicRelationsSettingService.viewPositionInfo(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	
	
	
	/**
	 * 조직도 설정 화면으로 이동
	 */
//	@RequestMapping("/organizationForm")
//	public String organizationForm(){
//		String url = "enovironmentSetting/planPublicRelationsSetting/organization";
//		return url;
//	}
	
	/**
	 * 급여 정책 설정 화면으로 이동
	 */
	@RequestMapping("/salaryForm")
	public String salaryForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/salary";
		return url;
	}
}
