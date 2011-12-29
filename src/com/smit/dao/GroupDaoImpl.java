package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.Page;
import com.smit.util.SmitPage;
import com.smit.vo.Group;

public class GroupDaoImpl extends HibernateDaoSupport implements IGroupDao {

	public Group findGroupByName(String groupName) {
		String hql = "from com.smit.vo.Group s where s.groupName='" + groupName + "'";		
		List list = this.getHibernateTemplate().find(hql);
		
		if(list.size() < 1){
			return null;
		}
		return (Group)list.get(0);
	}
	
	List<Group> findAllGroups(){
		String hql = "from com.smit.vo.Group s";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public void save(Group group) {
		this.getHibernateTemplate().save(group);
		
	}

	public void update(Group group) {
		this.getHibernateTemplate().update(group);
		
	}

	public void delete(Group group) {
		this.getHibernateTemplate().delete(group);
		
	}

	public Group getGroup(Integer id) {
		return this.getHibernateTemplate().get(Group.class, id);
	}

	public List listAllGroups(final SmitPage page) {
		
		if(page == null)
			return listAllGroups();
		
		 List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.Group");
		 page.setTotalCount(Integer.parseInt(count.get(0).toString() ));
		 
		 
		 List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
	            public Object doInHibernate(Session s) throws HibernateException,  
	                    SQLException {  
	            	String hql = "select g FROM com.smit.vo.Group g";
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
	
	private List listAllGroups(){
		return this.getHibernateTemplate().find("SELECT g FROM com.smit.vo.Group g");
	}

}
