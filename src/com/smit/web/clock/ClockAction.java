package com.smit.web.clock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.clock.ClockService;
import com.smit.util.Constants;
import com.smit.vo.alarmclock.Clock;

public class ClockAction extends MappingDispatchAction {
	
	private ClockService clockService;

	public void setClockService(ClockService clockService) {
		this.clockService = clockService;
	}	

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("success");
	}
	
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Clock> clockList = clockService.getAllItems();
		request.setAttribute("clockList", clockList);
		return mapping.findForward("home");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		Clock clock = new Clock();
		clock.setId(Integer.valueOf(id));
		try{
			clockService.delete(clock);
			request.setAttribute("status", Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}
		
		return mapping.findForward("status");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ClockForm clockForm = (ClockForm)form;
		Clock c = new Clock();
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());
		
		try{
			clockService.save(c);
			request.setAttribute("status", Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}
		
		return mapping.findForward("status");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ClockForm clockForm = (ClockForm)form;
		Clock c = new Clock();
		c.setId(clockForm.getId());
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());
		
		try{
			clockService.update(c);
			request.setAttribute("status", Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}
		
		return mapping.findForward("status");
	}

}
