package com.smit.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public class LogDaoImpl extends HibernateDaoSupport implements LogDao{

	private SessionFactory sessionFactory;
	
	public void sessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertBaseLog(BaseLog baseLog) {
		this.getHibernateTemplate().saveOrUpdate(baseLog);
		return true;
	}

	@Override
	public boolean insertDetailLog(DetailLog detailLog) {
		this.getHibernateTemplate().saveOrUpdate(detailLog);
		return true;
	}

	
	
}
