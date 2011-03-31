package com.smit.vo;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-11
	 * @class 用户类
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String password;
	private String email;//邮箱地址
	private String tel;//联系方式
	private String explain;//用户说明
	private Integer state;//0:冻结状态；1：激活状态
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
