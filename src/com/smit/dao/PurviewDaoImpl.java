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
import com.smit.vo.User;

public class PurviewDaoImpl extends HibernateDaoSupport implements IPurviewDao {

	public Purview findByName(String name) {
		String hql = "from com.smit.vo.Purview s where s.purviewName='" + name + "'";		
		List list = this.getHibernateTemplate().find(hql);
		
		if(list.size() < 1){
			return null;
		}
		return (Purview)list.get(0);
	}

	public void save(Purview purview) {
		this.getHibernateTemplate().save(purview);
	}

	public void update(Purview purview) {
		this.getHibernateTemplate().update(purview);
	}

	public void delete(Purview purview) {
		this.getHibernateTemplate().delete(purview);
	}

	public Purview getPurview(Integer id) {
		return this.getHibernateTemplate().get(Purview.class, id);
	}

	public List listAll(final SmitPage page) {
		if(page == null)
			return listAll();
		
		List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.Purview");
		 page.setTotalCount(Integer.parseInt(count.get(0).toString() ));		 
		 
		 List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
	            
			 public Object doInHibernate(Session s) throws HibernateException,  
	                    SQLException {  
	            	String hql = "select u FROM com.smit.vo.Purview u";
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
		return this.getHibernateTemplate().find("SELECT u FROM com.smit.vo.Purview u");
	}

}
