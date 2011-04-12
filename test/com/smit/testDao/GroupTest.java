package com.smit.testDao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.Group;
import com.smit.vo.User;


public class GroupTest extends TestCase {
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
		User user = new User();
		user.setUserName(new String("�����"));
		user.setExplain("KDKKDKD");
		Group group = new Group();
		group.setGroupName("edior");
		group.setSortRank(100);
        group.setCreatetime(new Timestamp(new Date().getTime()));
       // Set<User> users = new HashSet<User>();
       // users.add(user);
        //group.setUsers(users);
        user.setGroup(group);
		session.save(user);
		session.beginTransaction().commit();
	}
	

}
