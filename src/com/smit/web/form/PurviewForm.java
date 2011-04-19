package com.smit.web.form;

import org.apache.struts.validator.ValidatorForm;



public class PurviewForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8461872340058385147L;
	private String id;
	private String purviewName;
	private String purviewInfo;
	private String hideId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPurviewName() {
		return purviewName;
	}
	public void setPurviewName(String purviewName) {
		this.purviewName = purviewName;
	}
	public String getPurviewInfo() {
		return purviewInfo;
	}
	public void setPurviewInfo(String purviewInfo) {
		this.purviewInfo = purviewInfo;
	}
	public String getHideId() {
		return hideId;
	}
	public void setHideId(String hideId) {
		this.hideId = hideId;
	}
	
	
}
