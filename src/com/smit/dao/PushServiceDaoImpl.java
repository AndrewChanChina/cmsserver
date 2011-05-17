package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.Purview;
import com.smit.vo.PushService;
import com.smit.vo.UserAccountResource;

public class PushServiceDaoImpl extends HibernateDaoSupport implements IPushServiceDao {

	@Override
	public void save(PushService ps) {
		this.getHibernateTemplate().save(ps);
	}

	@Override
	public void update(PushService ps) {
		this.getHibernateTemplate().update(ps);
	}

	@Override
	public void delete(PushService ps) {
		this.getHibernateTemplate().delete(ps);
	}

	@Override
	public PushService getById(Integer id) {
		return this.getHibernateTemplate().get(PushService.class, id);
	}

	@Override
	public List listAll(final SmitPage page) {
		if(page == null)
			return listAll();
		
		List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.PushService");
		 page.setTotalCount(Integer.parseInt(count.get(0).toString() ));		 
		 
		 List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
	            
			 public Object doInHibernate(Session s) throws HibernateException,  
	                    SQLException {  
	            	String hql = "select u FROM com.smit.vo.PushService u";
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
	
	private List listAll(){
		return this.getHibernateTemplate().find("SELECT u FROM com.smit.vo.PushService u");
	}

	@Override
	public void saveOrUpdate(UserAccountResource us) {
		this.getHibernateTemplate().saveOrUpdate(us);		
	}

	@Override
	public void delete(UserAccountResource us) {
		this.getHibernateTemplate().delete(us);
	}

	@Override
	public List<UserAccountResource> listAllResource(String userName) {
		return (List<UserAccountResource>)this.getHibernateTemplate()
		.find("SELECT u FROM com.smit.vo.UserAccountResource u WHERE userAccount='"
				+userName + "'");
	}

	@Override
	public void deleteByAccount(String account) {
		List list = getHibernateTemplate()
		.find("SELECT u FROM com.smit.vo.UserAccountResource u WHERE userAccount = '" + account + "'");
		if(list.size() > 0){
			getHibernateTemplate().deleteAll(list);
		}
		
			
	}


}
