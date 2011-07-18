package com.smit.web.analysis;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.smit.service.UserAnalysisService;
import com.smit.util.TripleDESHelper;
import com.smit.vo.UserAnalysis;

public class UserAnalysisAction extends DispatchAction{

	private UserAnalysisService usService;
	
	public UserAnalysisService getUsService() {
		return usService;
	}

	public void setUsService(UserAnalysisService usService) {
		this.usService = usService;
	}

	public ActionForward addAnalysis(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			String encodeStr = request.getParameter("encodeStr");
			System.out.println(request.getRequestURI());
			System.out.println("recived msg is:"+encodeStr);
			TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFG");
			String param = helper.decode(encodeStr);
			System.out.println(param);
			parseXML(param);
			
			sendResponse(response,"200");
		}catch (Exception e){
			e.printStackTrace();
			sendResponse(response, "500");
		}
		
		return null;
	}

	private void sendResponse(HttpServletResponse response,String code) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		sb.append("<statusCode>"+code+"</statusCode>");
		sb.append("</global>");
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(sb.toString());
	}

	private void parseXML(String param) throws Exception {
		Document doc = DocumentHelper.parseText(param);	
		Element root  = doc.getRootElement();
		for(Iterator i = root.elementIterator();i.hasNext();){
			Element app = (Element) i.next();
			UserAnalysis us = new UserAnalysis();
			us.setApp_name(new String(app.elementText("appname").getBytes("gbk"),"utf-8"));
			us.setP_name(app.elementText("pname"));
			us.setVer_name(app.elementText("versionname"));
			us.setVer_code(app.elementText("versioncode"));
			us.setMachine_id(app.elementText("machineid"));
			us.setPermission(app.elementText("permission"));
			us.setInstall_time(app.elementText("installtime"));
			us.setRun_time(app.elementText("runtime"));
			us.setUninstall_time(app.elementText("uninstalltime"));
			
			usService.insert(us);
		}
	}
}
