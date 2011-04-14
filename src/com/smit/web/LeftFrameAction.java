package com.smit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LeftFrameAction extends Action {
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
			return mapping.findForward("column_tree_page");
		}
		return null;
	}
}