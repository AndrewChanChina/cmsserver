package com.smit.web;

import org.apache.struts.action.ActionForm;


public class SmitLoginForm extends ActionForm {
	private String userName;
	private String passwd;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
