package com.smit.service;

import com.smit.dao.ISmitLoginDao;


public class SmitLoginServiceImpl implements ISmitLoginService {

	private ISmitLoginDao smitLoginDao;	

	public ISmitLoginDao getSmitLoginDao() {
		return smitLoginDao;
	}

	public void setSmitLoginDao(ISmitLoginDao smitLoginDao) {
		this.smitLoginDao = smitLoginDao;
	}

	@Override
	public boolean login(String userName, String passwd) {
		
		if(smitLoginDao.isAdmin(userName, passwd)){
			return true;
		}
		return false;
	}

}
