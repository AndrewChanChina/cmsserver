package com.smit.web.sysinfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.SysInfoService;
import com.smit.vo.SysInfo;

public class SysInfoAction extends MappingDispatchAction {

	private SysInfoService sysInfoService;

	public void setSysInfoService(SysInfoService sysInfoService) {
		this.sysInfoService = sysInfoService;
	}

	public ActionForward addSysInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddSysInfoForm addSysInfoForm = (AddSysInfoForm) form;
		String keyToAdd = addSysInfoForm.getSysInfoKey();
		String valueToAdd = addSysInfoForm.getSysInfoValue();
		System.out.println(keyToAdd);
		System.out.println(valueToAdd);
		if (sysInfoService.addSysInfo(keyToAdd, valueToAdd)) {
			List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
			request.setAttribute("allSysInfos", allSysInfos);
			return mapping.findForward("reload");
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward deleteSysInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idToDelete = request.getParameter("id");
		String idListToDelete = request.getParameter("idList");

		System.out.println("We will delete the SysInfo: id = " + idToDelete);
		if (idToDelete != null && !idToDelete.equalsIgnoreCase("null")) {
			if (sysInfoService.deleteSysInfo(idToDelete)) {
				List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
				request.setAttribute("allSysInfos", allSysInfos);
				return mapping.findForward("reload");
			}
		}
		if (idListToDelete != null && !idListToDelete.equalsIgnoreCase("null")) {
			ArrayList<String> list = parseIdList(idListToDelete);
			if (sysInfoService.deleteSysInfo(list)) {
				List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
				request.setAttribute("allSysInfos", allSysInfos);
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
	}

	public ActionForward enableSysInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String enable = request.getParameter("enable");
		Integer intId = Integer.valueOf(id, 10);
		boolean b = true;
		if (enable.equalsIgnoreCase("true")) {
			b = true;
		} else {
			b = false;
		}
		if (sysInfoService.enableSysInfo(intId, b)) {
			List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
			request.setAttribute("allSysInfos", allSysInfos);
			return mapping.findForward("reload");
		} else {
			return mapping.findForward("fail");
		}
	}
	
	
	public ActionForward updateSysInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String itemListToUpdate = request.getParameter("itemList");

		if(itemListToUpdate != null && ! itemListToUpdate.equalsIgnoreCase("null"))
		{
			ArrayList<SysInfo> list = parseSysInfoList(itemListToUpdate);
			if(sysInfoService.updateSysInfo(list))
			{
				List<SysInfo> allSysInfos = sysInfoService.queryAllSysInfo();
				request.setAttribute("allSysInfos", allSysInfos);
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
	}

	ArrayList<String> parseIdList(String idList) {
		String tmpIdList = idList;
		int first = -1;
		ArrayList<String> list = new ArrayList<String>();
		while ((first = tmpIdList.indexOf("_")) != -1) {
			String idStr = tmpIdList.substring(0, first);
			list.add(idStr);

			tmpIdList = tmpIdList.substring(first + 1);
		}
		return list;
	}
	
	private ArrayList<SysInfo> parseSysInfoList(String itemListToUpdate)
	{
		ArrayList<SysInfo> list = new ArrayList<SysInfo>();
		//
		String tmpIdList = itemListToUpdate;
		int first = -1;
		while((first = tmpIdList.indexOf("_")) != -1)
		{
			String oneItem = tmpIdList.substring(0, first);
			SysInfo info = parseOneItem(oneItem);
			list.add(info);
			
			tmpIdList = tmpIdList.substring(first+1);
		}
		return list;
	}
	
	private SysInfo parseOneItem(String oneItem)
	{
		SysInfo info = new SysInfo();
		String tmpContentList = oneItem;
		int first = -1;
		int count = 0;
		while((first = tmpContentList.indexOf("^")) != -1)
		{
			String content = tmpContentList.substring(0, first);
			switch(count)
			{
			case 0:
				Integer intId = Integer.valueOf(content,10);
				info.setId(intId);
				break;
			case 1:
				info.setInfo_key(content);
				break;
			case 2:
				info.setInfo_value(content);
				break;
			case 3:
				if(content.equalsIgnoreCase("on"))
					info.setInfo_state("Y");
				else
					info.setInfo_state("N");
				break;
			default:
				break;
			}
			count ++;
			tmpContentList = tmpContentList.substring(first+1);
		}
		return info;
	}
}
