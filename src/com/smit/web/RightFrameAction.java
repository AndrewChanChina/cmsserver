package com.smit.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.ColumnService;
import com.smit.service.SysInfoService;
import com.smit.vo.Part;
import com.smit.vo.SysInfo;

public class RightFrameAction extends Action {
	
	private ColumnService queryAllColumnsService;
	public void setQueryAllColumnsService(ColumnService queryAllColumnsService)
	{
		this.queryAllColumnsService = queryAllColumnsService;
	}
	
	private SysInfoService queryAllSysInfoService;
	public void setQueryAllSysInfoService(SysInfoService queryAllSysInfoService)
	{
		this.queryAllSysInfoService = queryAllSysInfoService;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String whichClass = request.getParameter("class");
		
		if(whichClass.equalsIgnoreCase("sysInfoAndLog"))
		{
			List<SysInfo> allSysInfos = queryAllSysInfoService.queryAllSysInfo();
			request.setAttribute("allSysInfos", allSysInfos);
			return mapping.findForward("sys_info_page");//right.jsp
		}
		else if(whichClass.equalsIgnoreCase("column"))
		{
			List<Part> allColumns = queryAllColumnsService.queryAllColumns();
			request.setAttribute("allColumns", allColumns);
			return mapping.findForward("add_Column_page");//add_column.jsp
		}
		return null;
		
		
		//return mapping.findForward("rightFrame");
		
	}
}