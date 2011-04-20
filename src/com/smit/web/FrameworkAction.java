package com.smit.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ColumnService;
import com.smit.service.SysInfoService;
import com.smit.vo.Part;
import com.smit.vo.SysInfo;

public class FrameworkAction extends MappingDispatchAction {
	private ColumnService columnService;
	private SysInfoService sysInfoService;
	public void setColumnService(ColumnService columnService)
	{
		this.columnService = columnService;
	}
	
	public void setSysInfoService(SysInfoService sysInfoService)
	{
		this.sysInfoService = sysInfoService;
	}
	
	public ActionForward showLeftFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
			String whichClass = request.getParameter("class");
			if(whichClass.equalsIgnoreCase("column"))
			{
				List<Part> allColumns = columnService.queryAllColumns();
				request.setAttribute("allColumns", allColumns);
				return mapping.findForward("column_tree_page");
			}
			return mapping.findForward("to_left");
	}
	
	public ActionForward showRightFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String whichClass = request.getParameter("class");
		if(whichClass.equalsIgnoreCase("sysInfoAndLog"))
		{
			List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
			request.setAttribute("allSysInfos", allSysInfos);
			return mapping.findForward("sys_info_page");//right.jsp
		}
		else if(whichClass.equalsIgnoreCase("column"))
		{
			List<Part> allColumns = columnService.queryAllColumns();
			request.setAttribute("allColumns", allColumns);
			return mapping.findForward("add_Column_page");//add_column.jsp
		}
		return null;
	}
	
	public ActionForward showLogInfoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("logInfo");
	}
	
	public ActionForward showSysInfoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
		request.setAttribute("allSysInfos", allSysInfos);
		return mapping.findForward("sysInfo");
	}
}
