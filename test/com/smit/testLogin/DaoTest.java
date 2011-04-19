package com.smit.testLogin;

import java.util.HashSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.util.HibernateUtil;
import com.smit.vo.Group;
import com.smit.vo.User;

import junit.framework.TestCase;

public class DaoTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
			
		super.setUp();
	}
	
	public void testHello(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = new User();
		user.setUserName("username6");
		user.setPassword("psw6");
		user.setTel("13652");
		
		//Group group = new Group();
		//group.setGroupName("newGroup5");
		//user.setGroup(group);
		//group.getUsers().add(user);
		//Group group = (Group)session.get(Group.class, 5);
		//user.setGroup(group);
		//HashSet<User> users = new HashSet();
		//users.add(user);
		//group.setUsers(users);
		
		//session.save(group);
		session.save(user);
	
		session.getTransaction().commit();
	}
	
	public void testGroup(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Group group = new Group();
		group.setGroupName("group3");
		session.save(group);
		
		session.getTransaction().commit();
	}
	
	public void testDelete(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Group group = (Group)session.get(Group.class, 5);
		
		session.delete(group);
		
		session.getTransaction().commit();
	}
}
