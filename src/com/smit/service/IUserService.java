package com.smit.service;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.User;

public interface IUserService {
	public boolean login(String userName,String passwd);
	public void register(String userName,String password,String email,String telphone);
	public void regDeveloper(String userName,String password,String email,String telphone);
	public void regUser(String userName,String password);
	
	public User findUserByName(String userName);
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public User getUser(Integer id);
	// For pagination
	public List listAllUsers(SmitPage page);
}
