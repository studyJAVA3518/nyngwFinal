package com.nyngw.mypage.basicSetting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 주소 팝업 띄우기!!
 * @return
 */

@Controller
@RequestMapping("/mypage")
public class jusoController {

	@RequestMapping("/juso")
	public String juso(){
		
		return "mypage/juso";
	}
}
