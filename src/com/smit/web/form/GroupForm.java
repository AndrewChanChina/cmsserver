package com.smit.web.form;

import org.apache.struts.action.ActionForm;

public class GroupForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4346221320250267329L;
	private String groupName;
	private String id;
	private String hideId;
	private String[] purviewids;
	
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
	public String[] getPurviewids() {
		return purviewids;
	}
	public void setPurviewids(String[] purviewids) {
		this.purviewids = purviewids;		
	}
	
}
