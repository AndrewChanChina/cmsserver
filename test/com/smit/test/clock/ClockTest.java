package com.smit.test.clock;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.Video;
import com.smit.vo.alarmclock.Clock;

public class ClockTest extends TestCase {
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
	
		Clock c = new Clock();
		c.setDayofweek(64);
		c.setHour(22);
		
		session.beginTransaction().begin();
		session.save(c);
		session.beginTransaction().commit();
	}
	public void testGet(){
		Integer i = 52;
		Clock c = (Clock)session.get(Clock.class, 1);
		System.out.println(c.getHour());
	}

}
