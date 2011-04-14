package com.smit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MainFrameAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
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
}
