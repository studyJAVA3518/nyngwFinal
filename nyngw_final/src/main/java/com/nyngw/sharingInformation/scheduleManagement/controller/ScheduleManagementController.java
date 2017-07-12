package com.nyngw.sharingInformation.scheduleManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.sharingInformation.scheduleManagement.service.ScheduleManagementServiceImpl;


/**
 * Schedule - 일정
 * @author pc09
 *
 */

@Controller
@RequestMapping("/sharingInformation/scheduleManagement")
public class ScheduleManagementController {
	@Autowired
	private ScheduleManagementServiceImpl scheduleManagementServiceImpl;
	
	/**
	 * 일정추가버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 입력폼 url 반환
	 */
	@RequestMapping("/schedule")
	public String scheduleCheck(){
		
		return "sharingInformation/scheduleManagement/schedule";
	}
	
	/**
	 * 일정추가버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 입력폼 url 반환
	 */
	@RequestMapping("8")
	public String scheduleWriteForm(){
		
		return null;
	}
	
	/**
	 * 정보를 입력한 뒤 등록 하는 버튼
	 * @return 등록한뒤 화면전환 url 반환
	 */
	@RequestMapping("7")
	public String scheduleWrite(){
		
		return null;
	}
	
	/**
	 * 일정수정버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 수정폼 url 반환
	 */
	@RequestMapping("6")
	public String scheduleUpdateForm(){
		
		return null;
	}
	
	/**
	 * 정보를 입력한 뒤 수정 하는 버튼
	 * @return 수정한 뒤 화면전환 url 반환
	 */
	@RequestMapping("5")
	public String scheduleUpdate(){
		
		return null;
	}
	
	/**
	 * 일정삭제버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 삭제폼 url 반환
	 */
	@RequestMapping("4")
	public String scheduleDeleteForm(){
		
		return null;
	}
	
	/**
	 * 삭제 하는 버튼
	 * @return 삭제시 화면전환 url 반환
	 */
	@RequestMapping("3")
	public String scheduleDelete(){
		
		return null;
	}

	/**
	 * 일정을 클릭하여 상세보기 페이지로 가능 url 반환 메서드
	 * @return 상세페이지 url 반환
	 */
	@RequestMapping("2")
	public String scheduleDetail(){
		
		return null;
	}
	
	
	
	/**
	 * 상세보기에서 목록보기 버튼을 누르거나 등록, 수정, 삭제 화면에서 취소버튼을 누르면 보던 리스트 페이지로 가는 url을 반환하는 메서드
	 * @return 자신이 보던 리스트 화면으로 돌아가는 url 반환
	 */
	@RequestMapping("1")
	public String cancleAndList(){
		
		return null;
	}
	
	
}
