package com.nyngw.environmentSetting.planPublicRelationsSetting.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 기획홍보부 설정 컨트롤러
 * @author pc07
 *
 */
@Controller
@RequestMapping("enovironmentSetting/planPublicRelationsSetting")
public class PlanPublicRelationsSettingController {
	
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
	public String uploadExcelFile(
			@RequestParam("file") MultipartFile multipartFile,
			Model model,
			HttpServletRequest request
			) throws IOException{
		
		String upload = request.getSession()
				.getServletContext().getRealPath("WEB_INF/uplode");
		
		
		if(!multipartFile.isEmpty()){
			File file = new File(upload,multipartFile.getOriginalFilename());
		
//			multipartFile.transferTo(file);
			
			model.addAttribute("fileName",multipartFile.getOriginalFilename());
			model.addAttribute("uploadPath",file.getAbsolutePath());
		}
		return "enovironmentSetting/planPublicRelationsSetting/workingDay";
	}
	
	/**
	 * 회사 정보 설정 화면으로 이동
	 */
	@RequestMapping("/companyInfoForm")
	public String companyInfoForm(){
		String url = "enovironmentSetting/planPublicRelationsSetting/companyInfo";
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
