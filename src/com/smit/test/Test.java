package com.smit.test;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.User;

public class Test extends HibernateDaoSupport {
	public static void main(String[] args){
//		Configuration cfg = new Configuration().configure();
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//		String userName = "admin";
//		String passwd = "admin";
//		String hql = "from com.smit.vo.SmitAdmin s where userName='" + userName + "' and passwd='" + passwd + "'";
//		
//		List list = session.createQuery(hql).list();
//		if(list.size() > 0 ){
//			System.out.println("ksksksk");
//		}else {
//			System.out.println("DCCC");
//	    }
//		User user = new User();
//		user.setUserName("gunstofire");
//		user.setPassword("123456");
//		user.setEmail("ligm@szmg.com.cn");
//		user.setExplain("supper admin");
//		user.setTel("254122");
//		user.setState(0);
//        Set users = new HashSet();
//        users.add(user);
//		Group group = new Group();
//		group.setGroupName("editor");
//		group.setSortRank(100);
//	Date date = new Date();
//	group.setCreatetime(new Timestamp(date.getTime()));
//		group.setUsers(users);
//		session.save(group);
//		session.beginTransaction().commit();
//		User user = new User();
//		user.setUserName("kkkkkkkf");
//		user.setPassword("123456");
//		user.setEmail("ligm@szmg.com.cn");
//		user.setExplain("supper admin");
//		user.setTel("254122");
//		user.setState(0);
//        Set users = new HashSet();
//        users.add(user);
//		Group group = new Group();
//		group.setGroupName("tttttt");
//		group.setSortRank(100);
//	Date date = new Date();
//	group.setCreatetime(new Timestamp(date.getTime()));
//		group.setUsers(users);
//		session.save(group);
//		session.beginTransaction().commit();
//		Group group = (Group)session.load(Group.class, 2);
//		session.delete(group);
//		session.beginTransaction().commit();
		
		User user = new User();
		user.setUserName("kkkdk");		
		user.setEmail("kddkdk");
		Test t = new Test();
		try {
			
			t.getHibernateTemplate().save(user);
		}catch(HibernateException e){
			e.printStackTrace();
			
		}
		
	}

}
