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
		this.getHibernateTemplate().save(clock);
	}

	public void update(Clock clock) {
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
		if(ls == null || ls.size() < 1){
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

	public List<Object[]> getLatestItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
