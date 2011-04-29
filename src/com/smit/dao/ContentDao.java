package com.smit.dao;

import com.smit.util.Page;
import com.smit.vo.Content;
import com.smit.vo.Media;

public interface ContentDao {
	public void save(Content content);
	public void update(Content content);
	public void delete(Content content);
	public Page getPage(int currentPage,int pageSize,int pid);
	public Content getById(Integer id);
	public void saveorupdate(Content content);
	public Page getPage(int currentPage,int pageSize);


}
