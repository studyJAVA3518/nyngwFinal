package com.nyngw.sharingInformation.scheduleManagement.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.common.service.CommonServiceImpl;
import com.nyngw.dto.DepartmentVO;
import com.nyngw.dto.MemberVO;
import com.nyngw.dto.ScheduleVO;
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
	private ScheduleManagementServiceImpl scheduleManagementService;
	
	@Autowired
	private CommonServiceImpl commonService;
	/**
	 * 일정추가버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 입력폼 url 반환
	 */
	@RequestMapping("/schedule")
	public String scheduleCheck(Model model,String sc_code_number,Principal principal){
		String mem_id = principal.getName();
		MemberVO member = commonService.findMemberByMemId(mem_id);
		//로그인한 유저의 부서
		String mem_number = member.getMem_number();
		if(sc_code_number.equals("code5")){
			List<ScheduleVO> schedule = scheduleManagementService.SI_schedule_sc_mem_number(model, sc_code_number);
			List<ScheduleVO> scheduleList = new ArrayList<ScheduleVO>();
			model.addAttribute("scheduleList",scheduleList);
			for(int i = 0; i<schedule.size(); i++){
				MemberVO member2 = commonService.findMemberByMemNumber(schedule.get(i).getSc_mem_number());
				if(member.getMem_dept_number().equals(member2.getMem_dept_number())){
					String sc_mem_number = schedule.get(i).getSc_mem_number();
					scheduleManagementService.SI_selectDepartmentSchedule(model, sc_code_number, mem_number, sc_mem_number);
					System.out.println("부서일정가져와!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule.get(i).getSc_mem_number()+"스케줄등록한 사람???");
					System.out.println("부서일정가져와!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+mem_number+"로그인한 사람???");
					System.out.println("로그인한 사람의 부서번호!!!!!!!!!"+member.getMem_dept_number());
					System.out.println("일정등록한 사람의 부서번호!!!!!!!!!"+member2.getMem_dept_number());
				}
			}
			
		}else if(sc_code_number.equals("code6")){
			scheduleManagementService.SI_selectCompanySchedule(model, sc_code_number);
		}else{
			scheduleManagementService.getAllSchedule(model, sc_code_number,member.getMem_number());
		}

		if(sc_code_number.equals("code4")||sc_code_number.equals("code5")){
			model.addAttribute("au", "ok");
		}else{
			model.addAttribute("au", "");
			if(member.getMem_mngr_number().equals("role_master")||member.getMem_mngr_number().equals("role_prr_admin")){
				model.addAttribute("au", "ok");
			}
		}
		model.addAttribute("sc_code_number",sc_code_number);
		return "sharingInformation/scheduleManagement/schedule";
	}

	/**
	 * 일정을 클릭하여 상세보기 페이지로 가능 url 반환 메서드
	 * @return 상세페이지 url 반환
	 */
	@RequestMapping("/scheduleDetail")
	public String scheduleDetail(String sc_number,Model model,Principal principal){
		scheduleManagementService.getSchedule(model, sc_number,principal);
		return "sharingInformation/scheduleManagement/scheduleDetail";
	}
	
	
	/**
	 * 일정수정버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 수정폼 url 반환
	 */
	@RequestMapping("/scheduleEditForm")
	public String scheduleEditForm(Model model,String sc_number,Principal principal){
		scheduleManagementService.getSchedule(model, sc_number,principal);
		return "sharingInformation/scheduleManagement/scheduleEditForm";
	}
	
	/**
	 * 정보를 입력한 뒤 수정 하는 버튼
	 * @return 수정한 뒤 화면전환 url 반환
	 */
	@RequestMapping("/scheduleEdit")
	public String scheduleEdit(ScheduleVO schedule){
		scheduleManagementService.editSchedule(schedule);
		return "redirect:/sharingInformation/scheduleManagement/scheduleDetail?sc_number="+schedule.getSc_number();
	}
	
	/**
	 * 삭제 하는 버튼
	 * @return 삭제시 화면전환 url 반환
	 */
	@RequestMapping("/scheduleDelete")
	public String scheduleDelete(String sc_number,String sc_code_number){
		scheduleManagementService.deleteSchedule(sc_number);
		return "redirect:/sharingInformation/scheduleManagement/schedule?sc_code_number="+sc_code_number;
	}
	
	/**
	 * 일정추가버튼을 누를시 데이터를 입력하는 url을 반환하는 메서드
	 * @return 입력폼 url 반환
	 */
	@RequestMapping("/scheduleWriteForm")
	public String scheduleWriteForm(String sc_code_number, Model model){
		model.addAttribute("sc_code_number",sc_code_number);
		return "sharingInformation/scheduleManagement/scheduleWriteForm";
	}
	
	
	/**
	 * 정보를 입력한 뒤 등록 하는 버튼
	 * @return 등록한뒤 화면전환 url 반환
	 */
	@RequestMapping("/scheduleWrite")
	public String scheduleWrite(ScheduleVO schedule,String sc_code_number,Principal principal){
		//xml의 알림항목은 '' 
		String mem_id = principal.getName();
		MemberVO member = commonService.findMemberByMemId(mem_id);
		schedule.setSc_mem_number(member.getMem_number());
		scheduleManagementService.writeSchedule(schedule);
		return "redirect:/sharingInformation/scheduleManagement/schedule?sc_code_number="+sc_code_number;
	}
	
	/**
	 * 상세보기에서 목록보기 버튼을 누르거나 등록, 수정, 삭제 화면에서 취소버튼을 누르면 보던 리스트 페이지로 가는 url을 반환하는 메서드
	 * @return 자신이 보던 리스트 화면으로 돌아가는 url 반환
	 */
	@RequestMapping("1")
	public String cancleAndList(){
		
		return null;
	}
	
	@RequestMapping("side")
	public @ResponseBody Map<String,Object> sidebarSchedule(Principal principal,String date){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,String> param = new HashMap<String, String>();
		map.put("su", "no");
		
		param.put("mem_id", principal.getName());
		param.put("sc_date", date);
		
		List<ScheduleVO> scheduleList = null;
		try{
			scheduleList = scheduleManagementService.todayMemberSchedule(param);
			map.put("sc", scheduleList);
		}catch(Exception e){
		}
		
		if(scheduleList!=null && scheduleList.size()>0){
			map.put("su", "ok");
		}
		
		return map;
	}
	
	
}
