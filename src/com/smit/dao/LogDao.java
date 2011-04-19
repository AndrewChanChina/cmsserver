package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;

public interface LogDao {
	public void save(LogInfo info);
	public void update(LogInfo info);
	public void delete(LogInfo info);
	//public User getUser(Integer id);
	// For pagination
	// if page == null, return all users
	public List listAllUsers(SmitPage page);
}
