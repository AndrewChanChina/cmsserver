package com.smit.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.ColumnService;
import com.smit.vo.Part;

public class LeftFrameAction extends Action {
	
	private ColumnService queryAllColumnsService;
	public void setQueryAllColumnsService(ColumnService queryAllColumnsService)
	{
		this.queryAllColumnsService = queryAllColumnsService;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String whichClass = request.getParameter("class");
		
		if(whichClass.equalsIgnoreCase("sysInfoAndLog"))
		{
			return mapping.findForward("sys_info_and_log_page");
		}
		else if(whichClass.equalsIgnoreCase("column"))
		{
			List<Part> allColumns = queryAllColumnsService.queryAllColumns();
			request.setAttribute("allColumns", allColumns);
			return mapping.findForward("column_tree_page");
		}
		return null;
	}
}