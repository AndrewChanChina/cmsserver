package com.smit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.SmitLoginService;
import com.smit.util.Constants;

/**
 * smit login control
 * @author chenyzpower@gmail.com
 * @since 
 */
public class SmitLoginAction extends Action {

	private final static String LOGIN_SUC = "loginSuc";
	private SmitLoginService smitLoginService;
	
	public SmitLoginService getSmitLoginService() {
		return smitLoginService;
	}
	public void setSmitLoginService(SmitLoginService smitLoginService) {
		this.smitLoginService = smitLoginService;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String loginSuc = (String)session.getAttribute(LOGIN_SUC);

		LoginForm loginForm = (LoginForm)form;
		System.out.println(loginForm.getPasswd());
		System.out.println(loginForm.getUserName());
		if(smitLoginService.login(loginForm.getUserName(), loginForm.getPasswd())){
			session.setAttribute(LOGIN_SUC, Constants.SUCCESS);
			return mapping.findForward("sucess");
		} else 
		return mapping.findForward("login");
	}
	
}
