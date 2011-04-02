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
import com.smit.vo.Purview;

public class PurviewTest extends TestCase {
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

	public void testAddPurview() {
		
		try {
			Purview purview = new Purview();
			purview.setPurviewName("yffff");
			purview.setPurviewInfo("gh");
			Group group = new Group();
			group.setGroupName("kgfsdfg");
			group.setSortRank(100);
	        group.setCreatetime(new Timestamp(new Date().getTime()));
	        Set<Group> groups = new HashSet();
	        
	       purview.setGroups(groups);
			session.save(purview);
			session.beginTransaction().commit();
			session.close();
		}catch(HibernateException e){
			e.printStackTrace();
			
		}
	
		
	
	}

}
