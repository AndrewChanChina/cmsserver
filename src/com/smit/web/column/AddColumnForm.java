package com.smit.web.column;

import org.apache.struts.action.ActionForm;

public class AddColumnForm extends ActionForm{
	
	private String parentID;
	private String classToAdd;
	private String path;
	
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getClassToAdd() {
		return classToAdd;
	}
	public void setClassToAdd(String classToAdd) {
		this.classToAdd = classToAdd;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
