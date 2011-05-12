package com.smit.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.vo.Part;

public class ColumnDaoImpl extends HibernateDaoSupport implements ColumnDao
{
	private String ROOT_NAME = "All Columns";
	private SessionFactory sessionFactory;
	
	public void SessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public boolean isRootColumn(Integer id) throws Exception
	{
		Part part = queryByColumnId(id);
		if(part.getTopid().equals(0) && part.getFather_id().equals(0))
		{
			return true;
		}
		return false;
	}
	
	public boolean isTopColumn(Integer id) throws Exception
	{
		Part root = queryRootColumn();
		Part part = queryByColumnId(id);
		Integer rootId = root.getId();
		if(part.getFather_id().equals(rootId) &&
			part.getTopid().equals(rootId))
		{
			return true;
		}
		return false;
	}
	
	private List<Part> getNextLayerChildren(Integer nodeID, List<Part> allNodes)
	{
		List<Part> nextLayerChildren = new ArrayList<Part>();
		for(int i=0; i<allNodes.size(); i++)
		{
			Part p = allNodes.get(i);
			if(p.getFather_id().equals(nodeID))
			{
				nextLayerChildren.add(p);
			}
		}
		return nextLayerChildren;
	}
	
	private List<Part> getAllSubLayerChildren(Integer nodeID, List<Part> allNodes)
	{
		List<Part> allSubChildren = new ArrayList<Part>();

		List<Part> nextLayerChildren = getNextLayerChildren(nodeID, allNodes);
		allSubChildren.addAll(nextLayerChildren);
		for(int i = 0; i<nextLayerChildren.size(); i++)
		{
			List<Part> ps = getAllSubLayerChildren(nextLayerChildren.get(i).getId(), allNodes);
			allSubChildren.addAll(ps);
		}
		return allSubChildren;
	}
	
	private void recursiveDeleteChildren(Integer nodeIDToDelete)
	{
		List<Part> allParts = queryAllColumns();
		recursiveDelete(nodeIDToDelete, allParts);
	}
	
	private void recursiveDelete(Integer nodeIDToDelete, List<Part> allParts)
	{
		List<Part> nextLayerChildren = getNextLayerChildren(nodeIDToDelete, allParts);
		for(int i=0; i<nextLayerChildren.size(); i++)
		{
			Part p = nextLayerChildren.get(i);
			recursiveDelete(p.getId(), allParts);
			deleteColumn(Integer.toString(p.getId(), 10));
		}
	}
	
	public boolean addColumn(Integer topID, Integer parentID, String typeName)
	{
		HibernateTemplate ht = this.getHibernateTemplate();

		Part part = new Part();
		part.setFather_id(parentID);
		part.setId(null);
		part.setTopid(topID);
		part.setTypename(typeName);
		ht.saveOrUpdate(part);
		return true;
	}
	
	public List<Part> queryAllColumns() 
	{
		HibernateTemplate ht = getHibernateTemplate();
		List<Part> list = ht.executeFind(new HibernateCallback<Object>() {
	            public Object doInHibernate(Session s) throws HibernateException, SQLException
	            {  
	            	String hql = "FROM com.smit.vo.Part p";
	                Query query = s.createQuery(hql);  
	                List<Part> list = query.list();  
	                return list;  
	            }
	        });
		if(list == null)
		{
			list = new ArrayList<Part>();
		}
		if(list.size() == 0)
		{
			Part partRoot = new Part();
			partRoot.setId(null);
			partRoot.setTypename(ROOT_NAME);
			partRoot.setTopid(0);
			partRoot.setFather_id(0);
			
			ht.save(partRoot);
			list.add(partRoot);
		}
		return list;
	}
	
