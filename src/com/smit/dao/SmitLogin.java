package com.smit.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SmitLogin extends HibernateDaoSupport implements ISmitLogin {

	@Override
	public boolean isAdmin(String userName, String password) {
		String hql = "from com.smit.vo.User s where userName='" + userName + "' and passwd='" + password + "'";
		List list = this.getHibernateTemplate().find(hql);
		
		System.out.println(list.size());
		if(list.size() > 0 ){
			return true;
		}else 
		return false;
	}

}
