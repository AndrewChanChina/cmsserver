package com.smit.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomSessionFactory {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal threadLocal = new ThreadLocal();
	private static final Configuration cfg = new Configuration();
	private static SessionFactory sessionFactory = null;
	
	public static Session currentSession() throws HibernateException
	{
		closeSession();
		Session session = (Session)threadLocal.get();
		if(null == session)
		{
			if(sessionFactory == null)
			{
				try
				{
					cfg.configure(CONFIG_FILE_LOCATION);
					sessionFactory = cfg.buildSessionFactory();
				}
				catch(Exception e)
				{
					System.out.println("===============================");
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
			}
			if(sessionFactory != null)
			{
				session = sessionFactory.openSession();
			}
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void closeSession() throws HibernateException
	{
		Session session = (Session)threadLocal.get();
		threadLocal.set(null);
		if(session != null)
		{
			session.close();
		}
		//sessionFactory = null;
	}
	
	private CustomSessionFactory()
	{
		
	}
}
