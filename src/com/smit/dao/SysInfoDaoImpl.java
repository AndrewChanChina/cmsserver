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

public class SysInfoDaoImpl extends HibernateDaoSupport implements SysInfoDao{
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
		info.setInfo_state("Y");//enable this sysinfo
		Serializable ser = this.getHibernateTemplate().save(info);
		if(ser == null)
		{
			return false;
		}
		return true;
	}
	
	public List<SysInfo> queryAllSysInfo() throws Exception
	{
		String hql = "from com.smit.vo.SysInfo";
		Session session;
		try {
			session = CustomSessionFactory.currentSession();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw e1;
		}
		Transaction ts = null;
		SysInfo sysInfo = null;
		List<SysInfo> allSysInfo = new ArrayList();
		Query query = null; 
		try
		{
			query = session.createQuery(hql);
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
			
		}
		finally
		{
			session.flush();
			session.clear();
		}
		return allSysInfo;
	}
	
	/*
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
	*/
	
	public boolean deleteSysInfo(final ArrayList<String> idList)
	{
		for(int i=0; i<idList.size(); i++)
		{
			deleteSysInfo(idList.get(i));
		}
		return true;
	}
	
	public boolean deleteSysInfo(final String id)
	{
		Integer intId = Integer.valueOf(id,10);
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		SysInfo sysInfo = ht.get(SysInfo.class, intId);
		if(sysInfo == null)
		{
			return false;
		}
		ht.delete(sysInfo);
		return true;
		/*
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
		*/
		
	}
	
	public boolean updateSysInfo(final ArrayList<SysInfo> sysInfoList)
	{
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		for(int i=0; i<sysInfoList.size(); i++)
		{
			ht.saveOrUpdate(sysInfoList.get(i));
		}
		return true;
	}
	
	public boolean enableSysInfo(final Integer id, boolean trueOrFalse)
	{
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		SysInfo info = ht.get(SysInfo.class, id);
		if(trueOrFalse)
		{
			info.setInfo_state("Y");
		}
		else
		{
			info.setInfo_state("N");
		}

		ht.saveOrUpdate(info);

		return true;
	}
}
