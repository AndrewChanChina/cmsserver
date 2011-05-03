package com.smit.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.IUserService;
import com.smit.service.push.IPushDataService;
import com.smit.util.Constants;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.web.form.PushDataForm;
import com.smit.web.form.RegisterForm;

public class CopyOfPushDataAction extends MappingDispatchAction {

	IUserService userService;
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// set some data
		return mapping.findForward("success");
	}
	/**
	 * register a user in 'developer' group
	 */
	public ActionForward developerRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try{
			RegisterForm loginForm = (RegisterForm)form;
			userService.regDeveloper(loginForm.getUsername(), 
					loginForm.getPassword(), 
					loginForm.getEmail(), 
					loginForm.getTel());
					
		}catch (Exception e){
			return mapping.findForward("fail");
		}
		return mapping.findForward("success");		
	}
	
	
	public ActionForward pushData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		PushDataForm pf = (PushDataForm)form;		
		
		
		try{
			IPushDataService ps = (IPushDataService)session.getAttribute(Constants.PUSH_CONNECTION);
			
			List<String> userList = new ArrayList<String>();
			userList.add("test2@smit/spark");
			userList.add("test2@smit/SMIT");
			
			boolean b = ps.isConnected();
			ps.sendPushDataFromUser(userList,
					false, pf.getCollapseKey(), pf.getTitle(),
					pf.getTicket(), pf.getUri(), pf.getMessage(), true);
			
		}catch (Exception e){			
			return mapping.findForward("fail");				
		}
		return mapping.findForward("success");
				
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	
}
