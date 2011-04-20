package com.smit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.Part;
import com.smit.vo.SysInfo;

public class ColumnDaoImpl extends HibernateDaoSupport implements ColumnDao
{
	
	private SessionFactory sessionFactory;
	
	public void SessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public boolean addColumn(String parentID, String classToAdd, String path)
	{

		HibernateTemplate ht = this.getHibernateTemplate();
		
		Integer father_ID = Integer.valueOf(parentID);
		Part father = ht.get(Part.class, father_ID);
		
		Integer topIdToSet = father.getTopid();
		if(topIdToSet==0)
		{
			topIdToSet = father.getId();
		}
		
		Part part = new Part();
		part.setFather_id(father_ID);
		part.setId(null);
		part.setTopid(topIdToSet);
		part.setTypename(classToAdd);
		part.setPath(path);
		
		ht.saveOrUpdate(part);
		
		return true;
	}
	
	public List<Part> queryAllColumns() {
		//if(page == null)
			//return listAllUsers();
		
		//List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.User");
		 //page.setTotalCount(Integer.parseInt(count.get(0).toString() ));		 
		 
		 return getHibernateTemplate().executeFind(new HibernateCallback() {  
	            public Object doInHibernate(Session s) throws HibernateException, SQLException
	            {  
	            	String hql = "FROM com.smit.vo.Part p";
	                Query query = s.createQuery(hql);  
	                //int firstRow = page.getPageSize() * (page.getPageIndex() - 1);
	                //query.setFirstResult(firstRow);  
	                //query.setMaxResults(page.getPageSize());  
	                List<Part> list = query.list();  
	                return list;  
	            }
	        });  
	}

	
	/*
	public List<Part> queryAllColumns() throws Exception
	{
		
		List<Part> allColumns = new ArrayList<Part>();
		String hql = "from com.smit.vo.Part";
		Session session;
		try {
			session = CustomSessionFactory.currentSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		if(session == null)
		{
			System.out.println("ERROR in List<Part> queryAllColumns(): session = null");
			return allColumns;
		}
		Transaction ts = null;
		
		Query query = null; 
		try
		{
			query = session.createQuery(hql);
			ts = session.beginTransaction();
			allColumns = query.list();
			ts.commit();
		}
		catch(Exception e)
		{
			if(ts != null)
			{
				ts.rollback();
			}
			
		}
		finally
		{
			session.flush();
			session.clear();
		}
		return allColumns;
	}
	*/
	
	public boolean deleteColumn(final String id)
	{
		Integer intId = Integer.valueOf(id,10);
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		Part column = ht.get(Part.class, intId);
		if(column == null)
		{
			return false;
		}
		ht.delete(column);
		return true;
	}
	public boolean deleteColumn(final ArrayList<String> idList)
	{
		for(int i=0; i<idList.size(); i++)
		{
			deleteColumn(idList.get(i));
		}
		return true;
	}
	public boolean updateColumn(final ArrayList<Part> columnList)
	{
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		for(int i=0; i<columnList.size(); i++)
		{
			ht.saveOrUpdate(columnList.get(i));
		}
		return true;
	}
	
	public Part queryByColumnId(final Integer id) throws Exception
	{
		//Session session;
		//try {
			//session = CustomSessionFactory.currentSession();
		//} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//throw e;
		//}
		//if(session == null)
		//{
			//return null;
		//}
		//Part part = (Part)session.get(Part.class, id);
		//return part;
		
		
		List<Part> partsList = getHibernateTemplate().executeFind(new HibernateCallback() {  
            public Object doInHibernate(Session s) throws HibernateException, SQLException
            {  
            	String hql = "FROM com.smit.vo.Part p where id=" + id;
                Query query = s.createQuery(hql);  
                //int firstRow = page.getPageSize() * (page.getPageIndex() - 1);
                //query.setFirstResult(firstRow);  
                //query.setMaxResults(page.getPageSize());  
                List<Part> list = query.list();
               
                return list;
            }
        });
		Part part = new Part();
        if(!partsList.isEmpty() && partsList.size()>0)
        {
        	part = partsList.get(0);
        }
         return part;
	}
}
