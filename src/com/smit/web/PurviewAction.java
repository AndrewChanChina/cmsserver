package com.smit.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.IPurviewService;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.Group;
import com.smit.vo.Purview;
import com.smit.vo.User;
import com.smit.web.form.PurviewForm;
import com.smit.web.form.UserForm;

public class PurviewAction extends MappingDispatchAction {
	
	IPurviewService purviewService;

	public ActionForward listPurview(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {	 

		SmitPage pager = new SmitPage(WebUtil.getIntByRequestParament(
				request, SmitPage.pageNumberParameterName, 1));	
		int pageSize = 10; 		
		pager.setPageSize(pageSize);	
		pager.setUrl("listpurview.do?");

		try {
			List list = purviewService.listAll(pager);
			request.setAttribute("pb", pager);
			request.setAttribute("PurviewList", list);
		} catch (Exception e) {
			return mapping.findForward("fail");
		}  
		return mapping.findForward("showpurviewlist");
		
	}
	
	public ActionForward deletePurview(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {
		String id = request.getParameter("id");
		Purview g = new Purview();
		g.setId(Integer.valueOf(id));
		
		try{
			this.purviewService.delete(g);
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "can't delete user where id = " + id 
					+ "with error:" + e.getMessage());
			request.setAttribute("backUrl", "listuser.do");
			return mapping.findForward("fail");
		}
		return mapping.findForward("userright_purviewlist");
		
	}

	public ActionForward goNewPurview(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {
		try{
			PurviewForm pForm = new PurviewForm();		
			pForm.setHideId(WebUtil.setHideId2Session(request.getSession()));
			
			String id = request.getParameter("id");
			
			if(id != null){
				Purview g = purviewService.getPurview(Integer.valueOf(id));			
				BeanUtils.copyProperties(pForm, g);
			}			
			request.setAttribute("PurviewForm", pForm);
			
		}catch(Exception e){
			return mapping.findForward("fail");
		}
		
		return mapping.findForward("continue");
	}
	
	public ActionForward saveUpdatePurview(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {		
		
		PurviewForm pForm = (PurviewForm)form;
		if( WebUtil.isHideIdEqual(request.getSession(), pForm.getHideId()) == false){
			return mapping.findForward("fail");
		}
		
		Purview purview = new Purview();
		BeanUtils.copyProperties(purview, pForm);			
	
		try {
			if (pForm.getId()!=null && pForm.getId().isEmpty()==false ) {
				purview.setId(Integer.valueOf(pForm.getId()));
				purviewService.update(purview);
			} else {
				purviewService.save(purview);
			}
		} catch (Exception e) {

			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return mapping.findForward("userright_purviewlist");
		
	}
	
	public IPurviewService getPurviewService() {
		return purviewService;
	}

	public void setPurviewService(IPurviewService purviewService) {
		this.purviewService = purviewService;
	}

}
