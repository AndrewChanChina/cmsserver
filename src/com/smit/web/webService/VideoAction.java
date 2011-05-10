package com.smit.web.webService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.ColumnService;
import com.smit.service.collection.VideoService;
import com.smit.service.webService.IToXML;
import com.smit.service.webService.XmlWrap;
import com.smit.util.SmitPage;

public class VideoAction extends MappingDispatchAction{
	
	ActionMessages msgs = new ActionMessages();
	VideoService videoService;
	ColumnService columnService;

	public ActionForward video(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			VideoForm videoForm = (VideoForm)form;
			SmitPage smitPage = new SmitPage(1);
			smitPage.setPageSize(20);
			
			XmlWrap xmlObject = new XmlWrap();			
			
			List<IToXML> toXMLs = videoService.findByPartIdXMl(smitPage, 
					Integer.valueOf(videoForm.getColumnKey()));			
			xmlObject.setItems(toXMLs);
			
			request.setAttribute("xmlObject", xmlObject);
		} catch (Exception e) {
			return mapping.findForward("success");
		}
		return mapping.findForward("success");		
	}
	
	public ActionForward getVideoColumn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		try {			
			VideoForm videoForm = (VideoForm)form;
			XmlWrap xmlObject = null;
			if(videoForm.getColumnKey()==null 
					|| "null".equalsIgnoreCase(videoForm.getColumnKey()) ){
				xmlObject = columnService.queryRootChildren();
			}else{
				xmlObject = columnService.queryNextChildren(Integer.valueOf(videoForm.getColumnKey()));
			}
			request.setAttribute("xmlObject", xmlObject);
			return mapping.findForward("success");
			
		} catch (Exception e) {
			return mapping.findForward("success");
		}
	}
	
	public ActionForward addSysInfo2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {			
			XmlWrap xmlObject = columnService.queryRootChildren();
			request.setAttribute("xmlObject", xmlObject);
			return mapping.findForward("success");
			
		} catch (Exception e) {
			ActionMessage msg = new ActionMessage(e.getMessage());
			msgs.add("contentAction.list.failure",msg);
			this.saveMessages(request, msgs);
			
			return mapping.findForward("fail");
		}
				
	}

	public VideoService getVideoService() {
		return videoService;
	}

	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	public ColumnService getColumnService() {
		return columnService;
	}

	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}
	
	
	
}
