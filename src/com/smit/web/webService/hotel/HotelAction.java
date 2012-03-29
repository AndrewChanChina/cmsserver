package com.smit.web.webService.hotel;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.smit.dao.PushService2UserDao;
import com.smit.util.Constants;
import com.smit.vo.PushService2User;
// roomNum
public class HotelAction extends DispatchAction {
	
	private PushService2UserDao pushService2UserDao;
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.sendRedirect("hotel.do?opt=home");
		return null;
	}

	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		return mapping.findForward("home");
	}
	
	public ActionForward hotelinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String roomNum = (String)request.getParameter("roomNum");
		String data = (String)request.getParameter("data");
		System.out.println(roomNum);
		System.out.println(data);
		response.getWriter().println("lkdlldladf");		
		return mapping.findForward("hotelinfo");
	}
	
	public ActionForward roominfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String roomNum = (String)request.getParameter("roomNum");
		String data = (String)request.getParameter("data");
		System.out.println(roomNum);
		System.out.println(data);
		return mapping.findForward("roominfo");
	}
	
	// 提交酒店信息，和注册了那些服务
	public ActionForward pushServiceInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String roomNum = (String)request.getParameter("roomNum");
		String pushId = (String)request.getParameter("pushid");
		String pushServiceId = (String)request.getParameter("pushserviceid");
		System.out.println(roomNum);
		System.out.println(pushId);
		System.out.println(pushServiceId);
		
		PushService2User ps = new PushService2User();
		ps.setPushId(pushId);
		ps.setRoomNum(roomNum);
		ps.setServiceId(pushServiceId);
		
		pushService2UserDao.save(ps);
		
		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}

	public void setPushService2UserDao(PushService2UserDao pushService2UserDao) {
		this.pushService2UserDao = pushService2UserDao;
	}

}
