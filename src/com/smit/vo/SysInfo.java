package com.smit.vo;

import java.io.Serializable;

public class SysInfo implements Serializable{
	
	private Integer id;
	private String infoKey;
	private String infoValue;
	
	public String getInfoKey() {
		return infoKey;
	}
	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}
	public String getInfoValue() {
		return infoValue;
	}
	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void resetInfoKey(String key)
	{
		infoKey = key;
	}
	
	public void resetInfoValue(String value)
	{
		infoValue = value;
	}
}
