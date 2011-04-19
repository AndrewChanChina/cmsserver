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
	
	public ActionForward showMainFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String classification = request.getParameter("class");
		if(classification == null)
		{
			request.setAttribute("class", "null");
		}
		else if(classification.equalsIgnoreCase("sysInfoAndLog"))
		{
			request.setAttribute("class", classification);
		}
		else if(classification.equalsIgnoreCase("column"))
		{
			request.setAttribute("class", classification);
		}
		return mapping.findForward("mainFrame");
	}
	
	public ActionForward showLeftFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
			String whichClass = request.getParameter("class");
			
			if(whichClass.equalsIgnoreCase("sysInfoAndLog"))
			{
				return mapping.findForward("sys_info_and_log_page");
			}
			else if(whichClass.equalsIgnoreCase("column"))
			{
				List<Part> allColumns = columnService.queryAllColumns();
				request.setAttribute("allColumns", allColumns);
				return mapping.findForward("column_tree_page");
			}
			return null;
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
