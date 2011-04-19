package com.smit.web.form;

import org.apache.struts.action.ActionForm;


public class SmitLoginForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163171742841875769L;
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
