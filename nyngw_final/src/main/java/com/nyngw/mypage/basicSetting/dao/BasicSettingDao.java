package com.nyngw.mypage.basicSetting.dao;

import com.nyngw.dto.MemberVO;

public interface BasicSettingDao {

	public MemberVO selectMember(String id);

	public int updateMember(MemberVO vo);

}
