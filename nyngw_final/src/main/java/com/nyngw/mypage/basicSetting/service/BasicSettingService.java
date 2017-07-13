package com.nyngw.mypage.basicSetting.service;

import com.nyngw.dto.MemberVO;

public interface BasicSettingService {

	public MemberVO selectMember(String id);

	public int updateMember(MemberVO vo);

}
