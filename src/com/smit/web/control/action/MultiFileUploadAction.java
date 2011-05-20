package com.smit.web.control.action;

import java.io.BufferedInputStream;
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
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.smit.service.ProductControlService;
import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;
import com.smit.vo.Device;
import com.smit.vo.TestOption;
import com.smit.web.control.form.DetailLogForm;
import com.smit.web.control.form.LogForm;

public class MultiFileUploadAction extends MappingDispatchAction{

	private LogService logService;
	private ProductControlService service;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setService(ProductControlService service) {
		this.service = service;
	}
	
	public ActionForward showAddBaseLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Page page = new Page();
		request.setAttribute("page", page);
		return mapping.findForward("showAddBase");
	}

	public ActionForward showAddDetailLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("showAddDetail");
	}
	
	public ActionForward queryDetailLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String checkID = request.getParameter("checkID");
		List<Device> list = service.getDevice(checkID);
		//sendDetailLog(response, list);
		if(list.size()>0){
			Set<DetailLog> logs = list.get(0).getLogs();
			Page page = new Page();
			page.setCurrentPage(1);
			page.setTotalRecord(list.size());
			request.setAttribute("devices", logs);
			request.setAttribute("page", page);
		}
		return mapping.findForward("queryDetail");
	}

	private void sendDetailLog(HttpServletResponse response, List<Device> list) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(list.size()>0){
			Set<DetailLog> detailLog = list.get(0).getLogs();
			for(Iterator<DetailLog> i =detailLog.iterator();i.hasNext();){
				DetailLog log = i.next();
				sb.append("<device>");
				sb.append("<name>"+log.getTestOption().getName()+"</name>");
				sb.append("<testStatus>"+log.getTestStatus()+"</testStatus>");
				sb.append("<note>"+log.getNote()+"</note>");
				sb.append("<createTime>"+log.getCreate_time()+"</createTime>");
				sb.append("</device>");
			}
		}else{
			sb.append("<statusCode>103</statusCode>");
		}
		sb.append("</global>");
		response.getWriter().println(sb.toString());
	}
	public ActionForward queryBaseLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String checkID = request.getParameter("checkID");
		String currentStr = request.getParameter("currentPage");
		System.out.println("当前是第" + currentStr +"页");
//		if(Integer.parseInt(currentStr)>0){
//			
//		}
		List<Device> list = service.getDevice(checkID);
		Page page = new Page();
		if(list.size()>0){
			Set<BaseLog> logs = list.get(0).getBaseLogs();
			//Collections.
			page.setCurrentPage(1);
			page.setTotalRecord(logs.size());
			page.setCount(page.pageCount());
			
			request.setAttribute("baseLogs", logs);
		}
		request.setAttribute("checkID", checkID);
		request.setAttribute("page", page);
		return mapping.findForward("queryBase");
	}

	private void sendXMLLogInfo(HttpServletResponse response, List<Device> list)
			throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		if(list.size()>0){
			Set<BaseLog> baseLog = list.get(0).getBaseLogs();
			for(Iterator<BaseLog> i =baseLog.iterator();i.hasNext();){
				BaseLog log = i.next();
				sb.append("<log>");
				sb.append("<machineID>"+log.getMachineID()+"</machineID>");
				sb.append("<machineType>"+log.getMachineType()+"</machineType>");
				sb.append("<systemVersion>"+log.getSysVersion()+"</systemVersion>");
				sb.append("<softwareVersion>"+log.getSoftwareVersion()+"</softwareVersion>");
				sb.append("<testStatus>"+log.getTestStatus()+"</testStatus>");
				sb.append("<createTime>"+log.getCreate_time()+"</createTime>");
				sb.append("</log>");
			}
		}else{
			sb.append("<statusCode>102</statusCode>");
		}
		sb.append("</global>");
		response.getWriter().println(sb.toString());
	}
	
	public ActionForward log(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		LogForm logForm = (LogForm) form;
		FormFile  ff = logForm.getUpload();

		try{
			if(ff!=null&&!(ff.getFileName().equals(""))){
			//String basePath = this.servlet.getServletContext().getRealPath(arg0)+"/";
				String basePath = "D:\\filedown\\";
				File file = new File(basePath);
				if(!(file.exists())){
					file.mkdirs();
				}
				FileOutputStream fos = null;
				InputStream is = null;
				//构建文件在服务器保存的路径
				fos = new FileOutputStream(basePath+ff.getFileName());
				//获得输入流
				is = logForm.getUpload().getInputStream();
				byte[] buffer = new byte[8192];
				int count = 0;
				while((count = is.read(buffer))>0){
				fos.write(buffer,0,count);
			}
			fos.close();
			is.close();
		}
			insertBaseLog(logForm);
			createRespXML(response,"200");
		}catch (Exception e){
			e.printStackTrace();
			try {
				createRespXML(response,"102");
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
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		baseLog.setCreate_time(format.format(new Date()));
		List<Device> list = service.getDevice(logForm.getCheckID());
		baseLog.setDevice(list.get(0));
		logService.insertBaseLog(baseLog);
	}
	
	public ActionForward detailLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DetailLogForm df = (DetailLogForm) form;
		FormFile file = df.getUpload();
		byte[] buffer = new byte[8192];
		String xml = null;
		try{
			if(file != null){
				System.out.println(file.getFileName());
				if(!(file.getFileName().endsWith(".xml"))){
					createRespXML(response, "输入文件必须为XML格式！");
					return null;
				}
				int count = 0;
				InputStream is = file.getInputStream();
				while((count=is.read(buffer))>0){
					 is.read(buffer);
				}
				xml = new String(buffer);
			}else{
				xml = request.getParameter("uploadFile");
			}
			parseXML(xml);
			createRespXML(response,"200");
		}catch (Exception e){
			e.printStackTrace();
			createRespXML(response,"103");
		}
		return null;
	}

	private void parseXML(String xml) throws DocumentException {
		System.out.println(xml.toString());
		Document doc = DocumentHelper.parseText(xml.trim());
		Element root = doc.getRootElement();
		for(Iterator i =root.elementIterator();i.hasNext();){
			DetailLog log = new DetailLog();
			Element device = (Element) i.next();
			String name = device.elementText("name");
			TestOption option = service.getOption(name);
			
			log.setTestOption(option);
			log.setTestStatus(device.elementText("status"));
			log.setNote(device.elementText("note"));
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			log.setCreate_time(format.format(new Date()));
			logService.insertDetailLog(log);
		}
	}

	private void createRespXML(HttpServletResponse response,String result) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<global>");
		sb.append("<statusCode>"+result+"</statusCode>");
		sb.append("</global>");
		pw.println(sb.toString());
	}
	
}

	