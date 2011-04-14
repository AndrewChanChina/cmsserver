package com.smit.web.sysinfo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.SysInfoService;

public class EnableSysInfoAction extends Action {
	private SysInfoService enableSysInfoService;
	   
	public void setEnableSysInfoService(SysInfoService enableSysInfoService)
	{
		this.enableSysInfoService = enableSysInfoService;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id=request.getParameter("id");
		String enable = request.getParameter("enable");
		Integer intId = Integer.valueOf(id,10);
		boolean b = true;
		if(enable.equalsIgnoreCase("true"))
		{
			b = true;
		}
		else
		{
			b = false;
		}
		if(enableSysInfoService.enableSysInfo(intId, b))
		{
			return mapping.findForward("reload");
		}
		else
		{
			return mapping.findForward("fail");
		}
	}
}
