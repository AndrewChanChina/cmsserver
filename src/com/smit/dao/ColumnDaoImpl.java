package com.smit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		
		Part part = new Part();
		part.setFather_id(father_ID);
		part.setId(null);
		part.setTopid(father.getTopid());
		part.setTypename(classToAdd);
		
		return true;
	}
	
	public List<Part> queryAllColumns()
	{
		String hql = "from com.smit.vo.Part";
		Session session = CustomSessionFactory.currentSession();
		if(session == null)
		{
			System.out.println("ERROR in List<Part> queryAllColumns(): session = null");
		}
		Transaction ts = null;
		List<Part> allColumns = new ArrayList<Part>();
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
}
