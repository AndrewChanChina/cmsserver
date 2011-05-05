package com.smit.web.webService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.webService.IToXML;
import com.smit.service.webService.Object2Xml;
import com.smit.service.webService.VideoItem;

public class VideoAction extends MappingDispatchAction{

	public ActionForward video(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			
			Object2Xml xmlObject = new Object2Xml();
			VideoItem vi = new VideoItem();
			vi.setName("gong fu");
			vi.setDescription("a great movie");
			List<String> urls = new ArrayList<String>();
			urls.add("www.baidu.com/uu.flv");
			urls.add("www.baidu.com/bb.flv");
			vi.setUrls(urls);
			
			List<IToXML> toXMLs = new ArrayList<IToXML>();
			toXMLs.add(vi);
			
			xmlObject.setToXMLs(toXMLs);
			request.setAttribute("xmlObject", xmlObject);
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
		return mapping.findForward("success");		
	}
	
	public ActionForward addSysInfo2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("success");		
	}
}
