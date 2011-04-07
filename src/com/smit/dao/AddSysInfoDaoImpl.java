package com.smit.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.SysInfo;

public class AddSysInfoDaoImpl extends HibernateDaoSupport implements AddSysInfoDao{
	private SessionFactory sessionFactory;
	
	public void SessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	/*
	@Resource(name="sessionFactory")
    public void setSF(SessionFactory sessionFactory) {
            super.setSessionFactory(sessionFactory);
    }
    */

	public boolean addSysInfo(final String key, final String value)
	{
		SysInfo info = new SysInfo();
		info.setId(null);
		info.setInfo_key(key);
		info.setInfo_value(value);
		Serializable ser = this.getHibernateTemplate().save(info);
		if(ser == null)
		{
			return false;
		}
		return true;
	}
	
	public List<SysInfo> queryAllSysInfo()
	{
		String hql = "from com.smit.vo.SysInfo";
		Session session = CustomSessionFactory.currentSession();
		Transaction ts = null;
		SysInfo sysInfo = null;
		List<SysInfo> allSysInfo = new ArrayList();
		try
		{
			Query query = session.createQuery(hql);
			ts = session.beginTransaction();
			
			allSysInfo = query.list();
			ts.commit();
		}
		catch(Exception e)
		{
			if(ts != null)
			{
				ts.rollback();
			}
			CustomSessionFactory.closeSession();
		}
		return allSysInfo;
	}
	
	public List querySysInfoByKey(final String key)
	{
		String hql = "from com.smit.vo.SysInfo s where info_key='" + key + "'";
		List list = this.getHibernateTemplate().find(hql);
		System.out.println(list.size());
		if(list.size() > 0 ){
			return list;
		} 
		return null;
	}
	
	public boolean deleteSysInfo(final String key)
	{
		Session session = CustomSessionFactory.currentSession();
		Transaction ts = null;
		String hql = "from com.smit.vo.SysInfo where info_key = '" + key + "'";
		List<SysInfo> allSysInfo = new ArrayList();
		try
		{
			Query query = session.createQuery(hql);
			ts = session.beginTransaction();
			allSysInfo = query.list();
			if(allSysInfo.size()<=0)
			{
				return false;
			}
			
			//SysInfo sysInfoToDelete = (SysInfo) session.get(SysInfo.class, key);
			for(int i=0; i<allSysInfo.size(); i++)
			{
				session.delete(allSysInfo.get(i));
			}
			ts.commit();
		}
		catch(Exception e)
		{
			if(ts != null)
			{
				ts.rollback();
			}
			CustomSessionFactory.closeSession();
			return false;
		}
		return true;
	}
}
