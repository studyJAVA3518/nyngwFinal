package com.nyngw.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.dto.MemberVO;
import com.nyngw.dto.PositionVO;
import com.nyngw.login.service.LoginServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginServiceImpl loginService;

	@Autowired
	private JavaMailSenderImpl mailSender;

	/**
	 * 아이디 찾는 폼을 보여줄 메서드
	 * @return 폼을 보여줄 url
	 */
	@RequestMapping("/searchForm")
	public String idFindForm(){
		return "/login/searchForm";
	}

	@RequestMapping("/searchId")
	public String idForm(MemberVO member,PositionVO position,Model model){

		Map<String,String> param = new HashMap<String, String>();

		param.put("mem_name", member.getMem_name());
		param.put("mem_email", member.getMem_email());
		param.put("position_name", position.getPosition_name());

		MemberVO mem = loginService.getMemberParam(param);

		if(mem!=null){
			try{
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setFrom("1z3803@naver.com");
				simpleMailMessage.setTo(mem.getMem_email());
				simpleMailMessage.setSubject("NYN 그룹웨어");
				simpleMailMessage.setText("회원님의 아이디는 '"+mem.getMem_id()+"'입니다.");

				mailSender.send(simpleMailMessage);
				model.addAttribute("message", "메일이 정상적으로 발송되었습니다.");
			}catch(Exception e){
				model.addAttribute("message", "발송 과정중 문제가 발생했습니다.");
			}
		}else{
			model.addAttribute("message", "잘못된 입력입니다.");
		}
		return "/login/complate";
	}

	@RequestMapping("/searchPwd")
	public String pwdForm(MemberVO member,Model model){

		MemberVO mem = loginService.getMemberSearchPwd(member);
		try{
			loginService.updatePwd(mem);
		}
		catch(Exception e){
			model.addAttribute("message", "비밀번호 변경 실패");
			return "/login/complate";			
		}
		mem = loginService.getMemberSearchPwd(member);
		if(mem!=null){
			try{
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setFrom("1z3803@naver.com");
				simpleMailMessage.setTo(mem.getMem_email());
				simpleMailMessage.setSubject("NYN 그룹웨어");
				simpleMailMessage.setText(mem.getMem_id()+"님의 비밀번호는 '"+mem.getMem_pwd()+"'입니다.\n 비밀번호를 변경해주세요.");

				mailSender.send(simpleMailMessage);
				model.addAttribute("message", "메일이 정상적으로 발송되었습니다.");
			}catch(Exception e){
				model.addAttribute("message", "발송 과정중 문제가 발생했습니다.");
			}
		}else{
			model.addAttribute("message", "잘못된 입력입니다.");
		}
		return "/login/complate";
	}

}
