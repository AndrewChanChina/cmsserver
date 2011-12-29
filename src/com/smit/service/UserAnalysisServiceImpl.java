package com.smit.service;

import java.util.List;

import com.smit.dao.UserAnalysisDao;
import com.smit.vo.UserAnalysis;

public class UserAnalysisServiceImpl implements UserAnalysisService{

	private UserAnalysisDao usDao;
	
	
	public UserAnalysisDao getUsDao() {
		return usDao;
	}

	public void setUsDao(UserAnalysisDao usDao) {
		this.usDao = usDao;
	}

	public void insert(UserAnalysis us) {
		usDao.insert(us);
	}

	public List<UserAnalysis> getAnalysis() {
		return usDao.getAnalysis();
	}

}
