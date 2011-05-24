package com.smit.web.form;

import org.apache.struts.action.ActionForm;

public class PushServiceForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -794673755238123515L;
	private Integer id = null;
	private String serviceName = null;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	
}
