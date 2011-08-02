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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.smit.service.ColumnService;
import com.smit.service.collection.VideoService;
import com.smit.service.webService.IToXML;
import com.smit.service.webService.XmlWrap;
import com.smit.util.SmitPage;
import com.smit.vo.Video;

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
	
	//发送各类别最新的一个视频，不区分优酷土豆，一次最多发送20条；
	public ActionForward sendLatestVideos(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			response.setContentType("text/xml");
			response.setCharacterEncoding("utf-8");
			List<Object[]> videos = videoService.getLatestVideos();
			StringBuffer sb = new StringBuffer();
			sb.append("<xml>");
			sb.append("<items>");
			for(Object[] obj:videos){
				sb.append("<item>");
				sb.append("<name>"+obj[0]+"</name>");
				sb.append("<pictures><picture>"+obj[1]+"</picture></pictures>");
				sb.append("<urls><url>"+obj[2]+"</url></urls>");
				String des = (String) obj[3];
				if("".equals(des)||null == des||"null".equals(des)){
					System.out.println(des);
					sb.append("<description></description>");
				}else{
					sb.append("<description>"+ getDes(des)+"</description>");
				}
				String time = (String) obj[4];
				if("".equals(time)||null == time){
					sb.append("<time></time>");
				}else{
					sb.append("<time>"+time+"</time>");
				}
				sb.append("</item>");
			}
			sb.append("</items>");
			sb.append("</xml>");
			response.getWriter().println(sb.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public String getDes(String s) throws Exception{
		Document doc = DocumentHelper.parseText("<root>" + s +"</root>");
		Element root = doc.getRootElement();
		List<Element> pEles = root.elements("p");
		String des = "";
		for(Element e:pEles){
			String text = e.getTextTrim();
			if(!"".equals(text)&&null!=text){
				des = text;
			}
		}
		return des;
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
