package com.smit.web;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {
	private String userName;
	private String passwd;
	private String passwd2;
	
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
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
}
