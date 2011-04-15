package com.smit.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.Page;
import com.smit.util.SmitPage;
import com.smit.vo.Group;
import com.smit.vo.User;

public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {

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
	public User findGroupByName(String userName) {
		String hql = "from com.smit.vo.User s where s.userName='" + userName + "'";		
		List list = this.getHibernateTemplate().find(hql);
		
		if(list.size() < 1){
			return null;
		}
		return (User)list.get(0);
	}
	@Override
	public void save(User user) {

		String hql = "from com.smit.vo.Group s where s.groupName='normal'";
		
		List list = this.getHibernateTemplate().find(hql);			
		if(list.size() < 1){
		}
		Group group = (Group)list.get(0);
		user.setGroup(group);
		HashSet<User> users = new HashSet();
		users.add(user);
		
		group.setUsers(users);
		this.getHibernateTemplate().save(user);			
		this.getHibernateTemplate().flush();
		
	}
	@Override
	public void update(User user) {
		this.getHibernateTemplate().update(user);
		
	}
	@Override
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}
	@Override
	public User getUser(Integer id) {
		return this.getHibernateTemplate().get(User.class, id);
	}
	
	@Override
	public List listAllUsers(final SmitPage page) {
		if(page == null)
			return listAllUsers();
		
		List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.User");
		 page.setTotalCount(Integer.parseInt(count.get(0).toString() ));		 
		 
		 List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
	            public Object doInHibernate(Session s) throws HibernateException,  
	                    SQLException {  
	            	String hql = "select u FROM com.smit.vo.User u";
	                Query query = s.createQuery(hql);  
	                int firstRow = page.getPageSize() * (page.getPageIndex() - 1);
	                query.setFirstResult(firstRow);  
	                query.setMaxResults(page.getPageSize());  
	                List list = query.list();  
	                return list;  
	            }  
	        });  
		return list;
	}
	
	private List listAllUsers(){
		return this.getHibernateTemplate().find("SELECT u FROM com.smit.vo.User u");
	}

}
