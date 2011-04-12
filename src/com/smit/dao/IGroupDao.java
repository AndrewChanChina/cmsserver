package com.smit.dao;

import org.hibernate.Session;

import com.smit.vo.Group;

public interface IGroupDao {
	public Group findGroupByName(String groupName);
	public Group findGroupByID(String groupId);
}
