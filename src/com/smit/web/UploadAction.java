package com.smit.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.BeanUtils;

import com.smit.service.MediaService;
import com.smit.util.Page;
import com.smit.util.ServiceException;
import com.smit.vo.Content;
import com.smit.vo.Media;

public class UploadAction extends DispatchAction {
	
	ActionMessages msgs = new ActionMessages();
	private MediaService mediaService;
	
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return new ActionForward("upload.do?op=list");
	}
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadForm uploadForm = (UploadForm)form;
		String fileSize = uploadForm.getFile().getFileSize() /1024 + "B";
		String basePath = this.getServlet().getServletContext().getRealPath("/");
		//SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd\\HH");
		SimpleDateFormat format = new SimpleDateFormat("yyyy\\MM\\dd");
		File baseDir = new File(basePath);
		if(!baseDir.exists()){
			baseDir.mkdirs();
		}
		//System.out.println(basePath);System.exit(0);
		int beginIndex = uploadForm.getFile().getFileName().lastIndexOf(".");
		String ext = uploadForm.getFile().getFileName().substring(beginIndex);
		String fileName = UUID.randomUUID().toString() + ext;
		System.out.println(fileName);
		File file = new File(baseDir,fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
				
			}catch(IOException e){
				e.printStackTrace();
				
			}
			
		}
		FileOutputStream fos = new FileOutputStream(file);		
		fos.write(uploadForm.getFile().getFileData());
		fos.flush();
		fos.close();
		Media media = new Media();
		Date date = new Date();
		media.setCreatetime(new Double(String.valueOf(date.getTime())));
		media.setFileName(fileName);
		media.setSortRank(uploadForm.getSortRank());
		media.setSource(media.getSource());
		media.setTypeName(media.getTypeName());
		media.setFileSize(fileSize);
		media.setPath(baseDir.getPath());
		try {
			mediaService.save(media);	
			return mapping.findForward("s");			
		}catch(ServiceException e){
			e.printStackTrace();
			ActionMessage msg = new ActionMessage(e.getMessage());
			msgs.add("contentAction.save.failure", msg);
			this.saveMessages(request, msgs);
			return mapping.findForward("f");		
		}		
	
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Integer currentPage = (request.getParameter("pn")== null ||request.getParameter("pn") == "") ?1 : Integer.parseInt(request.getParameter("pn"));
		Integer pageSize = (request.getParameter("ps") == null ||request.getParameter("ps") == "") ? 20 : Integer.parseInt(request.getParameter("ps"));
	
		try {
			Page page = page = mediaService.findAll(currentPage, pageSize);	
				
			
			//System.out.println(page.getList().size());
			request.setAttribute("page", page);
			return mapping.findForward("list");
		}catch(ServiceException e){
			ActionMessage msg = new ActionMessage(e.getMessage());
			msgs.add("contentAction.list.failure",msg);
			this.saveMessages(request, msgs);
			return mapping.findForward("f");
			
		}
	
		
	
	
	
	
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	
	}

	
	

}
