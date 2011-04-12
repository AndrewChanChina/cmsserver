package com.smit.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.dao.SmitLoginDaoImpl;
import com.smit.vo.User;

public class chenyzTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		SmitLoginDaoImpl loginDao = new SmitLoginDaoImpl();
		loginDao.setSessionFactory(sessionFactory);
		
		User user = new User();
		user.setUserName("echen");
		loginDao.register(user);
	}

}
