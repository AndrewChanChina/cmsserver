package com.smit.service;

import java.util.ArrayList;
import java.util.List;

import com.smit.dao.ColumnDao;
import com.smit.dao.SysInfoDao;
import com.smit.vo.Part;

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
	
	public boolean deleteColumn(final String id)
	{
		if(columnDao.deleteColumn(id))
		{
			return true;
		}
		return false;
	}
	public boolean deleteColumn(final ArrayList<String> idList)
	{
		if(columnDao.deleteColumn(idList))
		{
			return true;
		}
		return false;
	}
	public boolean updateColumn(final ArrayList<Part> columnList)
	{
		if(columnDao.updateColumn(columnList))
		{
			return true;
		}
		return false;
	}
	
	public List<Part> queryAllColumns() throws Exception
	{
		List<Part> allColumns = columnDao.queryAllColumns();
		return allColumns;
	}
}
