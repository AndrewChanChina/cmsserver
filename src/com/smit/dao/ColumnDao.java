package com.smit.dao;

import java.util.ArrayList;
import java.util.List;

import com.smit.vo.Part;

public interface ColumnDao {
	public List<Part> queryAllColumns() throws Exception;
	public boolean addColumn(String parentID, String classToAdd, String path);
	
	public boolean deleteColumn(final String id);
	public boolean deleteColumn(final ArrayList<String> idList);
	public boolean updateColumn(final ArrayList<Part> columnList);
	
	Part queryByColumnId(final Integer id) throws Exception;
}
