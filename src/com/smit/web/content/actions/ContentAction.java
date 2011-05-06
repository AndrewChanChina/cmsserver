package com.smit.web.content.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.BeanUtils;

import com.smit.service.ColumnService;
import com.smit.service.ContentService;
import com.smit.service.IUserService;
import com.smit.util.Page;
import com.smit.util.ServiceException;
import com.smit.vo.Content;
import com.smit.vo.Part;
import com.smit.vo.User;
import com.smit.web.content.forms.ContentForm;

public class ContentAction extends DispatchAction {
	
	ActionMessages msgs = new ActionMessages();
	private ContentService contentService ;
	private ColumnService columnService;
	private IUserService userService;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return new ActionForward("content.do?op=list");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Part> parts = columnService.queryAllColumns();
		if(parts != null) {
			
			request.setAttribute("parts", parts);				
		}
		List contents = new ArrayList();
		if(request.getParameter("cid") != null){
			Content content = null;
			//User user = null;
			int id = Integer.parseInt(request.getParameter("cid"));
			if(contentService.findById(id) != null){
				content = contentService.findById(id);
				contents.add(0, content);
				
			}
			//if(userService.getUser(content.getAuthor_id()) != null){
				//user = userService.getUser(content.getAuthor_id());		
			//}
			if(columnService.queryByColumnId(id)!= null){
				Part part = columnService.queryByColumnId(id);
				contents.add(1,part.getTypename());
				
			}
			
			
		
		
		//contents.add(user.getUserName());
			request.setAttribute("contents", contents);  
		}
	
		
		return mapping.findForward("manager");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//DynaActionForm daf = (DynaActionForm)form;
		ContentForm contentForm = (ContentForm)form;
		
		//System.out.println(daf.get("content"));
		//int id = (Integer)daf.get("id");
		int id = contentForm.getId();
		Content content = null;
		Date date = new Date();
		try {
			if(id >0){
				 content = contentService.findById(id);		
				 
			
				 BeanUtils.copyProperties(contentForm,content);
				 contentService.update(content);
			}else {
				 content = new Content();		
			
				 content.setCreatetime((int)date.getTime());
				 BeanUtils.copyProperties(contentForm,content);
				 contentService.save(content);
			}
			request.setAttribute("url", "content.do?op=list");
			//contentService.saveorupdate(content);
			return mapping.findForward("success");			
		}catch(ServiceException e){
			e.printStackTrace();
			ActionMessage msg = new ActionMessage(e.getMessage());
			msgs.add("contentAction.save.failure", msg);
			this.saveMessages(request, msgs);
			request.setAttribute("url", "content.do?op=add");
			return mapping.findForward("fail");		
		}		
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer currentPage = (request.getParameter("pn")== null ||request.getParameter("pn") == "") ?1 : Integer.parseInt(request.getParameter("pn"));
		Integer pageSize = (request.getParameter("ps") == null ||request.getParameter("ps") == "") ? 20 : Integer.parseInt(request.getParameter("ps"));
		Integer pid = (request.getParameter("pid")== null || request.getParameter("pid")== "")?0:Integer.parseInt(request.getParameter("pid"));
		try {
			Page page = page = contentService.findAll(currentPage, pageSize,pid);	
				
		
			//System.out.println(page.getList().size());
			request.setAttribute("page", page);
			return mapping.findForward("list");
		}catch(ServiceException e){
			ActionMessage msg = new ActionMessage(e.getMessage());
			msgs.add("contentAction.list.failure",msg);
			this.saveMessages(request, msgs);
		
			return mapping.findForward("404");
			
		}
	
	
	
	
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int id = Integer.parseInt(request.getParameter("cid"));
		try {
			contentService.delete(id);
			request.setAttribute("url", "content.do?op=list");
			return mapping.findForward("success");
			
		}catch(ServiceException e){
			
			ActionMessage msg = new ActionMessage(e.getMessage());
			msgs.add("contentAction.save.failure", msg);
			this.saveMessages(request, msgs);
			request.setAttribute("url", "content.do?op=list");
			return mapping.findForward("fail");
			
		}
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    int id = Integer.getInteger(request.getParameter("cid"));
	    try {
	    	Content content = contentService.findById(id);
	    	request.setAttribute("contentBean", content);
	    	return mapping.findForward("/content_view.jsp");
	    }catch(ServiceException e){
	    	ActionMessage msg = new ActionMessage(e.getMessage());
	    	msgs.add("ContentAction.view.failure", msg);
	    	this.saveMessages(request, msgs);
	    	return mapping.findForward("fail");
	    }
		
	}
	
	
	public ActionForward check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	   // String[] ids = request.getParameterValues("ids");
	   // for(int i = 0;i < ids.length;i ++){
	    	//int id = Integer.parseInt(ids);
	  //  }
	    
	   // for(String id : ids){
		     String id = request.getParameter("cid");
	    	 try {
	 	    	Content content = contentService.findById(Integer.parseInt(id));
	 	    	content.setIsCheck(1);
	 	    	contentService.update(content);  
	 	   	    request.setAttribute("url", "content.do?op=list");
	 	   	 return mapping.findForward("success");
	 	    }catch(ServiceException e){
	 	    	ActionMessage msg = new ActionMessage(e.getMessage());
	 	    	msgs.add("ContentAction.view.failure", msg);
	 	    	this.saveMessages(request, msgs);
	 	       request.setAttribute("url", "content.do?op=list");
	 	    	return mapping.findForward("fail");
	 	    }
	    	
	    	
	    //}
	   
	    
	   
		
	}
	
	

}
