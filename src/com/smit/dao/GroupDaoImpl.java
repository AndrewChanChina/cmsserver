package com.smit.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.Group;

public class GroupDaoImpl extends HibernateDaoSupport implements IGroupDao {

	@Override
	public Group findGroupByName(String groupName) {
		String hql = "from com.smit.vo.Group s where s.groupName='" + groupName + "'";
		
		List list = this.getHibernateTemplate().find(hql);
	
		
		if(list.size() < 1){
			return null;
		}
		return (Group)list.get(0);
	}
	
	@Override
	public Group findGroupByID(String groupId) {	
		String hql = "from com.smit.vo.Group s where id='" + groupId + "'";
		Group group = this.getHibernateTemplate().load(Group.class, Integer.parseInt(groupId));	
		return group;
	}
	
	List<Group> findAllGroups(){
		String hql = "from com.smit.vo.Group s";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
