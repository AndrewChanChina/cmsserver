package com.smit.web.column;

import org.apache.struts.action.ActionForm;

public class AddColumnForm extends ActionForm{
	
	private String topID;
	
	private String parentID;
	private String typeName;
	//private String path;
	
	
	public void setTopID(String topID) {
		this.topID = topID;
	}
	
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTopID() {
		return topID;
	}
	/*
	public String getClassToAdd() {
		return classToAdd;
	}
	public void setClassToAdd(String classToAdd) {
		this.classToAdd = classToAdd;
	}
	*/
	/*
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	*/
}
