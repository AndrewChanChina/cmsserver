package com.smit.vo.alarmclock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupRoom implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_ADMIN = "admin";
	public static final String TYPE_USER = "user";
	
	// 中间表
	
	private int id;
	
	private Integer groupId;
	private Integer roomNumId;
	private String groupName;
	private String roomNum;
	
	// temp 
	private List<String> listRoomNums = new ArrayList<String>();
	
	private String status;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getRoomNumId() {
		return roomNumId;
	}
	public void setRoomNumId(Integer roomNumId) {
		this.roomNumId = roomNumId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getListRoomNums() {
		return listRoomNums;
	}
	public void setListRoomNums(List<String> listRoomNums) {
		this.listRoomNums = listRoomNums;
	}
}

