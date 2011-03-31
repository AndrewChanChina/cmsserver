package com.smit.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Group implements Serializable {

	/**
	 * @author ligm
	 * @date 2011-3-31
	 * @class Group 用户组类
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String groupName;
	private Integer uid;
	private Integer purview_id;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getPurview_id() {
		return purview_id;
	}
	public void setPurview_id(Integer purview_id) {
		this.purview_id = purview_id;
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
