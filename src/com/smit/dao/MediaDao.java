package com.smit.dao;

import com.smit.util.Page;
import com.smit.vo.Media;

public interface MediaDao {
	public void save(Media media);
	public void update(Media media);
	public void delete(Media media);
	public Page getPage(int currentPage,int pageSize);
	public Media getById(Integer id);

}
