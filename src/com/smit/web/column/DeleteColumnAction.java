package com.smit.web.column;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.ColumnService;
import com.smit.service.SysInfoService;


public class DeleteColumnAction extends Action {
	
	private ColumnService deleteColumnService;
	   
	public void setDeleteColumnService(ColumnService deleteColumnService) {
		this.deleteColumnService = deleteColumnService;
    }
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
		{
		String idToDelete=request.getParameter("id");
		String idListToDelete = request.getParameter("idList");

		System.out.println("We will delete the SysInfo: id = " + idToDelete);
		if(idToDelete != null && ! idToDelete.equalsIgnoreCase("null"))
		{
			if(deleteColumnService.deleteColumn(idToDelete))
			{
				return mapping.findForward("reload");
			}
		}
		if(idListToDelete != null && ! idListToDelete.equalsIgnoreCase("null"))
		{
			ArrayList<String> list = parseIdList(idListToDelete);
			if(deleteColumnService.deleteColumn(list))
			{
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
}
