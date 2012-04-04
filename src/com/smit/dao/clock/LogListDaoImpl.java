package com.smit.dao.clock;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.smit.dao.GenericDaoImpl;
import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.LogList;

public class LogListDaoImpl extends GenericDaoImpl<LogList, Long> implements
		LogListDao {

	public List<LogList> listLatestLog(final SmitPage page, final String[] names,
			final String[] values){
		
		if (page == null)
			return listAll(names, values);

		StringBuffer hql = new StringBuffer("SELECT count(*) FROM com.smit.vo.alarmclock.LogList g");
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
						"select g FROM com.smit.vo.alarmclock.LogList g");
				for (int i = 0; i < names.length; i++) {
					if (i == 0) {
						hql.append(" WHERE ");
					} else {
						hql.append(" AND ");
					}
					hql.append(names[i]).append("=\'").append(values[i])
							.append("\'");
				}
				hql.append(" ORDER BY timestamp DESC");
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
				"SELECT g FROM com.smit.vo.alarmclock.LogList g");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				hql.append(" WHERE ");
			} else {
				hql.append(" AND ");
			}
			hql.append(names[i]).append("=\'").append(values[i]).append("\'");			
		}
		hql.append(" ORDER BY timestamp DESC");
		return this.getHibernateTemplate().find(hql.toString());

	}
}
