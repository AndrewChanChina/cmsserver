package com.smit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ISmitLoginService;
import com.smit.util.Constants;
import com.smit.web.form.SmitLoginForm;

/**
 * smit login control
 * @author chenyzpower@gmail.com
 * @since 
 */
public class SmitLoginAction extends MappingDispatchAction {
	
	private ISmitLoginService smitLoginService;
	
	public ISmitLoginService getSmitLoginService() {
		return smitLoginService;
	}
	public void setSmitLoginService(ISmitLoginService smitLoginService) {
		this.smitLoginService = smitLoginService;
	}
	

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String loginSuc = (String)session.getAttribute(Constants.LOGIN_SUC);

		SmitLoginForm loginForm = (SmitLoginForm)form;
		if(form == null){
			return mapping.findForward("login");
		}
		
		System.out.println(loginForm.getPasswd());
		System.out.println(loginForm.getUserName());
		if(smitLoginService.login(loginForm.getUserName(), loginForm.getPasswd())){
			session.setAttribute(Constants.LOGIN_SUC, Constants.SUCCESS);
			return mapping.findForward("sucess");
		} else 
		return mapping.findForward("login");
	}
	public ActionForward logout(ActionMapping mapping,ActionForm form,
		   HttpServletRequest request,HttpServletResponse response) 
           throws Exception {
 
		HttpSession session = request.getSession();
		session.setAttribute(Constants.LOGIN_SUC,null);
	    return mapping.findForward("login");
	}
	 

	
}
