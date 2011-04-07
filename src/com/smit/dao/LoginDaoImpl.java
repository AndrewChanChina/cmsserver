package com.smit.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

	@Override
	public boolean getAdminMsg(String userName,String passwd) {
		String hql = "from com.smit.vo.SmitAdmin s where userName='" + userName + "' and passwd='" + passwd + "'";
		List list = this.getHibernateTemplate().find(hql);
		
		System.out.println(list.size());
		if(list.size() > 0 ){
			return true;
		}else 
		return false;
	}

}
