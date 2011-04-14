package com.smit.dao;

import java.util.List;

import com.smit.vo.Part;

public interface ColumnDao {
	public List<Part> queryAllColumns();
	public boolean addColumn(String parentID, String classToAdd, String path);
}
