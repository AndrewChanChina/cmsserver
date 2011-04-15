package com.smit.web.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	
	private String userName;
	private String email;
	private String tel;
	private String password;
	private String password2;
	private String id;
	private String hideId;
	private String groupId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHideId() {
		return hideId;
	}
	public void setHideId(String hideId) {
		this.hideId = hideId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}	
}
