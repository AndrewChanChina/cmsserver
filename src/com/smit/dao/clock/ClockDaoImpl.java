package com.smit.dao.clock;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.Video;
import com.smit.vo.alarmclock.Clock;

public class ClockDaoImpl extends HibernateDaoSupport implements ClockDao {

	public void save(Clock clock) {
		clock.setAlarmtime(60 * clock.getHour() + clock.getMinutes());
		this.getHibernateTemplate().save(clock);
	}

	public void update(Clock clock) {
		clock.setAlarmtime(60 * clock.getHour() + clock.getMinutes());
		this.getHibernateTemplate().update(clock);
	}

	public void delete(Clock clock) {
		this.getHibernateTemplate().delete(clock);
	}

	public List<Clock> findByPartId(SmitPage page, Integer partId) {

		return null;
	}

	public Clock getById(Integer id) {
		return this.getHibernateTemplate().get(Clock.class, id);
	}

	public Clock getByIdLocal(Integer localId) {
		List<Clock> ls = getHibernateTemplate().find(
				"select g FROM com.smit.vo.alarmclock.Clock g where id_local="
						+ localId);
		if (ls == null || ls.size() < 1) {
			return null;
		}
		return ls.get(0);

	}

	public List<Clock> listAll(final SmitPage page) {
		if (page == null)
			return listAll();

		List count = getHibernateTemplate().find(
				"SELECT count(*) FROM com.smit.vo.alarmclock.Clock");
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				String hql = "select g FROM com.smit.vo.alarmclock.Clock g";
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
				"SELECT g FROM com.smit.vo.alarmclock.Clock g");
	}

	public List<Clock> getAllItems() {
		return this.getHibernateTemplate().find("from Clock v");
	}

	private List<Clock> listAll(Integer startTime, Integer endTime) {
		StringBuffer hql = new StringBuffer(
				"select g FROM com.smit.vo.alarmclock.Clock g WHERE g.alarmtime BETWEEN ")
				.append(startTime).append(" AND ").append(endTime);
		return this.getHibernateTemplate().find(hql.toString());
	}

	public List<Clock> queryByTime(final SmitPage page, final Integer startTime,
			final Integer endTime) {

		if (page == null)
			return listAll(startTime, endTime);

		StringBuffer hql = new StringBuffer(
		"SELECT count(*) FROM com.smit.vo.alarmclock.Clock g WHERE g.alarmtime BETWEEN ")
		.append(startTime).append(" AND ").append(endTime);
		
		List count = getHibernateTemplate().find(hql.toString());
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer(
				"select g FROM com.smit.vo.alarmclock.Clock g WHERE g.alarmtime BETWEEN ")
				.append(startTime).append(" AND ").append(endTime);
				
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

	public List<Clock> queryByName(final SmitPage page, final String[] names,
			final String[] values) {
		if (page == null)
			return listAll(names, values);

		StringBuffer hql = new StringBuffer("SELECT count(*) FROM com.smit.vo.alarmclock.Clock g");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				hql.append(" WHERE ");
			} else {
				hql.append(" AND ");
			}
			hql.append(names[i]).append("=\'").append(values[i]).append("\'");
		}
		
		List count = getHibernateTemplate().find(hql.toString());
		
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer(
						"select g FROM com.smit.vo.alarmclock.Clock g");
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

	private List listAll(final String[] names, final String[] values) {
		StringBuffer hql = new StringBuffer(
				"SELECT g FROM com.smit.vo.alarmclock.Clock g");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				hql.append(" WHERE ");
			} else {
				hql.append(" AND ");
			}
			hql.append(names[i]).append("=\'").append(values[i]).append("\'");
		}
		return this.getHibernateTemplate().find(hql.toString());

	}

	public List<Object[]> getLatestItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
