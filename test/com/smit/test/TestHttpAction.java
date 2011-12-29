package com.smit.test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;


public class TestHttpAction extends MappingDispatchAction{

	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.getWriter().println("success recive msg!");
		return null;
	}
}
