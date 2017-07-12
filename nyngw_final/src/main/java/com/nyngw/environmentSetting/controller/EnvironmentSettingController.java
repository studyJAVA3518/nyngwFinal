package com.nyngw.environmentSetting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 환경설정관리 팝업 컨트롤러
 * @author pc07
 *
 */
@Controller
@RequestMapping("enovironmentSetting")
public class EnvironmentSettingController {
	
	/**
	 * 회사주소 검색시 주소검색 팝업 화면으로 이동
	 */
	@RequestMapping("/jusoPopupForm")
	public String jusoPopupForm(){
		String url = "enovironmentSetting/juso";
		return url;
	}
	
	/**
	 * 회사 부서 등록 팝업 화면으로 이동
	 */
	@RequestMapping("/deptPopupForm")
	public String companyDepartPopupForm(){
		String url = "enovironmentSetting/deptInsertPopup";
		return url;
	}

	/**
	 * 회사 직급 등록 팝업 화면으로 이동
	 */
	@RequestMapping("/positionPopupForm")
	public String positionPopupForm(){
		String url = "enovironmentSetting/positionInsertPopup";
		return url;
	}
	
	
}
