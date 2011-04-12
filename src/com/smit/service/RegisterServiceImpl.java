package com.smit.service;

import com.smit.dao.IGroupDao;
import com.smit.dao.ISmitLoginDao;
import com.smit.vo.Group;
import com.smit.vo.User;

public class RegisterServiceImpl implements IRegisterService{

	private ISmitLoginDao smitLoginDao;	
	private IGroupDao groupDao;
	
	public IGroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public ISmitLoginDao getSmitLoginDao() {
		return smitLoginDao;
	}
	
	public void setSmitLoginDao(ISmitLoginDao smitLoginDao) {
		this.smitLoginDao = smitLoginDao;
	}

	public boolean register(String userName,String password,String email,String telphone)
	{		
		User user = new User();
		user.setUserName(userName);		
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(telphone);
		
		return smitLoginDao.register(user);
	}
}
