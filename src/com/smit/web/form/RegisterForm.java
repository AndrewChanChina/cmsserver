package com.smit.web.form;

import org.apache.struts.validator.ValidatorForm;



/**
 * user register form
 * @author chenyzpower@gmail.com
 *
 */
public class RegisterForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6724967420974876592L;
	private String username;
	private String password;
	private String password2;
	private String tel;
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}	
	
}
