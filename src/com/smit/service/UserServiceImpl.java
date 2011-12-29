package com.smit.service;

import java.util.List;

import com.smit.dao.IUserDao;
import com.smit.util.Constants;
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
	
	public boolean login(String userName, String passwd) {
		
		if(userDao.isAdmin(userName, passwd)){
			return true;
		}
		return false;
	}
	/**
	 * register normal user
	 */
	public void register(String userName,String password,String email,String telphone)
	{		
		User user = new User();
		user.setUserName(userName);		
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(telphone);
		
		userDao.register(user,"normal");
	}
	/**
	 * register developer to developer group
	 */
	public void regDeveloper(String userName,String password,String email,String telphone){
		User user = new User();
		user.setUserName(userName);		
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(telphone);
		
		userDao.register(user,"developer");
	}
	public void regUser(String userName,String password){
		User user = new User();
		user.setUserName(userName);	
		user.setPassword(password);
		
		userDao.register(user,Constants.PUSH_GROUPNAME);
	}
	public User findUserByName(String userName) {
		return userDao.findGroupByName(userName);
	}
	public void save(User user) {
		userDao.save(user);		
	}
	public void update(User user) {
		userDao.update(user);		
	}
	public void delete(User user) {
		userDao.delete(user);		
	}
	public User getUser(Integer id) {
		return userDao.getUser(id);
	}
	public List listAllUsers(SmitPage page) {
		return userDao.listAllUsers(page);
	}

}
