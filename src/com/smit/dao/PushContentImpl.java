package com.smit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.PushContent;

public class PushContentImpl extends HibernateDaoSupport implements PushContentDao{

	private SessionFactory sessionFactory;
	public void sessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean insertContent(PushContent content) {
		this.getHibernateTemplate().save(content);
		return true;
	}

	@Override
	public List<PushContent> queryContent(int start,int num,String username) {
		String hql = "from PushContent p where p.username='"+username+"' order by p.create_time desc";
		Query query = this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(num);
		List<PushContent> list = query.list();
		return list;
	}

	@Override
	public List<PushContent> queryByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PushContent> queryContent(String username) {
		String hql = "from PushContent p where username='"+username+"' order by p.create_time desc";
//		Query query = this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql);
//		
		List<PushContent> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	@Override
	public void deleteContent(PushContent content) {
		this.getHibernateTemplate().delete(content);
	}
	@Override
	public PushContent getById(int id) {
		PushContent c = this.getHibernateTemplate().load(PushContent.class, id);
		return c;
	}
	
	
}
