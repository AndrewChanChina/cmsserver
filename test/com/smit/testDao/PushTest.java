package com.smit.testDao;

import java.sql.Timestamp;
import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.PushService;

public class PushTest extends TestCase {

	private Session session;
	@Override
	protected void setUp() throws Exception {
		try {
			Configuration config = new Configuration().configure();
			SessionFactory factory = config.buildSessionFactory();
			session = factory.openSession();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		
		super.setUp();
	}
	
	public void testSave(){
		PushService ps = new PushService();
		ps.setCreatetime(new Timestamp(new Date().getTime()));
		ps.setFlag("no");
		ps.setServiceName("test1");
		ps.setServiceId("123456789");
		ps.setUserId(123456);		
		session.save(ps);		
	}
}
