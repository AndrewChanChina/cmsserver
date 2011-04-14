package com.smit.web.sysinfo;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smit.service.SysInfoService;
import com.smit.vo.SysInfo;


public class UpdateSysInfoAction extends Action {
	
	private SysInfoService updateSysInfoService;
	   
	public void setUpdateSysInfoService(SysInfoService updateSysInfoService) {
		this.updateSysInfoService = updateSysInfoService;
    }
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String itemListToUpdate = request.getParameter("itemList");

		if(itemListToUpdate != null && ! itemListToUpdate.equalsIgnoreCase("null"))
		{
			ArrayList<SysInfo> list = parseSysInfoList(itemListToUpdate);
			if(updateSysInfoService.updateSysInfo(list))
			{
				return mapping.findForward("reload");
			}
		}
		return mapping.findForward("fail");
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
