package com.nyngw.sharingInformation.noticeMatter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nyngw.sharingInformation.noticeMatter.service.NoticeMatterServiceImpl;

@Controller
@RequestMapping("/sharingInformation/noticeMatter")
public class NoticeMatterController {
	@Autowired
	private NoticeMatterServiceImpl noticeMatterServiceImpl;
	
	/**
	 * 공지사항 List를 보여주는 페이지의 url을 반환하는 메소드
	 * @return  공지사항 url을 반환
	 */
	@RequestMapping("/nmList")
	public String noticeMatterCheck(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 공지사항 List를 보여주는 페이지의 url을 반환하는 메소드
	 * @return  공지사항 url을 반환
	 */
	@RequestMapping("/nmSelect")
	public String noticeMatterSelect(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 공지사항 List페이지에서 등록 버튼을 누를씨 등록페이지를 보여주는 url을 반환하는 메소드
	 * @return 공지사항등록페이지 url 반환
	 */
	@RequestMapping("/nmWriteForm")
	public String noticeMatterWriteForm(){
		
		return "sharingInformation/noticeMatter/noticeMatterWriteForm";
	}
	
	/**
	 * 정보를 입력한뒤 입력버튼을 눌러 화면을 전환해주는 url을 반환하는 메소드
	 * @return 등록한뒤 url 반환
	 */
	@RequestMapping("/nmWrite")
	public String noticeMatterWrite(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 공지사항 List페이지에서 수정 버튼을 누를시 수정페이지를 보여주는 url을 반환하는 메소드
	 * @return 공지사항수정페이지 url 반환
	 */
	@RequestMapping("/nmUpdateForm")
	public String noticeMatterUpdateForm(){
		
		return "sharingInformation/noticeMatter/noticeMatterUpdateForm";
	}
	
	/**
	 * 
	 * @return 공지사항수정페이지 url 반환
	 */
	@RequestMapping("/nmUpdate")
	public String noticeMatterUpdate(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 공지사항 List페이지에서 삭제 버튼을 누를시 삭제페이지를 보여주는 url을 반환하는 메소드
	 * @return 공지사항삭제페이지 url 반환
	 */
	@RequestMapping("/nmDeleteForm")
	public String noticeMatterDeleteForm(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 
	 * @return 공지사항삭제페이지 url 반환
	 */
	@RequestMapping("/nmDelete")
	public String noticeMatterDelete(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
	
	/**
	 * 등록된 공지사항을 선택하면 내용을 상세히 볼수 있는 페이지의 url을 반환하는 메서드
	 * @return 공지사항 상세페이지 url 반환
	 */
	@RequestMapping("/nmDetail")
	public String noticeMatterDetail(){
		
		return "sharingInformation/noticeMatter/noticeMatterDetail";
	}
	
	/**
	 * 상세보기에서 목록보기 버튼을 누르거나 등록, 수정, 삭제 화면에서 취소버튼을 누르면 보던 리스트 페이지로 가는 url을 반환하는 메서드
	 * @return 자신이 보던 리스트 화면으로 돌아가는 url 반환
	 */
	@RequestMapping("/cancle")
	public String cancleAndList(){
		
		return "sharingInformation/noticeMatter/noticeMatterList";
	}
}
