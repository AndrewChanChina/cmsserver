package com.smit.web.form;

import org.apache.struts.action.ActionForm;

public class GroupForm extends ActionForm {
	private String groupName;
	private String id;
	private String hideId;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHideId() {
		return hideId;
	}
	public void setHideId(String hideId) {
		this.hideId = hideId;
	}	
}
