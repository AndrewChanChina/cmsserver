package com.smit.web.webService;

import org.apache.struts.action.ActionForm;

public class VideoForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String method;
	private String machineVirtualId;
	private String apiKey;
	private String type;
	private String start;
	private String count;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMachineVirtualId() {
		return machineVirtualId;
	}
	public void setMachineVirtualId(String machineVirtualId) {
		this.machineVirtualId = machineVirtualId;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	

}
