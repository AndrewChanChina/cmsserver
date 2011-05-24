package com.smit.vo;

import java.io.Serializable;

public class DetailLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String testStatus;
	private String note;
	private String create_time;
	private TestOption testOption;//1对多
	private Device device;//1对多
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public TestOption getTestOption() {
		return testOption;
	}
	public void setTestOption(TestOption testOption) {
		this.testOption = testOption;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
	
}
