package com.nyngw.environmentSetting.humanResourceSetting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 기획홍보부 설정 컨트롤러
 * @author pc07
 *
 */
@Controller
@RequestMapping("enovironmentSetting/humanResourceSetting")
public class HumanResourceSettingController {
	
	/**
	 * 휴가 종류 설정 화면으로 이동
	 */
	@RequestMapping("/vacationKindForm")
	public String vacationKindForm(){
		String url = "enovironmentSetting/humanResourceSetting/vacationKind";
		return url;
	}
	
	/**
	 * 휴가 종류 설정 화면으로 이동
	 */
	@RequestMapping("/vacationDaysForm")
	public String vacationDaysForm(){
		String url = "enovironmentSetting/humanResourceSetting/vacationDays";
		return url;
	}
	
	
	
	
}
