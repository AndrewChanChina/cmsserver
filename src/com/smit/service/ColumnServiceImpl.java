package com.smit.service;

import java.util.ArrayList;
import java.util.List;

import com.smit.dao.ColumnDao;
import com.smit.service.webService.ColumnItem;
import com.smit.service.webService.IToXML;
import com.smit.service.webService.XmlWrap;
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

	public Part findByName(String name) {
		return columnDao.findByName(name);
	}

	/**
	 *  convert object information into xml object
	 */
	public XmlWrap queryNextChildren(Integer partId ) {
		List<Part> list = columnDao.queryNextChildren(partId);
		return toXmlWrap(list);
	}

	public XmlWrap queryRootChildren() throws Exception {
		List<Part> list = columnDao.queryTopColumns();
		return toXmlWrap(list);
	}
	
	private XmlWrap toXmlWrap(List<Part> list){
		XmlWrap xmlWrap = new XmlWrap();
		List<IToXML> items = new ArrayList<IToXML>();
		for(Part p : list){
			ColumnItem item = new ColumnItem();
			item.setName(p.getTypename());
			item.setKey(p.getPartId().toString());
			if(columnDao.queryNextChildren(p.getId()).isEmpty()) {
				item.setType(ColumnItem.TYPE_LEAF);
			} else {
				item.setType(ColumnItem.TYPE_NODE);
			}
			items.add(item);
		}
		xmlWrap.setItems(items);
		return xmlWrap;		
	}

	public void savePart(Part part) {
		columnDao.savePart(part);
	}

}
