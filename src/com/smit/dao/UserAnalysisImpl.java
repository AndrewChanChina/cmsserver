package com.smit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.UserAnalysis;

public class UserAnalysisImpl extends HibernateDaoSupport implements UserAnalysisDao{

	
	@Override
	public void insert(UserAnalysis us) {
		this.getHibernateTemplate().save(us);
	}

	@Override
	public List<UserAnalysis> getAnalysis() {
		return this.getHibernateTemplate().find("from UserAnalysis u");
	}

	
}
