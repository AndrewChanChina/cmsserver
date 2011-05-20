package com.smit.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String order_code;
	private String check_id;
	private String username;
	private String password;
	private String create_time;
	private Set<CertifiedProduct> products = new HashSet<CertifiedProduct>();
	private Set<DetailLog> logs = new HashSet<DetailLog>();
	private Order order;
	private Set<BaseLog> baseLogs = new HashSet<BaseLog>();
	
	//增加2个字段
	private String auth_code;//授权码
	private String machineID;//用户提交原始信息
	private String active_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getCheck_id() {
		return check_id;
	}
	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<CertifiedProduct> getProducts() {
		return products;
	}
	public void setProducts(Set<CertifiedProduct> products) {
		this.products = products;
	}
	public Set<DetailLog> getLogs() {
		return logs;
	}
	public void setLogs(Set<DetailLog> logs) {
		this.logs = logs;
	}
	
	
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getActive_status() {
		return active_status;
	}
	public void setActive_status(String active_status) {
		this.active_status = active_status;
	}
	public Set<BaseLog> getBaseLogs() {
		return baseLogs;
	}
	public void setBaseLogs(Set<BaseLog> baseLogs) {
		this.baseLogs = baseLogs;
	}
	
	
}
