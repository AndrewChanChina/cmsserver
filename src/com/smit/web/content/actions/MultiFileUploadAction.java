package com.smit.web.content.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.SAXReader;

import com.smit.service.LogService;
import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;
import com.smit.web.form.DetailLogForm;
import com.smit.web.form.LogForm;

public class MultiFileUploadAction extends MappingDispatchAction{

	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	public ActionForward log(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogForm logForm = (LogForm) form;
		String filename = logForm.getUpload().getFileName();
		System.out.println(logForm.getUpload().getFileName());
		//SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd");
		//String basePath = format.format(new Date());
		//String basePath = this.servlet.getServletContext().getRealPath(arg0)+"/";
		String basePath = "D:\\filedown\\";
		FileOutputStream fos = null;
		InputStream is = null;
		String result = "success";
		String xml = null;
		try{
				//构建文件在服务器保存的路径
				File file = new File(basePath,"WEB-INF/"+filename);
				fos = new FileOutputStream(basePath+filename);
				//获得输入流
				is = logForm.getUpload().getInputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
			}catch (Exception e){
					e.printStackTrace();
					result = "fail:" + e.toString();
			}finally{
					fos.close();
					is.close();
			}
			
			BaseLog baseLog = new BaseLog();
			baseLog.setMachineID(logForm.getMachineID());
			baseLog.setMachineType(logForm.getMachineType());
			baseLog.setSysVersion(logForm.getSystemVersion());
			baseLog.setSoftwareVersion(logForm.getSoftwareVersion());
			baseLog.setTestStatus(logForm.getTestStatus());
			logService.insertBaseLog(baseLog);
			//response.getWriter().println(result);
			createXML(response);
			return null;
	}
	
	public ActionForward detailLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DetailLogForm df = (DetailLogForm) form;
		FormFile file = df.getUpload();
		System.out.println(file.getFileName());
		byte[] buffer = new byte[8192];
		String xml = null;
		int count = 0;
		InputStream is = file.getInputStream();
		while((count=is.read(buffer))>0){
			 is.read(buffer);
		}
		
		xml = new String(buffer);
		System.out.println(xml);
		DetailLog log = new DetailLog();
		//Document doc = DocumentHelper.parseText(xml);
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(new ByteArrayInputStream(buffer));
		Element root = doc.getRootElement();
		for(Iterator i =root.elementIterator();i.hasNext();){
			Element device = (Element) i.next();
			log.setDeviceType(device.elementText("name"));
			log.setTestStatus(device.elementText("status"));
			log.setNote(device.elementText("note"));
			logService.insertDetailLog(log);
		}
		
		createXML(response);
		return null;
	}


	private void createXML(HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		//Document resp = DOMDocumentFactory.getInstance().createDocument();
		//Element global = resp.addElement("global");
		//Element status = global.addElement("status");
		//status.setText("200");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		sb.append("<status>200</status>");
		sb.append("</global>");
		pw.println(sb.toString());
	}
	
}

	