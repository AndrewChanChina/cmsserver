package com.smit.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.smit.service.IUserService;
import com.smit.service.push.IPushDataService;
import com.smit.util.Constants;
import com.smit.web.form.PushDataForm;

public class PushDataAction extends DispatchAction {

	private IUserService userService;
	private IPushDataService pushDataService;
	
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return new ActionForward("home.do");
	}	
	
	public ActionForward input(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		IPushDataService ps = (IPushDataService)session.getAttribute(Constants.PUSH_CONNECTION);
		ps.sendQueryResourceId("test2");
		return mapping.findForward("inputForm");
		
	}
	/**
	 * 给用户推送信息使用
	 */
	public ActionForward pushuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		PushDataForm pf = (PushDataForm)form;
		
		try{
			IPushDataService ps = (IPushDataService)session.getAttribute(Constants.PUSH_CONNECTION);
			
			List<String> userList = new ArrayList<String>();
			userList.add("test2@smit/spark");
			userList.add("test2@smit/SMIT");
			
			boolean bDelay = false;
			if( "yes".equalsIgnoreCase(pf.getIsDelay()) )
				bDelay = true;
			
			ps.sendPushDataFromUser(userList,
					bDelay, pf.getCollapseKey(), pf.getTitle(),
					pf.getTicket(), pf.getUri(), pf.getMessage(), true);
			
		}catch (Exception e){			
			return mapping.findForward("fail");				
		}
		response.sendRedirect("home.do");
		return null;
		//return mapping.findForward("success");
				
	}
	/**
	 * 给开发者推送信息用的
	 */
	public ActionForward pushdev(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		PushDataForm pf = (PushDataForm)form;
		return null;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setPushDataService(IPushDataService pushDataService) {
		this.pushDataService = pushDataService;
	}
	
	
	
}
