package com.nyngw.sharingInformation.noticeMatter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyngw.sharingInformation.noticeMatter.dao.NoticeMatterDaoImpl;

@Service
public class NoticeMatterServiceImpl implements NoticeMatterService {
	@Autowired
	private NoticeMatterDaoImpl noticeMatterDaoImpl;
}
