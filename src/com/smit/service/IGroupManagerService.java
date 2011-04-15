package com.smit.service;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.Group;

public interface IGroupManagerService {
	
	public Group findGroupByName(String groupName);
	public void save(Group group);
	public void update(Group group);
	public void delete(Group group);
	public Group getGroup(Integer id);
	// for pagination
	public List listAllGroups(SmitPage page);

}
