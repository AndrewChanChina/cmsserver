package com.smit.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.Subscriber;

public class SubscriberDaoImpl extends HibernateDaoSupport implements SubscriberDao{

	public void addSubscriber(Subscriber sub) {
		this.getHibernateTemplate().save(sub);
	}

	public List<Subscriber> findByCallUrl(String url) {
		String hql = "from Subscriber s where s.feed_url='"+url+"'";
		return this.getHibernateTemplate().find(hql);
	}

	public void deleteSubscriber(Subscriber sub) {
		this.getHibernateTemplate().delete(sub);
	}

	public List<Subscriber> findByTopicCallback(String feed, String callback) {
		String hql = "from Subscriber s where s.feed_url='"+feed+"'"+" and s.callback_url='"+callback+"'";
		return this.getHibernateTemplate().find(hql);
	}

}
