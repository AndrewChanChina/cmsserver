package com.smit.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.AtomRecord;
import com.smit.vo.RssRecord;

public class RssAtomDaoImpl extends HibernateDaoSupport implements RssAtomDao{

	public void addRssRecord(RssRecord record) {
		this.getHibernateTemplate().save(record);
	}

	public void addAtomRecord(AtomRecord record) {
		this.getHibernateTemplate().save(record);
	}

	public List<RssRecord> findRssByFeed(String feed_url) {
		String hql = "from RssRecord r where r.feed_url='"+feed_url+"'";
		return this.getHibernateTemplate().find(hql);
	}

	public List<AtomRecord> findAtomByFeed(String feed_url) {
		String hql = "from AtomRecord a where a.feed_url='"+feed_url+"'";
		return this.getHibernateTemplate().find(hql);
	}

}
