package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.SmitPage;
import com.smit.vo.News;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao{

	
	public void insert(News news) {
		
		this.getHibernateTemplate().save(news);
	}

	public List<News> getNews() {
		
		return this.getHibernateTemplate().find("from News n");
	}

	public List<News> findByPartId(final SmitPage page,final int partId) {
		List count = getHibernateTemplate().find("SELECT count(*) FROM com.smit.vo.News n where n.partId=" + partId);
		page.setTotalCount(Integer.parseInt(count.get(0).toString() ));
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {  
            public Object doInHibernate(Session s) throws HibernateException,  
                    SQLException {  
            	String hql = "FROM News n where n.partId=" + partId;
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

	public List<Object[]> getLatestNews() {
		String hql = "select n.title,n.description,n.link,n.author,n.comments,n.category,n.pubDate from news n inner join (select n1.part_id,max(n1.id)id from news n1 group by part_id)t on n.id=t.id order by create_time desc";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createSQLQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(20);
		return query.list();
	}

}
