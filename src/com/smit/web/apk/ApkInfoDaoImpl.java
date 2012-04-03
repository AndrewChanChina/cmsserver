package com.smit.web.apk;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.apk.ApkInfo;

public class ApkInfoDaoImpl extends HibernateDaoSupport implements ApkInfoDao {

	public void save(ApkInfo apkInfo) {
		this.getHibernateTemplate().save(apkInfo);
	}

	public void update(ApkInfo apkInfo) {
		this.getHibernateTemplate().update(apkInfo);
	}

	public void delete(ApkInfo apkInfo) {
		this.getHibernateTemplate().delete(apkInfo);
	}

	public List<ApkInfo> findByPartId(SmitPage page, Integer partId) {

		return null;
	}

	public ApkInfo getById(Integer id) {
		return this.getHibernateTemplate().get(ApkInfo.class, id);
	}

	public List<ApkInfo> listAll(final SmitPage page) {
		if (page == null)
			return listAll();

		List count = getHibernateTemplate().find(
				"SELECT count(*) FROM com.smit.vo.apk.ApkInfo");
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				String hql = "select g FROM com.smit.vo.apk.ApkInfo g";
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
				"SELECT g FROM com.smit.vo.apk.ApkInfo g");
	}
	
	private List listAll(final String[] names, final String[] values) {
		StringBuffer hql = new StringBuffer("SELECT g FROM com.smit.vo.apk.ApkInfo g");
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

	public List<ApkInfo> getAllItems() {
		return this.getHibernateTemplate().find("from ApkInfo v");
	}

	public List<Object[]> getLatestItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public ApkInfo findByPackageName(String packageName) {
		List<ApkInfo> list = getHibernateTemplate().find(
				"SELECT g FROM com.smit.vo.apk.ApkInfo g WHERE packageName='"
						+ packageName + "'");
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	public List<ApkInfo> findByNamedParam(final SmitPage page,
			final String[] names, final String[] values) {
		if (page == null)
			return listAll(names,values);

		StringBuffer hql = new StringBuffer("SELECT count(*) FROM com.smit.vo.apk.ApkInfo g");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				hql.append(" WHERE ");
			} else {
				hql.append(" AND ");
			}
			hql.append(names[i]).append("=\'").append(values[i])
					.append("\'");
		}
		
		List count = getHibernateTemplate().find(hql.toString());
		page.setTotalCount(Integer.parseInt(count.get(0).toString()));

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				StringBuffer hql = new StringBuffer(
						"select g FROM com.smit.vo.apk.ApkInfo g");
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

}
