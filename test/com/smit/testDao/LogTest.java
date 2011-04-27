package com.smit.testDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.BaseLog;

import junit.framework.TestCase;

public class LogTest extends TestCase{
	private Session session;

	@Override
	protected void setUp() throws Exception {
		try{
			Configuration cfg = new Configuration().configure();
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
		}catch (Exception e) {
			e.printStackTrace();
		}
		super.setUp();
	}
	
	public void testInsert(){
		BaseLog baseLog = new BaseLog();
		baseLog.setMachineID("24455");
		baseLog.setMachineType("8230");
		session.saveOrUpdate(baseLog);
		session.beginTransaction().commit();
	}
}