	public List<Part> queryTopColumns() throws Exception
	{
		Part root = queryRootColumn();
		final Integer rootID = root.getId();
		return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {  
	            public Object doInHibernate(Session s) throws HibernateException, SQLException
	            {  
	            	String hql = "FROM com.smit.vo.Part p where father_id = " + rootID;
	                Query query = s.createQuery(hql);  
	                List<Part> list = query.list();  
	                return list;  
	            }
	        });
	}

	public boolean deleteColumn(final String id)
	{
		Integer intId = Integer.valueOf(id,10);
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		recursiveDeleteChildren(intId);
		Part column = ht.get(Part.class, intId);
		if(column == null)
		{
			return false;
		}
		ht.delete(column);
		return true;
	}
	public boolean deleteColumn(final ArrayList<String> idList)
	{
		for(int i=0; i<idList.size(); i++)
		{
			deleteColumn(idList.get(i));
		}
		return true;
	}
	
	private boolean isAncestor(Integer ancestorId, Integer childId) throws Exception
	{
		boolean ret = false;
		Part ancestor = queryByColumnId(ancestorId);
		Part child = queryByColumnId(childId);
		Part root = queryRootColumn();
		Part tmp = child;
		while(true)
		{
			Integer t = tmp.getTopid();
			Integer f = tmp.getFather_id();
			if(t.equals(ancestorId) || f.equals(ancestorId))
			{
				return true;
			}
			if(t.equals(root.getId()) || f.equals(root.getId()))
			{
				return false;
			}
			tmp = queryByColumnId(f);
		}
	}
	
	public boolean updateColumn(final ArrayList<Part> columnList)
	{
		HibernateTemplate ht = this.getHibernateTemplate();
		if(ht == null)
		{
			return false;
		}
		for(int i=0; i<columnList.size(); i++)
		{
			ht.saveOrUpdate(columnList.get(i));
		}
		return true;
	}
	
	public Part queryByColumnId(final Integer id) throws Exception
	{
		List<Part> partsList = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {  
            public Object doInHibernate(Session s) throws HibernateException, SQLException
            {  
            	String hql = "FROM com.smit.vo.Part p where id=" + id;
                Query query = s.createQuery(hql);
                List<Part> list = query.list();
               
                return list;
            }
        });
		Part part = new Part();
        if(!partsList.isEmpty() && partsList.size()>0)
        {
        	part = partsList.get(0);
        }
        return part;
	}
	
	public List<Part> queryAllChildsUnderTop(final String id) throws Exception
	{
		return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {  
            public Object doInHibernate(Session s) throws HibernateException, SQLException
            {  
            	String hql = "FROM com.smit.vo.Part p where topid = " + id;
                Query query = s.createQuery(hql);
                List<Part> list = query.list();
                return list;
            }
        });
	}
	
	public Part queryRootColumn() throws Exception
	{
		HibernateTemplate ht = getHibernateTemplate();
		List<Part> list = ht.executeFind(new HibernateCallback<Object>() {
            public Object doInHibernate(Session s) throws HibernateException, SQLException
            {
            	String hql = "FROM com.smit.vo.Part p where topid = 0 AND father_id = 0";
                Query query = s.createQuery(hql);
                List<Part> list = query.list();
                if(list == null || list.size() == 0)
                {
                	return null;
                }
                return list;
            }
        });
		if(list != null)
		{
			return list.get(0);
		}
		else
		{
			//Insert into Part table the root info
			Part partRoot = new Part();
			partRoot.setId(null);
			partRoot.setTypename(ROOT_NAME);
			partRoot.setTopid(0);
			partRoot.setFather_id(0);
			
			ht.save(partRoot);
			return partRoot;
		}
	}


	@Override
	public Part findByName(String name) {
		String hql = "from com.smit.vo.Part s where s.typename='" + name + "'";		
		List list = this.getHibernateTemplate().find(hql);
		
		if(list.size() < 1){
			return null;
		}
		return (Part)list.get(0);
	}
	
	@Override
	public List<Part> queryNextChildren(Integer partId){
		String hql = "from com.smit.vo.Part s where s.partId = '" + partId + "'";
		List<Part> list = this.getHibernateTemplate().find(hql);
		if(list.isEmpty())
			return list;
		
		hql = "from com.smit.vo.Part s where s.father_id = '" + list.get(0).getId() + "'";
		list = this.getHibernateTemplate().find(hql);
		
		return list;		
	}



	@Override
	public void savePart(Part part) {
		this.getHibernateTemplate().save(part);
	}
}
