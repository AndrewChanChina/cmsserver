package com.smit.testDao;
import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.Media;


public class MediaTest extends TestCase {
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
		Media media = new Media();
		media.setPath("ldldl");
		media.setFileName("ldldlld");
		session.save(media);
		session.beginTransaction().commit();
	}
	

}
