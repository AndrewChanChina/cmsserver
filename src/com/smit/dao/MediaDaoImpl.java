package com.smit.dao;

import java.sql.SQLException;
import java.util.Iterator;
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
		System.out.println(currentPage);
		System.out.println(pageSize);
		Page page = new Page();
		try {
			List list = this.getHibernateTemplate().find("select count(*) from Media");
			Integer recordCount = Integer.parseInt(String.valueOf(list.get(0)));
			System.out.println(recordCount);
			
			List medias = this.getHibernateTemplate().execute(new HibernateCallback(){
	
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					
					return session.createQuery("from Media c order by c.sortRank" )
					           .setFirstResult((currentPage-1)*pageSize)
					           .setMaxResults(pageSize).list();
				}
				
			});
//			System.out.println("大小"+ medias.size());
//			System.out.println("文件名：" + ((Media)medias.get(2)).getFileName());
//			System.out.println("文件名：" + ((Media)medias.get(1)).getFileName());
//			System.out.println("文件名：" + ((Media)medias.get(0)).getFileName());
			page.setCurrentPage(currentPage);
			page.setPageSize(pageSize);
			page.setTotalRecord(recordCount);
			page.setList(medias);
			System.out.println(page.toString());
		
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
