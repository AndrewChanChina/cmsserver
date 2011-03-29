package com.smit.service;

import com.smit.dao.LoginDao;

public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao;
    
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public boolean login(String userName, String passwd) {
		if(loginDao.getAdminMsg(userName, passwd)){
			return true;
			
		}else 
			return false;
	}

}
