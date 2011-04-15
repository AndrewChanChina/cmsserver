package com.smit.web.column;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.ColumnService;
import com.smit.service.SysInfoService;
import com.smit.web.sysinfo.AddSysInfoForm;

public class AddColumnAction extends Action {
	private ColumnService addColumnService;
	   
	public void setAddColumnService(ColumnService addColumnService)
	{
		this.addColumnService = addColumnService;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddColumnForm addColumnForm = (AddColumnForm)form;
		String parentID = addColumnForm.getParentID();
		String classToAdd = addColumnForm.getClassToAdd();
		String path = addColumnForm.getPath();
		System.out.println("parentID: " + parentID);
		System.out.println("classToAdd: " + classToAdd);
		System.out.println("path: " + path);
		if(classToAdd.isEmpty())
		{
			System.out.println("classToAdd should not be empty");
			return null;
		}
		if(addColumnService.addColumn(parentID, classToAdd, path))
		{
			return mapping.findForward("reload");
		}
		else
		{
			//return mapping.findForward("fail");
		}
		return null;
	}
}
