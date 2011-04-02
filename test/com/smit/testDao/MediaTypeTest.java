package com.smit.testDao;
import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.MediaType;


public class MediaTypeTest extends TestCase {
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
		MediaType type = new MediaType();
		type.setTypeName("kdkdkdk");
		type.setIsspecial(0);
		session.save(type);
		session.beginTransaction().commit();
	}
	

}
