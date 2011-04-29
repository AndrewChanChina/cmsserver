package com.smit.service;

import com.smit.util.Page;
import com.smit.vo.Content;
import com.smit.vo.Media;

public interface ContentService {
	public void save(Content content);
	public void delete(int id);
	public void update(Content content);
	public Page findAll(int currentPage,int pageSize,int pid);
	public Content findById(int id);
	public void saveorupdate(Content content);
	public Page findAll(int currentPage,int pageSize);
	

}
