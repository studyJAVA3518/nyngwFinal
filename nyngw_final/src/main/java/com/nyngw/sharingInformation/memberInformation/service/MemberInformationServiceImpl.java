package com.nyngw.sharingInformation.memberInformation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.sharingInformation.memberInformation.dao.MemberInformationDaoImpl;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {
	@Autowired
	private MemberInformationDaoImpl memberInformationDaoImpl;
}
