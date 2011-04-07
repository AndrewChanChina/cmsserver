package com.smit.service;

import com.smit.dao.SmitLogin;

public class SmitLoginService implements ISmitLoginService {

	private SmitLogin smitLogin;

	public void setSmitLogin(SmitLogin smitLogin) {
		this.smitLogin = smitLogin;
	}


	@Override
	public boolean login(String userName, String passwd) {
		
		if(smitLogin.isAdmin(userName, passwd)){
			return true;
		}
		return false;
	}

}
