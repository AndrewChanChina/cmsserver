package com.smit.web.apk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;

import com.smit.service.push.IPushDataService;
import com.smit.util.Constants;
import com.smit.util.ParamsString;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.PushContent;
import com.smit.vo.apk.ApkInfo;
import com.smit.web.form.UploadFileForm;

public class ApkAction extends MappingDispatchAction {

	public static final String TYPE_UPLOAD = "upload";
	public static final String TYPE_DEVICE = "device";
	public static final String TYPE_SYSTEM = "system";
	
	// get all apk
	public static final String OP_LIST = "list_apk";
	public static final String OP_SET = "set";		// 读message的格式进行操作
	
	public static final String XML_OP_INSTALL = "install";
	public static final String XML_OP_UNINSTALL = "uninstall";
	public static final String XML_OP_LIST = "list";
	
	public static final String PUSH_SERVICE_ID = "tmticb0yfyRl4O71gXTxpbiTC92DvWFf";

	private ApkInfoService apkInfoService;

	public void setApkInfoService(ApkInfoService apkInfoService) {
		this.apkInfoService = apkInfoService;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("success");
	}

	// 接收客户端返回的状态
	public ActionForward postStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter(ParamsString.UUID);
		String status = request.getParameter(ParamsString.STATUS);
		ServletContext application = request.getSession().getServletContext();
		application.setAttribute(id, status);
		response.getOutputStream().println("ok");
		return null;
	}

	// 接受浏览器查询，状态的情况
	public ActionForward getStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter(ParamsString.UUID);
		ServletContext application = request.getSession().getServletContext();
		String status = (String) application.getAttribute(id);
		System.out.print(id + ":" + status);
		response.getOutputStream().println(status);
		return null;
	}

	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		List<ApkInfo> apkList;
		SmitPage page = new SmitPage(0);
		
		if (type != null && type.equals("uninstall")) {
			
			apkList = apkInfoService.findByNamedParam(page,
					new String[] { "type" }, new String[] { TYPE_UPLOAD });
		} else {
			apkList = apkInfoService.findByNamedParam(page,
					new String[] { "type" }, new String[] { TYPE_DEVICE });
		}

		request.setAttribute("roomnum", "201");
		request.setAttribute("type", type);
		request.setAttribute("apkList", apkList);
		
		return mapping.findForward("home");
	}

	// 添加的数据到数据库，然后发消息给客户端，写当前的状态
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String appName = request.getParameter("appName");
		String packageName = request.getParameter("packageName");
		String iconName = request.getParameter("icon_file");
		String apkName = request.getParameter("apk_file");

		// move to new place
		Date now = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance();
		String fakePath = dateFormat.format(now) + System.currentTimeMillis();

		String temp_path = request.getSession().getServletContext()
				.getRealPath(Constants.RINGS_PATH_TEMP);
		
		String real_path = request.getSession().getServletContext()
				.getRealPath(Constants.APK_PATH)
				+ File.separator + fakePath;

		File realFile = new File(real_path);
		if (!realFile.exists()) {
			realFile.mkdirs();
		}
		String realFileNameFull = real_path + File.separator + apkName;
		File tempFile = new File(temp_path + File.separator + apkName);

		if (!tempFile.exists()) {
			response.getOutputStream().print(Constants.FAIL);
			return null;
		}
		FileUtils.copyFile(tempFile, new File(realFileNameFull));

		boolean btemp = tempFile.delete();

		ApkInfo apkInfo = new ApkInfo();
		apkInfo.setApkUrl(realFileNameFull);
		apkInfo.setAppName(appName);
		apkInfo.setPackageName(packageName);
		apkInfo.setIconUrl(realFileNameFull);
		apkInfo.setType(TYPE_UPLOAD);

		apkInfoService.save(apkInfo);

		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}

	// 请求客户端删除apk
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		ApkInfo apkInfo = new ApkInfo();
		apkInfo.setId(Integer.valueOf(id));
		try {
			apkInfoService.delete(apkInfo);
			request.setAttribute("status", Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}

		return mapping.findForward("status");
	}

	public ActionForward pushData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] apkIds = request.getParameterValues("ids");
		String type = request.getParameter("install");//  not_install
		String roomNum = request.getParameter("roomnum");
		boolean bInstall = !"not_install".equals(type);
		
		
		List<String> pushIdList = new ArrayList<String>();
		
		pushIdList.add("2012321159156669");
		pushIdList.add("20123211441538316");
		
		HttpSession session = request.getSession();
		IPushDataService ps = (IPushDataService) session.getAttribute(Constants.PUSH_CONNECTION);
		
		String op = request.getParameter("op");
		if(op == OP_LIST){
			ps.sendPushDataFromDev(PUSH_SERVICE_ID, pushIdList, true, WebUtil.randomString(10),
					"push apk", "push apk ticket", OP_LIST, "quickly");
			
		}else{
			List<ApkInfo> listApk = new ArrayList<ApkInfo>();
			for(int i=0; i<apkIds.length; i++){
				ApkInfo apk = apkInfoService.getById(Integer.valueOf(apkIds[i]));
				if(apk != null){
					if(bInstall){
						apk.setOperation(XML_OP_INSTALL);
					}else{
						apk.setOperation(XML_OP_UNINSTALL);
					}
					listApk.add(apk);
				}				
			}
			String message = ApkInfoXmlParse.allApkInfoToXmlItems(listApk);
			
			ps.sendPushDataFromDev("apk_install", pushIdList, true, WebUtil.randomString(10),
					"push apk", "push apk ticket", OP_SET, message);			
		}
		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}
	public void pushData(HttpServletRequest request, PushContent pc)
			throws Exception {
		HttpSession session = request.getSession();
		String[] apkIds = request.getParameterValues("ids");
		IPushDataService ps = (IPushDataService) session
				.getAttribute(Constants.PUSH_CONNECTION);
		List<String> userList = new ArrayList<String>();
		for (String s : apkIds) {
			userList.add(session.getAttribute(Constants.CURUSERNAME) + "@smit/"
					+ s);
		}
		ps.sendPushDataFromUser(userList, false,
				RandomStringUtils.randomNumeric(4), pc.getTitle(), "",
				pc.getUrl(), pc.getDes(), pc.getContent_type());
	}

	public ActionForward webservice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String roomNum = (String) request.getParameter("roomNum");
		String data = (String) request.getParameter("data");
		String type = (String) request.getParameter("type");
		System.out.println("clock webservice " + roomNum);
		System.out.println("clock webservice " + data);

		ApkInfoXmlParse axp = new ApkInfoXmlParse(data);
		List<ApkInfo> la = axp.parse();
		if (la == null) {
			response.getOutputStream().print(Constants.FAIL);
			return null;
		}
		if("list".equals(type)){
			//apkInfoService.
		}
		// TODO jia shang fangjian
		for (ApkInfo a : la) {
			if (XML_OP_INSTALL.equals(a.getOperation())) {
				ApkInfo apk = apkInfoService.findByPackageName(a
						.getPackageName());
				if (apk != null) {
					apk.setApkUrl(a.getApkUrl());
					apk.setAppName(a.getAppName());
					apk.setIconUrl(a.getIconUrl());
					apk.setOperation(a.getOperation());
					apk.setPackageName(a.getPackageName());
					apk.setStatus(a.getStatus());
				} else {
					apk = a;
				}
				apk.setType(TYPE_DEVICE);
				a.setRoomnum(roomNum);
				apkInfoService.save(apk);
			} else if (XML_OP_UNINSTALL.equals(a.getOperation())) {
				ApkInfo apk = apkInfoService.findByPackageName(a
						.getPackageName());
				if (apk != null) {
					apkInfoService.delete(apk);
				}
			} else if(XML_OP_LIST.equals(a.getOperation())) {

			}
		}
		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}

	public ActionForward uploadApkFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
