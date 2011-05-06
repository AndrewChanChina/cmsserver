package com.smit.web.control.form;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class LogForm extends ActionForm{

	/**
	 * 
	 */
	
	
	//base log attr
	private String machineID;
	private String machineType;
	private String systemVersion;
	private String softwareVersion;
	private String testStatus;
	
	//detail log attr
	//private String deviceType;
	//private String note;
	private FormFile upload;
	
	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
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

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
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

//	public String getDeviceType() {
//		return deviceType;
//	}

//	public void setDeviceType(String deviceType) {
//		this.deviceType = deviceType;
//	}
//
//	public String getNote() {
//		return note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}

//	public List<FormFile> getUpload() {
//		return upload;
//	}
//
//	public void setUpload(List<FormFile> upload) {
//		this.upload = upload;
//	}
//

	/**
	public FormFile getUploadFile(int index){
		int size = uploadFiles.size();
		if(index>size-1){
			for(int i=0;i<index-size+1;i++){
				uploadFiles.add(new FormFile());
			}
		}
		return uploadFiles.get(index);
	}
**/
}
