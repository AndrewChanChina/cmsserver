package com.smit.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.User;

public class Test extends HibernateDaoSupport {
	public static void main(String[] args){
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		String userName = "admin";
		String passwd = "admin";
		String hql = "from com.smit.vo.SmitAdmin s where userName='" + userName + "' and passwd='" + passwd + "'";
		
		List list = session.createQuery(hql).list();
		if(list.size() > 0 ){
			System.out.println("ksksksk");
		}else {
			System.out.println("DCCC");
	    }
		User user = new User();
		user.setUserName("guns");
		user.setPassword("123456");
		user.setEmail("ligm@szmg.com.cn");
		user.setExplain("supper admin");
		user.setTel("254122");
		user.setState(0);
		session.save(user);
		session.beginTransaction().commit();
	}

}
