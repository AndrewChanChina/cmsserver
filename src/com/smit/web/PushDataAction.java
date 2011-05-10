package com.smit.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.IUserService;
import com.smit.service.push.IPushDataService;
import com.smit.util.Constants;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.User;
import com.smit.web.form.PushDataForm;
import com.smit.web.form.RegisterForm;
import com.smit.web.form.SmitLoginForm;

public class PushDataAction extends MappingDispatchAction {

	private IUserService userService;
	private IPushDataService pushDataService;
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// set some data
		return mapping.findForward("success");
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String loginSuc = (String)session.getAttribute(Constants.LOGIN_SUC);

		SmitLoginForm loginForm = (SmitLoginForm)form;		
		
		System.out.println(loginForm.getPasswd());
		System.out.println(loginForm.getUserName());
		try{
			if(userService.login(loginForm.getUserName(), loginForm.getPasswd())){
				// save some login information
				session.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);
				session.setAttribute(Constants.CURUSERNAME, loginForm.getUserName());
				response.sendRedirect("home.do");
				return null;
				//return mapping.findForward("success");
			} else if(pushDataService.login(Constants.PUSH_HOST, 
					loginForm.getUserName(), loginForm.getPasswd())) 
			{
				// login smack success
				session.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);
				session.setAttribute(Constants.CURUSERNAME, loginForm.getUserName());
				session.setAttribute(Constants.PUSH_CONNECTION, pushDataService);
				User u = userService.findUserByName(loginForm.getUserName());
				if(u != null){
					session.setAttribute(Constants.CUR_USER_ID, u.getId());
				}				
				return mapping.findForward("success");
			} else {
				throw new Exception("dd");
			}
			
		}catch (Exception e){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("ee42452"));
			this.saveErrors(request, errors);
			return mapping.findForward("fail");				
		}		
				
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
	public void setPushDataService(IPushDataService pushDataService) {
		this.pushDataService = pushDataService;
	}
	
	
	
}
