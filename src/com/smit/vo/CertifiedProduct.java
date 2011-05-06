package com.smit.vo;

import java.io.Serializable;

public class CertifiedProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String old_check_id;
	private String new_check_id;
	private String status;
	private String create_time;
	private Device device;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOld_check_id() {
		return old_check_id;
	}
	public void setOld_check_id(String old_check_id) {
		this.old_check_id = old_check_id;
	}
	public String getNew_check_id() {
		return new_check_id;
	}
	public void setNew_check_id(String new_check_id) {
		this.new_check_id = new_check_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
}
