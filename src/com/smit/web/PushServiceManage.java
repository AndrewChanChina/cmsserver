package com.smit.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.smit.service.push.IPushManageService;
import com.smit.util.Constants;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.PushService;
import com.smit.web.form.PushServiceForm;

public class PushServiceManage extends DispatchAction {

	IPushManageService pushManage;
		
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer pageIndex = (Integer)request.getSession().getAttribute(SmitPage.CUR_PAGE);
		if(pageIndex == null)
			pageIndex = 1;
		SmitPage pager = new SmitPage(WebUtil.getIntByRequestParament(
				request, SmitPage.pageNumberParameterName, pageIndex));	
		int pageSize = 2; 		
		pager.setPageSize(pageSize);	
		pager.setUrl("pushservicemanage.do?opt=list&");
		
		try { 
			List list = pushManage.listAll(pager);
			request.setAttribute("pb", pager);
			request.setAttribute("pushServiceList", list);
		} catch (Exception e) {
			return mapping.findForward("fail");
		} 		
		return mapping.findForward("listPage");		
	}	
	
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		try{
			this.saveToken(request); // avoid repeat submit form data	
			
			String id = request.getParameter("id");
			
			if(id != null){
				PushService g = pushManage.getById(Integer.valueOf(id));
				request.setAttribute("pushService", g);
			}
			
		}catch(Exception e){
			return mapping.findForward("fail");
		}
		
		return mapping.findForward("managePage");
	}	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(this.isTokenValid(request, true) == false){
			return mapping.findForward("repeatForm"); // repeat submit form data
		}
		try{
			// TODO keep user name to be unique, modify database setting			
			PushServiceForm sForm = (PushServiceForm)form;
			
			if(sForm.getId()==null){
				Integer userId = (Integer)request.getSession().getAttribute(Constants.CUR_USER_ID);
				PushService ps = new PushService();
				
				ps.setUserId(userId);
				ps.setServiceName(sForm.getServiceName());	
				
				pushManage.save(ps);
			}else{				
				PushService ps = pushManage.getById(sForm.getId());
				ps.setServiceName(sForm.getServiceName());
				pushManage.update(ps);
			}			
			
		}catch(Exception e){
			return mapping.findForward("fail");
		}
		response.sendRedirect("pushservicemanage.do?opt=list");
		return null;
		//return mapping.findForward("back2ListPage");		
	}	
	
	/**
	 * manage push Service 
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		PushService g = new PushService();
		g.setId(Integer.valueOf(id));
		
		try{
			this.pushManage.delete(g);
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "can't delete where id = " + id 
					+ "with error:" + e.getMessage());
			request.setAttribute("backUrl", "pushservicemanage.do?opt=list");
			return mapping.findForward("fail");
		}
		return mapping.findForward("back2ListPage");		
	}

	/**
	 * prepare data then go to send data page
	 */
	public ActionForward pushData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			List list = pushManage.listAll(null);
			request.setAttribute("pushServiceList", list);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		//response.sendRedirect("home.do");
		return mapping.findForward("sendData");
	}
	
	public void setPushManage(IPushManageService pushManage) {
		this.pushManage = pushManage;
	}
}
