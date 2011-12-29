package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.DaoException;
import com.smit.util.Page;
import com.smit.vo.Content;
import com.smit.vo.Media;

public class ContentDaoImpl extends HibernateDaoSupport implements ContentDao {
  
	public void save(Content content) {
		try {
			this.getHibernateTemplate().save(content);
		
		}catch(HibernateException e){
			throw new DaoException("内容保存操作" + e.getMessage());
		}

	}
	public void saveorupdate(Content content) {
		try {
			this.getHibernateTemplate().saveOrUpdate(content);
		
		}catch(HibernateException e){
			throw new DaoException("内容保存操作" + e.getMessage());
		}

	}

	public void update(Content content) {
		try {
			this.getHibernateTemplate().update(content);
		}catch(HibernateException e){
			throw new DaoException("内容更新操作" + e.getMessage());
		}

	}

	public void delete(Content content) {
		try {
			this.getHibernateTemplate().delete(content);
		}catch(HibernateException e){
			throw new DaoException("内容删除操作" + e.getMessage());
		}

	}

	public Page getPage(final int currentPage,final int pageSize,int pid) {
		Page page = new Page();
		String sql_count = "select count(*) from com.smit.vo.Content";
	    String hql = "from com.smit.vo.Content c order by c.sortRank";
		if(pid >0){
			sql_count += " where pid =" + pid;
			hql += " where c.Pid=" + pid;
		}
		final String hql_list = hql;
		try {
			List list = this.getHibernateTemplate().find(sql_count);
			Integer recordCount = Integer.parseInt(String.valueOf(list.get(0)));
			
			List contents = this.getHibernateTemplate().execute(new HibernateCallback(){
	
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					
					return session.createQuery(hql_list)
					           .setFirstResult((currentPage-1)*pageSize)
					           .setMaxResults(pageSize).list();
				}
				
			});
			page.setTotalRecord(recordCount);
			page.setCurrentPage(currentPage);
			page.setPageSize(pageSize);
			
			page.setList(contents);
		
		}catch(HibernateException e){
			throw new DaoException("内容查找全部操作" + e.getMessage());
			
		}
		return page;
	}
	
	
	public Page getPage(final int currentPage,final int pageSize) {
		Page page = new Page();
		
		try {
			
			List list = this.getHibernateTemplate().find("select count(*) from com.smit.vo.Content");
			Integer recordCount = Integer.parseInt(String.valueOf(list.get(0)));
			List contents = this.getHibernateTemplate().execute(new HibernateCallback(){
	
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					
					return session.createQuery("from com.smit.vo.Content")
					           .setFirstResult((currentPage-1)*pageSize)
					           .setMaxResults(pageSize).list();
				}
				
			});
			page.setTotalRecord(recordCount);
			page.setCurrentPage(currentPage);
			page.setPageSize(pageSize);
			page.setList(contents);
		
		}catch(HibernateException e){
			throw new DaoException("内容查找全部操作" + e.getMessage());
			
		}
		return page;
	}

	public Content getById(Integer id) {
		Content content = null;
		try {
			content = (Content)this.getHibernateTemplate().get(Content.class, id);
			
		}catch(HibernateException e){
			throw new DaoException("内容根据ID查找操作??" + e.getMessage());
		}
		return content;
	}



}
