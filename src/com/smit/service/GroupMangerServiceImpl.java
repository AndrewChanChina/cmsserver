package com.smit.service;

import java.util.List;

import com.smit.dao.IGroupDao;
import com.smit.util.SmitPage;
import com.smit.vo.Group;

public class GroupMangerServiceImpl implements IGroupManagerService {

	IGroupDao groupDao;
	@Override
	public Group findGroupByName(String groupName) {

		return groupDao.findGroupByName(groupName);
	}

	@Override
	public void save(Group group) {
		groupDao.save(group);

	}

	@Override
	public void update(Group group) {
		groupDao.update(group);

	}

	@Override
	public void delete(Group group) {
		groupDao.delete(group);

	}

	@Override
	public Group getGroup(Integer id) {
		return groupDao.getGroup(id);
		
	}

	@Override
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
