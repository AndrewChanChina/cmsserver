package com.smit.web;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.IGroupManagerService;
import com.smit.service.IPurviewService;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.Group;
import com.smit.vo.Purview;
import com.smit.web.form.GroupForm;

public class GroupAction extends MappingDispatchAction {
	
	private final static String METHOD_PARAM = "show";
	private final static String METHOD_DEFAULT = "grouplist";
	private final static String METHOD_USER	= "userlist";
	private IGroupManagerService groupManager;
	private IPurviewService purviewService;
	
	public ActionForward groupList(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {	 

		SmitPage pager = new SmitPage(WebUtil.getIntByRequestParament(
				request, SmitPage.pageNumberParameterName, 1));	
		int pageSize = 10; 		
		pager.setPageSize(pageSize);	
		pager.setUrl("grouplist.do?");

		try {
			List groupList = groupManager.listAllGroups(pager);
			request.setAttribute("pb", pager);
			request.setAttribute("grouplist", groupList);
		} catch (Exception e) {
			return mapping.findForward("fail");
		}  
		return mapping.findForward("showgrouplist");
		
	}
	
	public ActionForward saveUpdateGroup(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {		
		
		GroupForm groupForm = (GroupForm)form;
		
		if( WebUtil.isHideIdEqual(request.getSession(), groupForm.getHideId()) == false){
			return mapping.findForward("fail");
		}
		
		java.util.Date today = new java.util.Date();
		Group g = new Group();
		g.setCreatetime( new java.sql.Timestamp(today.getTime()) );
		g.setGroupName(groupForm.getGroupName());
		g.setSortRank(groupForm.getSortRank());
		
		for(String id : groupForm.getPurviewids()){
			Purview p = new Purview();
			p.setId(Integer.valueOf(id));
			g.getPurviews().add(p);
		}
	
		try {
			if (groupForm.getId()!=null && groupForm.getId().isEmpty()==false ) {
				g.setId(Integer.valueOf(groupForm.getId()));
				groupManager.update(g);
			} else {
				groupManager.save(g);
			}
		} catch (Exception e) {

			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return mapping.findForward("userright_grouplist");
		
	}
	
	public ActionForward deleteGroup(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {
		String id = request.getParameter("id");
		Group g = new Group();
		g.setId(Integer.valueOf(id));
		
		try{
			this.groupManager.delete(g);
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "can't delete group where id = " + id 
					+ "with error:" + e.getMessage());
			request.setAttribute("backUrl", "grouplist.do");
			return mapping.findForward("fail");
		}
		return mapping.findForward("userright_grouplist");
		
	}
	
	public ActionForward goNewGroup(ActionMapping mapping,ActionForm form,
			   HttpServletRequest request,HttpServletResponse response) 
	           throws Exception {
		try{
			GroupForm groupForm = new GroupForm();
			groupForm.setHideId(WebUtil.setHideId2Session(request.getSession()));
			
			String id = request.getParameter("id");
			
			if(id != null){
				Group g = groupManager.getGroup(Integer.valueOf(id));
				groupForm.setGroupName(g.getGroupName());
				groupForm.setId(String.valueOf( g.getId()) );
				StringBuilder sb = new StringBuilder();
				Set<Purview> set = g.getPurviews();
				for(Purview p : set){
					sb.append(p.getId()).append(",");
				}
				String[] ids = sb.toString().split(",");
				groupForm.setPurviewids(ids);
				
			}
			List purviews = purviewService.listAll(null);
			request.setAttribute("purviewlist", purviews);
			request.setAttribute("groupForm", groupForm);
			
		} catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return mapping.findForward("continue");
	}
	
	public IGroupManagerService getGroupManager() {
		return groupManager;
	}
	public void setGroupManager(IGroupManagerService groupManager) {
		this.groupManager = groupManager;
	}
	public IPurviewService getPurviewService() {
		return purviewService;
	}
	public void setPurviewService(IPurviewService purviewService) {
		this.purviewService = purviewService;
	}
	
	
}
