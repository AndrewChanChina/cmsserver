package com.smit.dao;

import java.util.List;

import com.smit.util.Page;
import com.smit.util.SmitPage;
import com.smit.vo.User;

public interface IUserDao {

	public boolean isAdmin(String userName, String password);
	public void register(User user,String groupName);
	
	public User findGroupByName(String userName);
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public User getUser(Integer id);
	// For pagination
	// if page == null, return all users
	public List listAllUsers(SmitPage page);
	


}
