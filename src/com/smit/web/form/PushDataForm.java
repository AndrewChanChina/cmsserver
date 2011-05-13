package com.smit.web.form;

import org.apache.struts.validator.ValidatorForm;

public class PushDataForm extends ValidatorForm {
	
	private String servicetype;
	private String serviceName;
	private String collapseKey;
	private String isDelay;
	private String title;
	private String ticket;
	private String uri;
	private String message;
	private String[] deviceIds;
	
	
	
	public String[] getDeviceIds() {
		return deviceIds;
	}
	public void setDeviceIds(String[] deviceIds) {
		this.deviceIds = deviceIds;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getCollapseKey() {
		return collapseKey;
	}
	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}
	public String getIsDelay() {
		return isDelay;
	}
	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
