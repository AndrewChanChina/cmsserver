package com.smit.service;

import java.util.ArrayList;
import java.util.List;

import com.smit.vo.Part;
import com.smit.vo.SysInfo;

public interface ColumnService {
	public boolean addColumn(String parentID, String classToAdd, String path);
	public boolean deleteColumn(final String id);
	public boolean deleteColumn(final ArrayList<String> idList);
	public boolean updateColumn(final ArrayList<Part> columnList);
	public List<Part> queryAllColumns() throws Exception;
}
