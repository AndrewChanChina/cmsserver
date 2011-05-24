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
		String deviceID = request.getParameter("deviceID");
		String currentStr = request.getParameter("currentPage");
		String type = request.getParameter("type");
		
		if(deviceID.matches("\\d+")){
			Device device = service.findById(Integer.parseInt(deviceID));
			List logs = null;
			getLogs(request, device, currentStr, type, logs, "detail");
		}else{
			Page page = new Page();
			request.setAttribute("page", page);
			request.setAttribute("deviceID", deviceID);
		}
		return mapping.findForward("queryDetail");
	}

	private void getLogs(HttpServletRequest request, Device device,
			String currentStr, String type,
			List logs, String logType) {
		Page page = new Page();
		if(null != device){
			setTotalRecord(logType, page, device);
			if("pre".equals(type)){
				logs = getPrePage(currentStr, logs, logType, page, device);
			}else if("next".equals(type)){
				logs = getNextPage(currentStr, logs, logType, page, device);
			}else{
				logs = getPage(logs, logType, page, device);
			}
		}
		request.setAttribute("logs", logs);
		request.setAttribute("deviceID", device.getId());
		request.setAttribute("page", page);
	}

	private void setTotalRecord(String logType, Page page, Device device) {
		if("detail".equals(logType)){
			page.setTotalRecord(device.getLogs().size());
		}else if("base".equals(logType)){
			page.setTotalRecord(device.getBaseLogs().size());
		}
	}

	private List getPage(List logs, String logType, Page page, Device device) {
		if("detail".equals(logType)){
			logs = logService.getDetailLogs(device.getId(), 0, page.size);
		}else if("base".equals(logType)){
			logs = logService.getBaseLogs(device.getId(), 0, page.size);
		}
		page.setCurrentPage(1);
		page.setCount(page.pageCount());
		return logs;
	}

	private List getNextPage(String currentStr, List logs, String logType,
			Page page, Device device) {
		int currentPage = Integer.parseInt(currentStr);
		int start = 0;
		if(currentPage<page.pageCount()){
			page.setCurrentPage(currentPage+1);
			start = currentPage*(page.size);
		}else{
			page.setCurrentPage(page.pageCount());
			start = (currentPage-1)*(page.size);
		}
		System.out.println("start is :" + start);
		int num = page.size;
		if("detail".equals(logType)){
			logs = logService.getDetailLogs(device.getId(), start, num);
		}else if("base".equals(logType)){
			logs = logService.getBaseLogs(device.getId(), start, num);
		}
		return logs;
	}

	private List getPrePage(String currentStr, List logs, String logType,
			Page page, Device device) {
		int currentPage = Integer.parseInt(currentStr);
		if(currentPage>1){
			page.setCurrentPage(currentPage-1);
		}else{
			page.setCurrentPage(1);
		}
		int begin = (currentPage-2)*(page.size);
		int start = begin>=0 ? begin : 0;
		System.out.println("start is :" + start);
		int num = page.size;
		if("detail".equals(logType)){
			logs = logService.getDetailLogs(device.getId(), start, num);
		}else if("base".equals(logType)){
			logs = logService.getBaseLogs(device.getId(), start, num);
		}
		return logs;
	}

	public ActionForward queryBaseLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String deviceID = request.getParameter("deviceID");
		String currentStr = request.getParameter("currentPage");
		String type = request.getParameter("type");
		if(deviceID.matches("\\d+")){
			Device device = service.findById(Integer.parseInt(deviceID));
			List logs = null;
			getLogs(request, device, currentStr, type, logs, "base");	
		}else{
			Page page = new Page();
			request.setAttribute("page", page);
			request.setAttribute("deviceID", "");
		}
		return mapping.findForward("queryBase");
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
		String checkID = request.getParameter("checkID");
		if(("".equals(checkID))||(null == checkID)|| service.getDevice(checkID).size()==0){
			createRespXML(response, "103");
			return null;
		}
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
			parseXML(xml,checkID);
			createRespXML(response,"200");
		}catch (Exception e){
			e.printStackTrace();
			createRespXML(response,"103");
		}
		return null;
	}

	private void parseXML(String xml,String checkID) throws DocumentException {
		System.out.println(xml.toString());
		Document doc = DocumentHelper.parseText(xml.trim());
		Element root = doc.getRootElement();
		for(Iterator i =root.elementIterator();i.hasNext();){
			DetailLog log = new DetailLog();
			Element dv = (Element) i.next();
			String name = dv.elementText("name");
			TestOption option = service.getOption(name);
			List<Device> list = service.getDevice(checkID);
			if(list.size()>0){
				log.setDevice(list.get(0));
			}
			log.setTestOption(option);
			
			log.setTestStatus(dv.elementText("status"));
			log.setNote(dv.elementText("note"));
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

	