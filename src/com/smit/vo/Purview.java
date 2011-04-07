package com.smit.vo;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

public class Purview implements Serializable {

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class Ȩ����
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String purviewName;
	private String purviewInfo;
	private Set<Group> groups = new HashSet<Group>();
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
