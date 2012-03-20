package com.smit.web.webService.hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
// roomNum
public class HotelAction extends DispatchAction {
	
	public ActionForward hotelinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String roomNum = (String)request.getParameter("roomNum");
		return mapping.findForward("hotelinfo");
	}
	
	public ActionForward roominfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String roomNum = (String)request.getParameter("roomNum");
		return mapping.findForward("roominfo");
	}

}
