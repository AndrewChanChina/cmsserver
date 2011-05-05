package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.Group;
import com.smit.vo.Video;

public class VideoDaoImpl extends HibernateDaoSupport implements VideoDao {

	@Override
	public void save(Video video) {
		this.getHibernateTemplate().save(video);		
	}

	@Override
	public void update(Video video) {
		this.getHibernateTemplate().update(video);		
	}

	@Override
	public void delete(Video video) {
		this.getHibernateTemplate().delete(video);		
	}	

	@Override
	public List<Video> listAll(final SmitPage page) {
		if( page == null)
			return listAll();
		
		List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.Video");
		page.setTotalCount(Integer.parseInt(count.get(0).toString() ));
		 
		 
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
	            public Object doInHibernate(Session s) throws HibernateException,  
	                    SQLException {  
	            	String hql = "select g FROM com.smit.vo.Video g";
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

	@Override
	public List<Video> findByPartId(final SmitPage page, final Integer partId) {
		if( page == null)
			return listAll();
		
		List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.Video where partId=" + partId);
		page.setTotalCount(Integer.parseInt(count.get(0).toString() ));
		 
		 
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
	            public Object doInHibernate(Session s) throws HibernateException,  
	                    SQLException {  
	            	String hql = "select g FROM com.smit.vo.Video g where partId=" + partId;
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

	@Override
	public Video getById(Integer id) {
		return this.getHibernateTemplate().get(Video.class,	id);
	}
	
	private List listAll(){
		return this.getHibernateTemplate().find("SELECT g FROM com.smit.vo.Video g");
	}

	
}
