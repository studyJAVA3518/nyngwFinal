package com.nyngw.environmentSetting.planPublicRelationsSetting.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nyngw.dto.CompanyVO;
import com.nyngw.environmentSetting.planPublicRelationsSetting.service.PlanPublicRelationsSettingServiceImpl;
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
	public String workingDaysForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/workingDay";
		return url;
	}
	
	/**
	 * 엑셀파일을 수동으로 업로드하는 메서드
	 * @param multipartFile
	 * @param title
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/multipartFile",method=RequestMethod.POST)
	public String uploadlFile(
			@RequestParam("logoFile") MultipartFile multipartFile,
			@RequestParam("company_number2") String company_number2,
			Model model,
			HttpServletRequest request
			) throws IOException{
		
		String upload = request.getSession()
				.getServletContext().getRealPath("WEB_INF/uplode");
		File file = null;
		if(!multipartFile.isEmpty()){
			file = new File(upload,multipartFile.getOriginalFilename());
			
//			multipartFile.transferTo(file);
			model.addAttribute("fileName",multipartFile.getOriginalFilename());
			model.addAttribute("uploadPath",file.getAbsolutePath());
		}
		int resultComLogo = -1;
		try {
			resultComLogo = planPublicRelationsSettingService.modifyCompanyLogo(file.getAbsolutePath(), company_number2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "enovironmentSetting/planPublicRelationsSetting/companyInfoForm";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("companyInfo",companyView);
		
		
		return url;
	}
	
	/**
	 * 회사 부서 설정 화면으로 이동
	 */
	@RequestMapping("/companyDepartForm")
	public String companyDepartForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/companyDepart";
		return url;
	}
	
	/**
	 * 회사 직급 설정 화면으로 이동
	 */
	@RequestMapping("/companyPositionForm")
	public String companyPositionForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/companyPosition";
		return url;
	}
	
	/**
	 * 조직도 설정 화면으로 이동
	 */
	@RequestMapping("/organizationForm")
	public String organizationForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/organization";
		return url;
	}
	
	/**
	 * 급여 정책 설정 화면으로 이동
	 */
	@RequestMapping("/salaryForm")
	public String salaryForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/salary";
		return url;
	}
}
