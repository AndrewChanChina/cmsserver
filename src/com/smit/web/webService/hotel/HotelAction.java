package com.smit.web.webService.hotel;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
// roomNum
public class HotelAction extends DispatchAction {
	
	public ActionForward hotelinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String roomNum = (String)request.getParameter("roomNum");
		String data = (String)request.getParameter("data");
		System.out.println(roomNum);
		System.out.println(data);
		response.getWriter().println("lkdlldladf");
		return null;
		//return mapping.findForward("hotelinfo");
	}
	
	public ActionForward roominfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String roomNum = (String)request.getParameter("roomNum");
		String data = (String)request.getParameter("data");
		System.out.println(roomNum);
		System.out.println(data);
		return mapping.findForward("roominfo");
	}

}
