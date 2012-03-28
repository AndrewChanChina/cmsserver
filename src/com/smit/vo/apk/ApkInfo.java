package com.smit.vo.apk;

import java.io.Serializable;

import com.smit.web.clock.Alarm;
import com.smit.web.clock.Alarm.DaysOfWeek;

public class ApkInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String status;
	private String roomnum;
	private String iconUrl;
	
	// 下面是客户端的定义的
	public static final String APP_NAME = "appname";
	public static final String APP_PACKAGE_NAME = "packagename";
	public static final String APP_OPERATION = "operation";
	public static final String APP_APK_URL = "apkurl";
	
	private String appName; 
	private String packageName;
	private String operation;
	private String apkUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	
	
}

