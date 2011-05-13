package com.smit.web.control.action;

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
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.SAXReader;

import com.smit.service.LogService;
import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;
import com.smit.web.control.form.DetailLogForm;
import com.smit.web.control.form.LogForm;

public class MultiFileUploadAction extends MappingDispatchAction{

	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	public ActionForward log(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		LogForm logForm = (LogForm) form;
		String filename = logForm.getUpload().getFileName();
		System.out.println(logForm.getUpload().getFileName());
		
		//String basePath = this.servlet.getServletContext().getRealPath(arg0)+"/";
		String basePath = "D:\\filedown\\";
		File file = new File(basePath);
		if(!(file.exists())){
			file.mkdirs();
		}
		FileOutputStream fos = null;
		InputStream is = null;
		try{
				//构建文件在服务器保存的路径
				//File file = new File(basePath,"WEB-INF/"+filename);
				fos = new FileOutputStream(basePath+filename);
				//获得输入流
				is = logForm.getUpload().getInputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
				fos.close();
				is.close();
				insertBaseLog(logForm);
				createRespXML(response,"200");
			}catch (Exception e){
				e.printStackTrace();
				try {
					createRespXML(response,"109");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			return null;
	}


	private void insertBaseLog(LogForm logForm) {
		BaseLog baseLog = new BaseLog();
		baseLog.setMachineID(logForm.getMachineID());
		baseLog.setMachineType(logForm.getMachineType());
		baseLog.setSysVersion(logForm.getSystemVersion());
		baseLog.setSoftwareVersion(logForm.getSoftwareVersion());
		baseLog.setTestStatus(logForm.getTestStatus());
		baseLog.setLogFile("d:\\filedown\\"+logForm.getUpload().getFileName());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		baseLog.setCreate_time(format.format(new Date()));
		logService.insertBaseLog(baseLog);
	}
	
	public ActionForward detailLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DetailLogForm df = (DetailLogForm) form;
		FormFile file = df.getUpload();
		System.out.println(file.getFileName());
		if(!(file.getFileName().endsWith(".xml"))){
			createRespXML(response, "输入文件必须为XML格式！");
			return null;
		}
		byte[] buffer = new byte[8192];
		String xml = null;
		int count = 0;
		InputStream is = file.getInputStream();
		while((count=is.read(buffer))>0){
			 is.read(buffer);
		}
		
		xml = new String(buffer);
		parseXML(xml);
		createRespXML(response,"200");
		return null;
	}


	private void parseXML(String xml) throws DocumentException {
		System.out.println(xml.toString());
		Document doc = DocumentHelper.parseText(xml.trim());
		Element root = doc.getRootElement();
		for(Iterator i =root.elementIterator();i.hasNext();){
			DetailLog log = new DetailLog();
			Element device = (Element) i.next();
			//log.setDeviceType(device.elementText("name"));
			log.setTestStatus(device.elementText("status"));
			log.setNote(device.elementText("note"));
			logService.insertDetailLog(log);
		}
	}


	private void createRespXML(HttpServletResponse response,String result) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		//Document resp = DOMDocumentFactory.getInstance().createDocument();
		//Element global = resp.addElement("global");
		//Element status = global.addElement("status");
		//status.setText("200");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		sb.append("<status>"+result+"</status>");
		sb.append("</global>");
		pw.println(sb.toString());
	}
	
}

	