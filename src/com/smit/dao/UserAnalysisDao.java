package com.smit.dao;

import java.util.List;

import com.smit.vo.UserAnalysis;

public interface UserAnalysisDao {

	public void insert(UserAnalysis us);
	public List<UserAnalysis> getAnalysis();
	
}
