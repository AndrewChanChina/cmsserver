package com.smit.web.webService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.collection.VideoService;
import com.smit.service.webService.IToXML;
import com.smit.service.webService.Object2Xml;
import com.smit.service.webService.VideoItem;

public class VideoAction extends MappingDispatchAction{
	
	VideoService videoService;

	public ActionForward video(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			
			Object2Xml xmlObject = new Object2Xml();			
			
			List<IToXML> toXMLs = videoService.findByPartIdXMl(null, 1);			
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

	public VideoService getVideoService() {
		return videoService;
	}

	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}
	
	
}
