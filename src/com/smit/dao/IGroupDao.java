package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.Group;

public interface IGroupDao {
	public Group findGroupByName(String groupName);
	
	public void save(Group group);
	public void update(Group group);
	public void delete(Group group);
	public Group getGroup(Integer id);
	// for pagination
	// if page == null return all groups
	public List listAllGroups(SmitPage page);
}
