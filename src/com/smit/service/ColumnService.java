package com.smit.service;

import java.util.ArrayList;
import java.util.List;

import com.smit.vo.Part;
import com.smit.vo.SysInfo;

public interface ColumnService {
	//Part queryByColumnId(final Integer id) throws Exception;
	public boolean addColumn(Integer topID, Integer parentID, String typeName);
	public boolean deleteColumn(final String id);
	public boolean deleteColumn(final ArrayList<String> idList);
	public boolean updateColumn(final ArrayList<Part> columnList);
	public List<Part> queryAllColumns() throws Exception;
	public List<Part> queryTopColumns() throws Exception;
	public Part queryRootColumn() throws Exception;
	public List<Part> queryAllChildsUnderTop(final String id) throws Exception;
	public Part queryByColumnId(final Integer id) throws Exception;
}
