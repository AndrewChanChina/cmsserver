package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.PushService;
import com.smit.vo.PushService2User;

public class PushService2UserDaoImpl extends HibernateDaoSupport implements PushService2UserDao {

	public void save(PushService2User ps) {
		this.getHibernateTemplate().save(ps);
	}

	public void update(PushService2User ps) {
		this.getHibernateTemplate().update(ps);		
	}

	public void delete(PushService2User ps) {
		this.getHibernateTemplate().delete(ps);		
	}

	public PushService2User getById(Integer id) {
		return this.getHibernateTemplate().load(PushService2User.class, id);
	}

	public List<PushService2User> listAll(final SmitPage page, final String[] names,
			final String[] values) {
		if(page == null){
			return listAll(names,values);
		}

		List count = getHibernateTemplate().find(
		"SELECT count(*) FROM com.smit.vo.PushService2User");
		
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer(
						"select g FROM com.smit.vo.PushService2User g");
				for (int i = 0; i < names.length; i++) {
					if (i == 0) {
						hql.append(" WHERE ");
					} else {
						hql.append(" AND ");
					}
					hql.append(names[i]).append("=\'").append(values[i])
							.append("\'");
				}
				Query query = s.createQuery(hql.toString());
				int firstRow = page.getPageSize() * (page.getPageIndex() - 1);
				query.setFirstResult(firstRow);
				query.setMaxResults(page.getPageSize());
				List list = query.list();
				return list;
			}
		});
		return list;
	}
	
	private List<PushService2User> listAll(String []names, String []values){
		StringBuffer hql = new StringBuffer("SELECT g FROM com.smit.vo.PushService2User g");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				hql.append(" WHERE ");
			} else {
				hql.append(" AND ");
			}
			hql.append(names[i]).append("=\'").append(values[i])
					.append("\'");
		}
		return this.getHibernateTemplate().find(hql.toString());
	}

	public List<PushService2User> listRoomNum(){
		String sql = "SELECT g FROM com.smit.vo.PushService2User g GROUP BY g.roomNum";
		return this.getHibernateTemplate().find(sql);
	}
}
