package com.nyngw.environmentSetting.planPublicRelationsSetting.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
			System.out.println("기본로고!!!!!!!!!!!!!!");
		
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
				System.out.println("로고 변경시!!!!!!!!!!!!!!");
			}
		}
		
		System.out.println("파일패스: "+uploadFilePath);
		System.out.println("회사번호: "+company_number2);
		//파일 경로를 update한다
		try {
			//파일경로를 업데이트하고 결과값을 int로 저장
			resultComLogo = planPublicRelationsSettingService.modifyCompanyLogo(uploadFilePath, session.getAttribute("companyNumber").toString());
			System.out.println("업데이트 결과값 : "+resultComLogo);
			//company
			company = appointedUIService.checkCompany();
			//session에 logo경로 저장
			session.setAttribute("companyLogo",company.getCompany_logo());
			System.out.println("session에 저장된 값: "+company.getCompany_logo());
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
