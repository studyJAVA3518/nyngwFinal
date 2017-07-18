package com.nyngw.environmentSetting.humanResourceSetting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyngw.dto.Member_Carear_VacationVO;
import com.nyngw.dto.Paging;
import com.nyngw.dto.Vacation_PolicyVO;
import com.nyngw.dto.Vacation_TotalVO;
import com.nyngw.dto.Year_VacationVO;
import com.nyngw.environmentSetting.humanResourceSetting.service.HumanResourceSettingServiceImpl;


/**
 * 기획홍보부 설정 컨트롤러
 * @author pc07
 *
 */
@Controller
@RequestMapping("enovironmentSetting/humanResourceSetting")
public class HumanResourceSettingController {
	
	@Autowired
	private HumanResourceSettingServiceImpl humanResurceSettingsService;
	
	/**
	 * 휴가 종류 설정 화면으로 이동
	 */
	@RequestMapping("/vacationKindForm")
	public String vacationKindForm(Model model){
		
		List<Vacation_PolicyVO> vacationList =  humanResurceSettingsService.getVacationKind();
		
		model.addAttribute("vacationList",vacationList);
		String url = "enovironmentSetting/humanResourceSetting/vacationKind";
		return url;
	}
	
	/**
	 * 휴가 종류 설정 화면으로 이동
	 */
	@RequestMapping("/vacationDaysForm")
	public String vacationDaysForm(Model model,String page,Member_Carear_VacationVO mcv){
		
		if(mcv.getMem_dept_number()!=null){
			if(mcv.getMem_dept_number().equals("all")){
				mcv.setMem_dept_number(null);
			}
		}
		
		System.out.println(mcv.getMem_dept_number());
		
		int p=1;
		if(page != null){
			p=Integer.valueOf(page);
			if(p<1){
				p=1;
			}
		}
		Paging paging = new Paging(p, 10);
		paging.setNumberOfRecords(humanResurceSettingsService.countContent(mcv));
		
		int firstRow = 0;
		int endRow = 0;
		
		paging.makePaging();
		
		firstRow = (paging.getCurrentPageNo() - 1) * paging.getRecordsPerPage() + 1;
		endRow = firstRow + paging.getRecordsPerPage() - 1;
		
		mcv.setStartPage(firstRow-1);
		mcv.setEndPage(endRow);
		
		List<Year_VacationVO> yearList = humanResurceSettingsService.getVacationYearSetting();
		List<Member_Carear_VacationVO> memberVacationList = humanResurceSettingsService.getCarearVacationSet(mcv);
		
		model.addAttribute("dept_number", mcv.getMem_dept_number());
		model.addAttribute("page", paging);
		model.addAttribute("yearList",yearList );
		model.addAttribute("memberList", memberVacationList);
		String url = "enovironmentSetting/humanResourceSetting/vacationDays";
		return url;
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> saveVacationKind(
			@RequestParam(value="numbers[]")List<String> numbers,
			@RequestParam(value="ons[]")List<String> on,@RequestParam(value="days[]")List<String> day){
		Map<String,Object> map = new HashMap<String, Object>();
		Vacation_PolicyVO vacation = new Vacation_PolicyVO();

		map.put("su", "ok");
		for (int i = 0; i < numbers.size(); i++) {
			vacation.setVp_number(numbers.get(i));
			vacation.setVp_payonoff(on.get(i));
			vacation.setVp_totalday(day.get(i));
			try{
				humanResurceSettingsService.updateVacation(vacation);
			}catch(Exception e){
				map.put("su", "no");
			}
		}
		
		return map;
	}
	
	@RequestMapping("/modify")
	public @ResponseBody Map<String,Object> saveVacationKind(
			@RequestParam(value="numbers[]")List<String> numbers,
			@RequestParam(value="ons[]")List<String> on){
		Map<String,Object> map = new HashMap<String, Object>();
		Year_VacationVO vacation = new Year_VacationVO();
		map.put("su", "ok");
		for (int i = 0; i < numbers.size(); i++) {
			vacation.setYv_year(numbers.get(i));
			vacation.setYv_vacation_day(on.get(i));
			System.out.println(vacation);
			try{
				humanResurceSettingsService.setModifyVacationYearSetting(vacation);
			}catch(Exception e){
				map.put("su", "no");
			}
		}
		
		return map;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String,Object> saveVacationDeleteKind(
			@RequestParam(value="numbers[]")List<String> numbers){
		Map<String,Object> map = new HashMap<String, Object>();
		Vacation_PolicyVO vacation = new Vacation_PolicyVO();
		
		map.put("su", "ok");
		for (int i = 0; i < numbers.size(); i++) {
			vacation.setVp_number(numbers.get(i));
			try{
				humanResurceSettingsService.deleteVacation(vacation);
			}catch(Exception e){
				map.put("su", "no");
			}
		}
		return map;
	}
	
	@RequestMapping("/deleteYear")
	public @ResponseBody Map<String,Object> saveVacationDeleteYear(
			@RequestParam(value="numbers[]")List<String> numbers){
		Map<String,Object> map = new HashMap<String, Object>();
		Year_VacationVO vacation = new Year_VacationVO();
		
		map.put("su", "ok");
		for (int i = 0; i < numbers.size(); i++) {
			vacation.setYv_year(numbers.get(i));
			try{
				humanResurceSettingsService.deleteYearVacation(vacation);
			}catch(Exception e){
				map.put("su", "no");
			}
		}
		return map;
	}
	
	@RequestMapping("/addVacation")
	public @ResponseBody Map<String,Object> vacationAdd(Vacation_PolicyVO vacation){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("su", "ok");
		try{
			humanResurceSettingsService.insertVacation(vacation);
		}catch(Exception e){
			map.put("su", "no");
		}
		return map;
	}
	
	@RequestMapping("/addYearVacation")
	public @ResponseBody Map<String,Object> yearVacationAdd(Year_VacationVO vacation){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("su", "ok");
		try{
			humanResurceSettingsService.insertYearVacation(vacation);
		}catch(Exception e){
			map.put("su", "no");
		}
		return map;
	}
	
	@RequestMapping("/detailVacation")
	public String detailVacation(String mem_number,Model model){
		
		Member_Carear_VacationVO member = humanResurceSettingsService.getMember(mem_number);
		
		List<Vacation_TotalVO> vacationList = humanResurceSettingsService.getVacationList(member);
		
		model.addAttribute("member", member);
		model.addAttribute("vacationList", vacationList);
		return "enovironmentSetting/humanResourceSetting/memberDetailVacation";
	}
	
	
	
	
}
