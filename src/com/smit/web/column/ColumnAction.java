package com.smit.web.column;

import java.io.PrintWriter;
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
		String topID = addColumnForm.getTopID();
		String parentID = addColumnForm.getParentID();
		String typeName = addColumnForm.getTypeName();
		//String path = addColumnForm.getPath();
		System.out.println("topID: " + topID);
		System.out.println("parentID: " + parentID);
		System.out.println("typeName: " + typeName);
		//System.out.println("path: " + path);
		if(topID.equals("-9999"))
		{
			setAttributes(request);
			return mapping.findForward("reload");//reloadMainFrame
		}
		if(typeName.isEmpty())
		{
			System.out.println("typeName should not be empty");
			//return null;
			setAttributes(request);
			return mapping.findForward("reload");//reloadMainFrame
		}
		Integer intTopID = Integer.valueOf(topID);
		Integer intParentID = Integer.valueOf(parentID);
		if(intParentID == -9999)
		{
			intParentID = intTopID;
		}
		if(columnService.addColumn(intTopID, intParentID, typeName))
		{
			setAttributes(request);
			return mapping.findForward("reload");//reloadMainFrame
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
				setAttributes(request);
				return mapping.findForward("reload");
			}
		}
		if(idListToDelete != null && ! idListToDelete.equalsIgnoreCase("null"))
		{
			ArrayList<String> list = parseIdList(idListToDelete);
			if(columnService.deleteColumn(list))
			{
				setAttributes(request);
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
				setAttributes(request);
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
	}
	
	public void selectColumnsUnderTopID(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		String topId = request.getParameter("topId");
		PrintWriter w = response.getWriter();
		response.setCharacterEncoding("utf-8");
		Part root = columnService.queryRootColumn();
		Integer param = Integer.valueOf(topId);
		if(root.getId().equals(param))
		{
			String str = "";
			w.write(str);
			return;
		}
		List<Part> topColumns = columnService.queryAllChildsUnderTop(topId);
		String str = formTopColumnsString(topColumns);
		System.out.print(str);
		w.write(str);
		return;
	}
	
	private String formTopColumnsString(List<Part> topColumns)
	{
		String str = "";
		for(int i=0; i<topColumns.size(); i++)
		{
			Part part = topColumns.get(i);
			str += part.getId() + "__" + part.getTopid() + "__" + 
				  part.getFather_id() + "__" + part.getTypename();
			if(i != topColumns.size()-1)//Not last one
			{
				str += "##";
			}
		}
		return str;
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
	
	private void setAttributes(HttpServletRequest request) throws Exception
	{
		Part rootColumn = columnService.queryRootColumn();
		request.setAttribute("rootColumn", rootColumn);
		List<Part> allColumns = columnService.queryAllColumns();
		request.setAttribute("allColumns", allColumns);
		List<Part> allTopColumns = columnService.queryTopColumns();
		request.setAttribute("topColumns", allTopColumns);
	}
	
	private Part parseOneItem(String oneItem)
	{
		Part column = new Part();
		String tmpContentList = oneItem;
		int first = -1;
		int count = 0;
		//ColumnDao columnDao = new ColumnDaoImpl();
		
		while((first = tmpContentList.indexOf("^^^")) != -1)
		{
			String content = tmpContentList.substring(0, first);
			switch(count)
			{
			case 0:
				Integer intId = Integer.valueOf(content,10);
				//column.setId(intId);
				try {
					column = columnService.queryByColumnId(intId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1:
				column.setTypename(content);
				break;
			case 2:
				Integer intTopId = Integer.valueOf(content,10);
			column.setTopid(intTopId);
				break;
			case 3:
				//column.setPath(content);
				Integer intFatherId = Integer.valueOf(content,10);
				if(intFatherId.equals(-9999))
				{
					intFatherId = column.getTopid();
				}
				column.setFather_id(intFatherId);
				break;
			default:
				break;
			}
			count ++;
			tmpContentList = tmpContentList.substring(first+3);
		}
		return column;
	}
}
