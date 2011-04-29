package com.smit.service;

import com.smit.util.Page;
import com.smit.vo.Media;

public interface MediaService {
	public void save(Media media);
	public void delete(int id);
	public void update(int id);
	public Page findAll(int currentPage,int pageSize);
	public Media findById(int id);
	
	

}
