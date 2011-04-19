package com.smit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.SysInfoService;

public class AddSysInfoAction extends Action {
	private SysInfoService addSysInfoService;
	   
		public void setAddSysInfoService(SysInfoService addSysInfoService) {
			this.addSysInfoService = addSysInfoService;
	    }
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddSysInfoForm addSysInfoForm = (AddSysInfoForm)form;
		String keyToAdd = addSysInfoForm.getSysInfoKey();
		String valueToAdd = addSysInfoForm.getSysInfoValue();
		System.out.println(keyToAdd);
		System.out.println(valueToAdd);
		if(addSysInfoService.addSysInfo(keyToAdd, valueToAdd))
		{
			return mapping.findForward("reload");
		}
		else
		{
			return mapping.findForward("fail");
		}
	}
}
