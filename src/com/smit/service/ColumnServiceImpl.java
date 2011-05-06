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
	
	public boolean addColumn(Integer topID, Integer parentID, String typeName)
	//public boolean addColumn(String parentID, String classToAdd)
	{
		if(columnDao.addColumn(topID, parentID, typeName))
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
	
	public List<Part> queryTopColumns() throws Exception
	{
		List<Part> allColumns = columnDao.queryTopColumns();
		return allColumns;
	}
	
	public List<Part> queryAllChildsUnderTop(final String id) throws Exception
	{
		List<Part> allChildren = columnDao.queryAllChildsUnderTop(id);
		return allChildren;
	}
	
	public Part queryRootColumn() throws Exception
	{
		Part root = columnDao.queryRootColumn();
		return root;
	}
	
	public Part queryByColumnId(final Integer id) throws Exception
	{
		return columnDao.queryByColumnId(id);
	}
}
