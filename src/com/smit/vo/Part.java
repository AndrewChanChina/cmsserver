package com.smit.vo;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;


public class Part implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class ��Ŀ��
	 * @params topid:������ĿID,father_id:�ϼ���ĿID��typename:��Ŀ���
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String typename;
	private Integer topid;
	private Integer father_id;
	//private String path;
	
	//private Part parent;
	//private Part top;

	/*
	private Set<Part> children = new HashSet<Part>();
	private Set<Part> bottoms = new HashSet<Part>();
	*/
	private Set<Content> contents = new HashSet<Content>();

	/*
	public Part getTop() {
		return top;
	}
	public void setTop(Part top) {
		this.top = top;
	}
	*/
	
	/*
	public Part getParent() {
		return parent;
	}
	public void setParent(Part parent) {
		this.parent = parent;
	}
	*/
	
	/*
	public Set<Part> getChildren() {
		return children;
	}
	public void setChildren(Set<Part> children) {
		this.children = children;
	}
	
	public void addChild(Part child)
	{
		children.add(child);
	}
	
	public Set<Part> getBottoms() {
		return bottoms;
	}
	public void setBottoms(Set<Part> bottoms) {
		this.bottoms = bottoms;
	}
	public void addBottom(Part bottom)
	{
		bottoms.add(bottom);
	}
	*/
	public Set<Content> getContents() {
		return contents;
	}
	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public Integer getTopid() {
		return topid;
	}
	public void setTopid(Integer topid) {
		this.topid = topid;
	}
	
	public Integer getFather_id() {
		return father_id;
	}
	public void setFather_id(Integer father_id) {
		this.father_id = father_id;
	}
	
	/*
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	*/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 

}
