package com.smit.testDao;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.Video;

public class VideoTest extends TestCase {
	
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
		Video v = new Video();
		v.setTitle("important news");
		v.setAuthor("andrew");
		v.setEnclosure_url("www.baidu.com");
		session.beginTransaction().begin();
		session.save(v);
		session.beginTransaction().commit();
	}
	
	public void testGet(){
		Integer i = 52;
		Video v = (Video)session.get(Video.class, 1);
		System.out.println(v.getAuthor());
	}
}
