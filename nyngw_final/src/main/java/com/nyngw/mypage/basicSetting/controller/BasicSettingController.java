package com.nyngw.mypage.basicSetting.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.nyngw.dto.MemberVO;
import com.nyngw.dto.MemberVOup;
import com.nyngw.mypage.basicSetting.service.BasicSettingServiceImpl;



@Controller
@RequestMapping("/mypage/basicSetting")
public class BasicSettingController {
	
	@Autowired
	private BasicSettingServiceImpl basicSettingServiceImpl; 
	
	
	
	/**
	 * 자신의 서명을 등록하는 폼의 url을 반환하는 메서드
	 * @return 서명등록하는 페이지를 보여줄 url
	 */
	@RequestMapping("/sign")
	public String signInsertForm(Model model, Principal principal){
		System.out.println("사인 들어옴");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = user.getUsername();
		
		MemberVO vo =  basicSettingServiceImpl.selectMember(mem_id);
		model.addAttribute("MemberVoDetail",vo);
		
		return "mypage/basicSetting/sign";
	}
	
	/**
	 * 자신의 서명을 재등록 하기 위해 화면을 전환하는 메서드
	 * @return	서명 재등록하는 페이지를 보여줄 url
	 */
//	@RequestMapping("")
//	public String signUpdateFrom(){
//		
//		return null;
//	}
	
	/**
	 * 정보를 입력한뒤 수정 버튼을 통해 화면전환 하는 url을 반환하는 메서드
	 * @return	수정 url 반환
	 */
	@RequestMapping("/updateMemberForm")
	public String updateMemberForm(Model model, MemberVO vo ){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String mem_id = user.getUsername();
		
		vo =  basicSettingServiceImpl.selectMember(mem_id);
		model.addAttribute("MemberVoDetail",vo);
		
		return "mypage/basicSetting/updateMemberForm";
	}
	
	@RequestMapping(value="/updateMember", method=RequestMethod.POST)
	public String updateMember(MemberVO vo,Principal principal,Model model,String mem_npwd, MemberVOup voup) throws IOException{
		String upload = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/resources/memface";
		String upload2 = "D:/git/nyngw/nyngw_final/nyngw_final/src/main/webapp/resources/memsign";
		
		MultipartFile multipartFile = voup.getMem_imgup();
		MultipartFile multipartFile2 = voup.getMem_signup();
		String mem_id = principal.getName();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO mem = basicSettingServiceImpl.selectMember(user.getUsername());
		MemberVO memup = voup.toMemberVO();
		
		if(vo.getMem_pwd().equals(mem.getMem_pwd())){
			
			if(!multipartFile.isEmpty()){
				File file = new File(upload , multipartFile.getOriginalFilename());//+"$$"+System.currentTimeMillis()
				multipartFile.transferTo(file);
				System.out.println(file);
				memup.setMem_img(multipartFile.getOriginalFilename());
				vo.setMem_img(memup.getMem_img());
			}else{
				vo.setMem_img(mem.getMem_img());
			}
			
			if(!multipartFile2.isEmpty()){
				File file2 = new File(upload2 , multipartFile2.getOriginalFilename());//+"$$"+System.currentTimeMillis()
				multipartFile2.transferTo(file2);
				memup.setMem_sign(multipartFile2.getOriginalFilename());
				vo.setMem_sign(memup.getMem_sign());
			}else{
				vo.setMem_sign(mem.getMem_sign());
			}
			vo.setMem_id(mem_id);
			
			if(mem_npwd.isEmpty()){
				vo.setMem_pwd(mem.getMem_pwd());
			}else{
				vo.setMem_pwd(mem_npwd);
			}
			
			basicSettingServiceImpl.updateMember(vo);
		}else{
			return "redirect:/mypage/basicSetting/updateMemberForm";
		}
		
		return "redirect:/mypage/basicSetting/sign";
	}
	
	/**
	 * 파일업로드 등록버튼
	 * @return 파일 업로드
	 */
	
	
}
