package com.smit.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.DaoException;
import com.smit.util.Page;
import com.smit.vo.Media;

public class MediaDaoImpl extends HibernateDaoSupport implements MediaDao {
  
	@Override
	public void save(Media media) {
		try {
			this.getHibernateTemplate().save(media);
		}catch(HibernateException e){
			throw new DaoException("��Դ�������" + e.getMessage());
		}

	}

	@Override
	public void update(Media media) {
		try {
			this.getHibernateTemplate().update(media);
		}catch(HibernateException e){
			throw new DaoException("��Դ���²���" + e.getMessage());
		}

	}

	@Override
	public void delete(Media media) {
		try {
			this.getHibernateTemplate().delete(media);
		}catch(HibernateException e){
			throw new DaoException("��Դɾ�����" + e.getMessage());
		}

	}

	@Override
	public Page getPage(final int currentPage,final int pageSize) {
		Page page = new Page();
		try {
			List list = this.getHibernateTemplate().find("select count(*) from Media");
			Integer recordCount = Integer.parseInt(String.valueOf(list.get(0)));
			
			
			List contents = this.getHibernateTemplate().execute(new HibernateCallback(){
	
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					
					return session.createQuery("from Media c order by c.sortRank" )
					           .setFirstResult((currentPage-1)*pageSize)
					           .setMaxResults(pageSize).list();
				}
				
			});
			page.setTotalRecord(recordCount);
			page.setList(contents);
		
		}catch(HibernateException e){
			throw new DaoException("��Դ���Ҳ���" + e.getMessage());
			
		}
		return page;
	}

	@Override
	public Media getById(Integer id) {
		Media media = null;
		try {
			media = (Media)this.getHibernateTemplate().get(Media.class, id);
			
		}catch(HibernateException e){
			throw new DaoException("�����ԴID���Ҳ���" + e.getMessage());
		}
		return media;
	}

}
