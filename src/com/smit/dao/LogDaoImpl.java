package com.smit.dao;

import java.util.List;

import org.hibernate.Query;
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
		this.getHibernateTemplate().merge(baseLog);
		return true;
	}

	@Override
	public boolean insertDetailLog(DetailLog detailLog) {
		this.getHibernateTemplate().merge(detailLog);
		return true;
	}

	@Override
	public List<BaseLog> getBaseLog(int id, int begin, int num) {
		String hql = "from BaseLog b where b.device.id='"+id+"'";
		Query query = this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(num);
		List<BaseLog> list = query.list();
		return list;
	}

	@Override
	public List<DetailLog> getDetailLog(int id, int begin, int num) {
		String hql = "from DetailLog d where d.device.id='"+id+"'";
		Query query = this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(num);
		List<DetailLog> list = query.list();
		return list;
	}

	
	
	
}
