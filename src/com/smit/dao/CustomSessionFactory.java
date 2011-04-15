package com.smit.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomSessionFactory {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	//private static ThreadLocal threadLocal = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null; 
	
	private static void initialize()
	{
		//if(null == threadLocal)
		//{
			//threadLocal = new ThreadLocal();
		//}
		if(null == cfg)
		{
			cfg = new Configuration();
		}
	}
	public static Session currentSession() throws Exception
	{
		initialize();
		//Session session = (Session)threadLocal.get();
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
					throw e;
				}
			}
			if(sessionFactory != null)
			{
				session = sessionFactory.openSession();
			}
			//threadLocal.set(session);
		}
		return session;
	}
	
	public static void closeSession() throws HibernateException
	{
		//Session session = null;
		//if(null != threadLocal) 
		//{
			//session = (Session)threadLocal.get();
			//threadLocal.set(null);
		//}
		if(session != null)
		{
			session.close();
			session = null;
		}
		if(sessionFactory != null)
		{
			sessionFactory.close();
			//sessionFactory = null;
		}
	}
	
	private CustomSessionFactory()
	{
		
	}

}
