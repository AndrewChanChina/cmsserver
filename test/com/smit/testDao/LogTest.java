package com.smit.testDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;
import com.smit.vo.TestOption;

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
	
//	public void testInsert(){
//		DetailLog log = new DetailLog();
//		TestOption op = new TestOption();
//		op.setId(1);
//		log.setTestOption(op);
//		session.saveOrUpdate(log);
//		session.beginTransaction().commit();
//	}
}
