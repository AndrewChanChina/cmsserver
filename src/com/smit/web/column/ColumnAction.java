package com.smit.web.column;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.dao.ColumnDao;
import com.smit.dao.ColumnDaoImpl;
import com.smit.service.ColumnService;
import com.smit.vo.Part;

public class ColumnAction extends MappingDispatchAction {
	
	private ColumnService columnService;
	   
	public void setColumnService(ColumnService columnService)
	{
		this.columnService = columnService;
	}

	public ActionForward addColumn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddColumnForm addColumnForm = (AddColumnForm)form;
		String parentID = addColumnForm.getParentID();
		String classToAdd = addColumnForm.getClassToAdd();
		String path = addColumnForm.getPath();
		System.out.println("parentID: " + parentID);
		System.out.println("classToAdd: " + classToAdd);
		System.out.println("path: " + path);
		if(classToAdd.isEmpty())
		{
			System.out.println("classToAdd should not be empty");
			return null;
		}
		if(columnService.addColumn(parentID, classToAdd, path))
		{
			List<Part> allColumns = columnService.queryAllColumns();
			request.setAttribute("allColumns", allColumns);
			return mapping.findForward("reload");
		}
		else
		{
			//return mapping.findForward("fail");
		}
		return null;
	}
	
	public ActionForward deleteColumn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idToDelete=request.getParameter("id");
		String idListToDelete = request.getParameter("idList");

		System.out.println("We will delete the SysInfo: id = " + idToDelete);
		if(idToDelete != null && ! idToDelete.equalsIgnoreCase("null"))
		{
			if(columnService.deleteColumn(idToDelete))
			{
				List<Part> allColumns = columnService.queryAllColumns();
				request.setAttribute("allColumns", allColumns);
				return mapping.findForward("reload");
			}
		}
		if(idListToDelete != null && ! idListToDelete.equalsIgnoreCase("null"))
		{
			ArrayList<String> list = parseIdList(idListToDelete);
			if(columnService.deleteColumn(list))
			{
				List<Part> allColumns = columnService.queryAllColumns();
				request.setAttribute("allColumns", allColumns);
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
	}
	
	public ActionForward updateColumn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String itemListToUpdate = request.getParameter("itemList");

		if(itemListToUpdate != null && ! itemListToUpdate.equalsIgnoreCase("null"))
		{
			ArrayList<Part> list = parseColumnList(itemListToUpdate);
			if(columnService.updateColumn(list))
			{
				List<Part> allColumns = columnService.queryAllColumns();
				request.setAttribute("allColumns", allColumns);
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
	}
	
	ArrayList<String> parseIdList(String idList)
	{
		String tmpIdList = idList;
		int first = -1;
		ArrayList<String> list = new ArrayList<String>();
		while((first = tmpIdList.indexOf("_")) != -1)
		{
			String idStr = tmpIdList.substring(0, first);
			list.add(idStr);
			
			tmpIdList = tmpIdList.substring(first+1);
		}
		return list;
	}
	
	private ArrayList<Part> parseColumnList(String itemListToUpdate)
	{
		ArrayList<Part> list = new ArrayList<Part>();
		//
		String tmpIdList = itemListToUpdate;
		int first = -1;
		while((first = tmpIdList.indexOf("___")) != -1)
		{
			String oneItem = tmpIdList.substring(0, first);
			Part column = parseOneItem(oneItem);
			list.add(column);
			
			tmpIdList = tmpIdList.substring(first+1);
		}
		return list;
	}
	
	private Part parseOneItem(String oneItem)
	{
		Part column = new Part();
		String tmpContentList = oneItem;
		int first = -1;
		int count = 0;
		ColumnDao columnDao = new ColumnDaoImpl();
		
		while((first = tmpContentList.indexOf("^^^")) != -1)
		{
			String content = tmpContentList.substring(0, first);
			switch(count)
			{
			case 0:
				Integer intId = Integer.valueOf(content,10);
				//column.setId(intId);
				try {
					column = columnDao.queryByColumnId(intId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				column.setTypename(content);
				break;
			case 2:
				Integer intFatherId = Integer.valueOf(content,10);
				column.setFather_id(intFatherId);
				break;
			case 3:
				column.setPath(content);
				break;
			default:
				break;
			}
			count ++;
			tmpContentList = tmpContentList.substring(first+1);
		}
		return column;
	}
}
