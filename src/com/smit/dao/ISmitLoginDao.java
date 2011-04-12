package com.smit.dao;

import com.smit.vo.User;

public interface ISmitLoginDao {

	public boolean isAdmin(String userName, String password);
	public boolean register(User user);
	public boolean updateUser(User user);
}
