package com.smit.dao;

import java.util.List;

import com.smit.util.Page;
import com.smit.vo.User;

public interface UserDao {
	public User findById(int id);
	public void save(User user);
	public Page findAll(int currentPage,int pageSize);
	public void delete(int id);
	public List findByParams(Object[] params);

}
