package com.smit.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.Subscriber;

public class SubscriberDaoImpl extends HibernateDaoSupport implements SubscriberDao{

	@Override
	public void addSubscriber(Subscriber sub) {
		this.getHibernateTemplate().save(sub);
	}

	@Override
	public List<Subscriber> findByCallUrl(String url) {
		String hql = "from Subscriber s where s.feed_url='"+url+"'";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteSubscriber(Subscriber sub) {
		this.getHibernateTemplate().delete(sub);
	}

	@Override
	public List<Subscriber> findByTopicCallback(String feed, String callback) {
		String hql = "from Subscriber s where s.feed_url='"+feed+"'"+" and s.callback_url='"+callback+"'";
		return this.getHibernateTemplate().find(hql);
	}

}
