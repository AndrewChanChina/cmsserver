package com.smit.web.apk;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.service.push.IPushDataService;
import com.smit.util.Constants;
import com.smit.util.ParamsString;
import com.smit.vo.PushContent;
import com.smit.vo.alarmclock.Clock;
import com.smit.vo.apk.ApkInfo;

public class ApkAction extends MappingDispatchAction {
	
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
		String status = (String)application.getAttribute(id);
		System.out.print(id + ":" + status);
		response.getOutputStream().println(status);
		return null;
	}
	
	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ApkInfo> apkList = apkInfoService.getAllItems();
		
		
		request.setAttribute("apkList", apkList);
		return mapping.findForward("home");
	}
	// 添加的数据到数据库，然后发消息给客户端，写当前的状态
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		return null;
	}
	// 请求客户端删除apk
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		Clock clock = new Clock();
		clock.setId(Integer.valueOf(id));
		try{
			//clockService.delete(clock);
			request.setAttribute("status", Constants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}
		
		return mapping.findForward("status");
	}
	
	public void pushData(HttpServletRequest request,PushContent pc) throws Exception{
		HttpSession session = request.getSession();
		String[] deviceIds = request.getParameterValues("deviceIds");
		IPushDataService ps = (IPushDataService)session.getAttribute(Constants.PUSH_CONNECTION);
		List<String> userList = new ArrayList<String>();
		for(String s:deviceIds){
			userList.add(session.getAttribute(Constants.CURUSERNAME)
					+ "@smit/"+ s);
		}
		ps.sendPushDataFromUser(userList, false, RandomStringUtils.randomNumeric(4), pc.getTitle(), "", pc.getUrl(), pc.getDes(), pc.getContent_type());
	}
	
	
	public ActionForward webservice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String roomNum = (String)request.getParameter("roomNum");
		String data = (String)request.getParameter("data");
		System.out.println("clock webservice " + roomNum);
		System.out.println("clock webservice " + data);
		
		ApkInfoXmlParse axp = new ApkInfoXmlParse(data);
		List<ApkInfo> la = axp.parse();
		if(la == null){
			return mapping.findForward("status");
		}
		// TODO jia shang fangjian
		for(ApkInfo a : la){
			if("install".equals(a.getOperation())) {
				ApkInfo apk = apkInfoService.findByPackageName(a.getPackageName());
				if(apk != null){
					apk.setApkUrl(a.getApkUrl());
					apk.setAppName(a.getAppName());
					apk.setIconUrl(a.getIconUrl());
					apk.setOperation(a.getOperation());
					apk.setPackageName(a.getPackageName());
					apk.setStatus(a.getStatus());
				}else{
					apk = a;
				}
				a.setRoomnum(roomNum);
				apkInfoService.save(apk);
			}else if("uninstall".equals(a.getOperation())){
				ApkInfo apk = apkInfoService.findByPackageName(a.getPackageName());
				if(apk != null){
					apkInfoService.delete(apk);
				}
			}else{
				
			}
		}
		response.getOutputStream().print(Constants.SUCCESS);
		return null;
	}

}
