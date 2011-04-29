package com.smit.testDao;
import java.sql.Timestamp;
import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.Content;


public class ContentTest extends TestCase {
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
	
	public void testAdd(){
		Content content = new Content();
		content.setTitle("kdkdk");

		content.setContent("kdkdkdkd");
		session.save(content);
		session.beginTransaction().commit();
	}
	

}
