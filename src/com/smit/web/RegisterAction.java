package com.smit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.IRegisterService;
import com.smit.vo.User;
import com.smit.web.form.RegisterForm;

public class RegisterAction extends Action {

	private IRegisterService registerService;	
	
	public IRegisterService getRegisterService() {
		return registerService;
	}
	
	public void setRegisterService(IRegisterService registerService) {
		this.registerService = registerService;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RegisterForm loginForm = (RegisterForm)form;		
				
		if(registerService.register(loginForm.getUsername(), 
				loginForm.getPassword(), 
				loginForm.getEmail(), 
				loginForm.getTel())) {			
			return mapping.findForward("sucess");
		} else {
			return mapping.findForward("fail");
		}
	}
}
