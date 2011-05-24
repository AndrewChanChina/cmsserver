package com.smit.web;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
import com.smit.vo.TestOption;
import com.smit.web.control.action.Page;

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
	
	//add by luocheng 2011-04-26
	public ActionForward showLogPage(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String classType = request.getParameter("class");
		if(classType.equals("base")){
			Page page = new Page();
			page.setCount(page.pageCount());
			request.setAttribute("page", page);
			request.setAttribute("deviceID", "");
			return mapping.findForward("baseLog");
		}else if(classType.equals("detail")){
			Page page = new Page();
			page.setCount(page.pageCount());
			request.setAttribute("page", page);
			request.setAttribute("deviceID", "");
			return mapping.findForward("detail");
		}else if(classType.equals("order")){
			List<TestOption> list = new ArrayList();
			request.setAttribute("optionList", list);
			return mapping.findForward("order");	
		}else if(classType.equals("test")){
			return mapping.findForward("testOption");
		}
		return null;
	}
	
	//add by luocheng 2011-05-05
	public ActionForward showAuth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("class");
		if(type.equals("login")){
			return mapping.findForward("loginAuth");
		}else if(type.equals("reqAuth")){
			return mapping.findForward("requestAuth");
		}else if(type.equals("active")){
			return mapping.findForward("active");
		}else if(type.equals("product")){
			return mapping.findForward("product");
		}else if(type.equals("confirm")){
			return mapping.findForward("confirm");
		}
		return null;
	}
}
