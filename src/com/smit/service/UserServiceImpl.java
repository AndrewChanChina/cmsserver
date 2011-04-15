package com.smit.service;

import java.util.List;

import com.smit.dao.IUserDao;
import com.smit.util.SmitPage;
import com.smit.vo.User;


public class UserServiceImpl implements IUserService {

	private IUserDao userDao;	

	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean login(String userName, String passwd) {
		
		if(userDao.isAdmin(userName, passwd)){
			return true;
		}
		return false;
	}
	@Override
	public boolean register(String userName,String password,String email,String telphone)
	{		
		User user = new User();
		user.setUserName(userName);		
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(telphone);
		
		return userDao.register(user);
	}
	@Override
	public User findGroupByName(String userName) {
		return userDao.findGroupByName(userName);
	}
	@Override
	public void save(User user) {
		userDao.save(user);		
	}
	@Override
	public void update(User user) {
		userDao.update(user);		
	}
	@Override
	public void delete(User user) {
		userDao.delete(user);		
	}
	@Override
	public User getUser(Integer id) {
		return userDao.getUser(id);
	}
	@Override
	public List listAllUsers(SmitPage page) {
		return userDao.listAllUsers(page);
	}

}
