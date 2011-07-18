package com.smit.service;

import java.util.List;

import com.smit.vo.UserAnalysis;


public interface UserAnalysisService {
	
	public void insert(UserAnalysis us);
	public List<UserAnalysis> getAnalysis();
}
