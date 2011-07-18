package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.News;

public interface NewsDao {
	
	public void insert(News news);
	public List<News> getNews();
	public List<News> findByPartId(SmitPage page,int id);
}
