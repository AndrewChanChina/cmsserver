package com.smit.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class Group 用户组类
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String groupName;
	private Set<User> users = new HashSet<User>();
	private Set<Purview> purviews = new HashSet<Purview>();
	private Timestamp createtime;
	private Integer sortRank;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	
	
	public Set<Purview> getPurviews() {
		return purviews;
	}
	public void setPurviews(Set<Purview> purviews) {
		this.purviews = purviews;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<User> getUsers() {
		return users;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getSortRank() {
		return sortRank;
	}
	public void setSortRank(Integer sortRank) {
		this.sortRank = sortRank;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
