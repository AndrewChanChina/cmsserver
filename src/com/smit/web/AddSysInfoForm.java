package com.smit.web;

import org.apache.struts.action.ActionForm;

public class AddSysInfoForm extends ActionForm{
	
	private String sysInfoKey;
	private String sysInfoValue;
	
	public String getSysInfoKey() {
		return sysInfoKey;
	}
	
	public void setSysInfoKey(String sysInfoKey) {
		this.sysInfoKey = sysInfoKey;
	}
	
	public String getSysInfoValue() {
		return sysInfoValue;
	}
	
	public void setSysInfoValue(String sysInfoValue) {
		this.sysInfoValue = sysInfoValue;
	}
}
