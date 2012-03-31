package com.smit.web.clock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;

import com.smit.service.clock.ClockService;
import com.smit.util.Constants;
import com.smit.util.DaoException;
import com.smit.util.ServiceException;
import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Rings;
import com.smit.web.form.UploadFileForm;

public class MusicAction extends MappingDispatchAction {

	private ClockService clockService;

	public void setClockService(ClockService clockService) {
		this.clockService = clockService;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("success");
	}

	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String opt = request.getParameter(Constants.OPERATOR);
		SmitPage page = new SmitPage(0);
		List<Rings> listRings = clockService.findByPartIdRings(page, 1);
		request.setAttribute("listRings", listRings);
		if(opt != null && opt.equals("list")){			
			return mapping.findForward("list");
		}
		return mapping.findForward("home");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String name = request.getParameter("name");
		String photoStr = request.getParameter("photos");
		String[] paths = photoStr.split(";");
		
		// move to new place
		Date now = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance();
		String fakePath = dateFormat.format(now) + System.currentTimeMillis();
		
		String temp_path = request.getSession().getServletContext().getRealPath(Constants.RINGS_PATH_TEMP);;
		
		String real_path =request.getSession().getServletContext()
								.getRealPath(Constants.RINGS_PATH) + File.separator + fakePath;
		String real_path_ex = Constants.RINGS_PATH + File.separator + fakePath;
		File realFile = new File(real_path);
		if(!realFile.exists()){
			realFile.mkdirs();
		}		
		String realFileNameFull = real_path+File.separator+paths[0];
		real_path_ex += File.separator+paths[0];
		File tempFile = new File(temp_path+File.separator+paths[0]);
		
		if(!tempFile.exists()){			
			response.getOutputStream().print(Constants.FAIL);
			return null;
		}	
		FileUtils.copyFile(tempFile, new File(realFileNameFull));
		//boolean brename = tempFile.renameTo(new File(realFileNameFull));
		
		// TODO delete is not ok
		boolean btemp = tempFile.delete();
		
		Rings rings = new Rings();
		rings.setName(name);
		rings.setLocalUrl(real_path_ex);
		rings.setFileName(paths[0]);
		
		clockService.save(rings);
		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			String id = request.getParameter("id");
			Rings rings = new Rings();
			rings.setId(Integer.valueOf(id));
			clockService.delete(rings);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getOutputStream().print(Constants.FAIL);
			return null;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getOutputStream().print(Constants.FAIL);
			return null;
		}
		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}

	public ActionForward uploadMusicFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// TODO 中文乱码
		UploadFileForm fileForm = (UploadFileForm) form;
		System.out.println(fileForm.getFile().getFileName());

		String save_path = request.getSession().getServletContext()
				.getRealPath(Constants.RINGS_PATH_TEMP);
		;
		File file = new File(save_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		byte[] buffer = new byte[8192];
		FormFile uploadFile = fileForm.getFile();
		int count = 0;
		if (null != uploadFile) {
			try {
				InputStream in = uploadFile.getInputStream();
				FileOutputStream out = new FileOutputStream(file
						+ File.separator + uploadFile.getFileName());
				while ((count = in.read(buffer)) > 0) {
					out.write(buffer, 0, count);
				}
				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// response.setContentType("text");
		response.getWriter().print(uploadFile.getFileName());
		return null;
	}

}
