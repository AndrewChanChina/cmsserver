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
	private Integer partId; 


	/*
	private Set<Part> children = new HashSet<Part>();
	private Set<Part> bottoms = new HashSet<Part>();
	*/
	private Set<Content> contents = new HashSet<Content>();


	public Integer getPartId() {
		return partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}

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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 

}
