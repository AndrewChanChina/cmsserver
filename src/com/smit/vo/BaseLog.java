package com.smit.vo;

import java.io.Serializable;

public class BaseLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String machineID;
	private String machineType;
	private String sysVersion;
	private String softwareVersion;
	private String testStatus;
	private String logFile;//upload file path
	private String create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getSysVersion() {
		return sysVersion;
	}
	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
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
	
}
