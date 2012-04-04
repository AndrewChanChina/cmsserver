package com.smit.web.clock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.smit.dao.PushService2UserDao;
import com.smit.service.clock.ClockService;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.vo.alarmclock.LogList;

public class OperationLogAction extends DispatchAction {

	private PushService2UserDao pushService2UserDao;
	private ClockService clockService;

	public PushService2UserDao getPushService2UserDao() {
		return pushService2UserDao;
	}

	public void setPushService2UserDao(PushService2UserDao pushService2UserDao) {
		this.pushService2UserDao = pushService2UserDao;
	}

	public ClockService getClockService() {
		return clockService;
	}

	public void setClockService(ClockService clockService) {
		this.clockService = clockService;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.sendRedirect("operationlog.do?opt=list");
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmitPage page = new SmitPage(WebUtil.getIntByRequestParament(request, 
				SmitPage.pageNumberParameterName, 1),20);
		page.setUrl("operationlog.do?opt=list");
		
		List<LogList> listLog = clockService.listLatestLog(page, new String[]{}, new String[]{});
		
		request.setAttribute("listLog", listLog);
		request.setAttribute("pb", page);
		return mapping.findForward("list");
	}

}
