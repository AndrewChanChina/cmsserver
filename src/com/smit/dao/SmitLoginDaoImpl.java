package com.smit.dao;

import java.util.HashSet;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.Group;
import com.smit.vo.User;

/**
 * deal with smit login access database 
 * @author chenyzpower@gmail.com
 *
 */
public class SmitLoginDaoImpl extends HibernateDaoSupport implements ISmitLoginDao {

	@Override
	public boolean isAdmin(String userName, String password) {
		String hql = "from com.smit.vo.User s where userName='" + userName + "' and passwd='" + password + "'";
		List list = this.getHibernateTemplate().find(hql);
		
		System.out.println(list.size());
		if(list.size() > 0 ){
			return true;
		}else 
		return false;
	}
	/**
	 *   save a user to 'normal' group.
	 *   So please make sure that is a normal group name in smit_group
	 */
	@Override
	public boolean register(User user){
		try{	
			
			String hql = "from com.smit.vo.Group s where s.groupName='normal'";
			
			List list = this.getHibernateTemplate().find(hql);			
			if(list.size() < 1){
				return false;
			}
			Group group = (Group)list.get(0);
			user.setGroup(group);
			HashSet<User> users = new HashSet();
			users.add(user);
			
			group.setUsers(users);
			this.getHibernateTemplate().save(user);			
			this.getHibernateTemplate().flush();
		}catch(DataAccessException e){
			return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateUser(User user){
		try{
			this.getHibernateTemplate().update(user);
			this.getHibernateTemplate().flush();
		}catch(DataAccessException e){
			return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}

}
