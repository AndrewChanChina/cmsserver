package com.smit.service;

import java.util.List;

import com.smit.dao.IGroupDao;
import com.smit.util.SmitPage;
import com.smit.vo.Group;

public class GroupMangerServiceImpl implements IGroupManagerService {

	IGroupDao groupDao;
	public Group findGroupByName(String groupName) {

		return groupDao.findGroupByName(groupName);
	}

	public void save(Group group) {
		groupDao.save(group);

	}

	public void update(Group group) {
		groupDao.update(group);

	}

	public void delete(Group group) {
		groupDao.delete(group);

	}

	public Group getGroup(Integer id) {
		return groupDao.getGroup(id);
		
	}

	public List listAllGroups(SmitPage page) {
		return groupDao.listAllGroups(page);
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	

}
