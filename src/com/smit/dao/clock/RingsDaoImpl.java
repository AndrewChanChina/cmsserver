package com.smit.dao.clock;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Rings;

public class RingsDaoImpl extends HibernateDaoSupport implements RingsDao {

	public void save(Rings clock) {
		this.getHibernateTemplate().save(clock);
	}

	public void update(Rings clock) {
		this.getHibernateTemplate().update(clock);
	}

	public void delete(Rings clock) {
		this.getHibernateTemplate().delete(clock);
	}


	public Rings getById(Integer id) {
		return this.getHibernateTemplate().get(Rings.class, id);
	}


	public List<Rings> listAll(final SmitPage page) {
		if (page == null)
			return listAll();

		List count = getHibernateTemplate().find(
				"SELECT count(*) FROM com.smit.vo.alarmclock.Rings");
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				String hql = "select g FROM com.smit.vo.alarmclock.Rings g";
				Query query = s.createQuery(hql);
				int firstRow = page.getPageSize() * (page.getPageIndex() - 1);
				query.setFirstResult(firstRow);
				query.setMaxResults(page.getPageSize());
				List list = query.list();
				return list;
			}
		});
		return list;
	}

	private List listAll() {
		return this.getHibernateTemplate().find(
				"SELECT g FROM com.smit.vo.alarmclock.Rings g");
	}
}
