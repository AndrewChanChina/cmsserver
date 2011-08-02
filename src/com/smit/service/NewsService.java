package com.smit.service;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.News;

public interface NewsService {

	public void insert(News news);
	public List<News> getNews();
	public void insert(List<News> list);
	public List<News> findByPartId(SmitPage page,int id);
	public List<Object[]> getLatestNews();
}
