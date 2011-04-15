package com.smit.web.column;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.dao.ColumnDao;
import com.smit.dao.ColumnDaoImpl;
import com.smit.dao.CustomSessionFactory;
import com.smit.service.ColumnService;
import com.smit.service.SysInfoService;
import com.smit.vo.Part;
import com.smit.vo.SysInfo;


public class UpdateColumnAction extends Action {
	
	private ColumnService updateColumnService;
	   
	public void setUpdateColumnService(ColumnService updateColumnService) {
		this.updateColumnService = updateColumnService;
    }
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String itemListToUpdate = request.getParameter("itemList");

		if(itemListToUpdate != null && ! itemListToUpdate.equalsIgnoreCase("null"))
		{
			ArrayList<Part> list = parseColumnList(itemListToUpdate);
			if(updateColumnService.updateColumn(list))
			{
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
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
