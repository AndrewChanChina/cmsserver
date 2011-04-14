package com.smit.service;

import com.smit.dao.ColumnDao;
import com.smit.dao.SysInfoDao;

public class ColumnServiceImpl implements ColumnService {
	
	private ColumnDao columnDao;
	
	public void setColumnDao(ColumnDao dao)
	{
		this.columnDao = dao;
	}
	
	public boolean addColumn(String parentID, String classToAdd, String path)
	{
		if(columnDao.addColumn(parentID, classToAdd, path))
		{
			return true;
		}
		return false;
		
	}
}
