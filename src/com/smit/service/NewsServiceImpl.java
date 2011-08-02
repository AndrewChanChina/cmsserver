package com.smit.service;

import java.util.List;

import com.smit.dao.NewsDao;
import com.smit.util.SmitPage;
import com.smit.vo.News;

public class NewsServiceImpl implements NewsService{

	private NewsDao newsDao;
	
	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public void insert(News news) {
		newsDao.insert(news);
	}

	@Override
	public List<News> getNews() {
		
		return newsDao.getNews();
	}

	@Override
	public void insert(List<News> list) {
		for(News n :list){
			insert(n);
		}
	}

	@Override
	public List<News> findByPartId(SmitPage page,int id) {
		return newsDao.findByPartId(page,id);
	}

	@Override
	public List<Object[]> getLatestNews() {
		return newsDao.getLatestNews();
	}

	
}
