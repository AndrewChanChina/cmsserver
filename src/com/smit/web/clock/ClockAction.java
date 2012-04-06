package com.smit.web.clock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.smit.dao.PushService2UserDao;
import com.smit.service.clock.ClockService;
import com.smit.service.push.IPushDataService;
import com.smit.util.ApplicationCache;
import com.smit.util.Constants;
import com.smit.util.ParamsString;
import com.smit.util.SmitPage;
import com.smit.util.WebUtil;
import com.smit.util.timer.TimeOutTask;
import com.smit.vo.PushService2User;
import com.smit.vo.alarmclock.Clock;
import com.smit.vo.alarmclock.GroupRoom;
import com.smit.vo.alarmclock.LogList;
import com.smit.vo.alarmclock.Rings;

public class ClockAction extends MappingDispatchAction {

	private ClockService clockService;
	private PushService2UserDao pushService2UserDao;

	public static final String PUSH_SERVICE_ID = "GVTO6mcPcNGm3556786E8KL48M9L87rr";

	public static final String OP_LIST = "list";
	public static final String OP_SET = "set"; // 读message的格式进行操作

	public void setClockService(ClockService clockService) {
		this.clockService = clockService;
	}

	public void setPushService2UserDao(PushService2UserDao pushService2UserDao) {
		this.pushService2UserDao = pushService2UserDao;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("success");
	}
	
	private String generateCommitUrl(HttpServletRequest request,int id){
		return String.format(WebUtil.getServerAppIPwithPath(request)
		+ "/clock_postStatus.do?%s=%s&%s=",ParamsString.UUID,id,ParamsString.STATUS);		
	}

	// 接收客户端返回的状态
	public ActionForward postStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter(ParamsString.UUID);
		String idbackup = request.getParameter(ParamsString.UUID_BACKUP);
		String status = request.getParameter(ParamsString.STATUS);
		String localid = request.getParameter(ParamsString.ID);
		
		Clock c = clockService.getById(Integer.valueOf(id));
		if(c == null){
			response.getOutputStream().println("error id");
		}
		if(ParamsString.OP_DELETE.equals(c.getOperation())){
			if("true".endsWith(status)){
				c.setStatus(ParamsString.STATUS_HIDE);
				clockService.deleteTrue(c);
			}else{
				c.setStatus(ParamsString.STATUS_FAIL);
				clockService.updateStatus(c);
			}			
		}else if(ParamsString.OP_UPDATE.equals(c.getOperation())){
			Clock clockBackup = clockService.getById(Integer.valueOf(idbackup));
			if("true".endsWith(status)){
				c.setStatus(ParamsString.STATUS_SUC);	
				c.setId_local(Integer.valueOf(localid));
				
			}else{
				c.copy(clockBackup);
				clockService.updateStatus(c);  // 恢复
				c.setStatus(ParamsString.STATUS_FAIL);
			}	
			clockService.deleteTrue(clockBackup);  // 删除备份
			clockService.updateStatus(c);
		}else{
			if("true".endsWith(status)){
				c.setStatus(ParamsString.STATUS_SUC);
				c.setId_local(Integer.valueOf(localid));
			}else{
				c.setStatus(ParamsString.STATUS_FAIL);
			}		
			clockService.updateStatus(c);
		}
		
		ApplicationCache.getInstance().setAttribute(PUSH_SERVICE_ID, true);
		response.getOutputStream().println("ok");
		return null;
	}

	// 接受浏览器查询，状态的情况
	public ActionForward getStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Object obj = ApplicationCache.getInstance().getAttribute(PUSH_SERVICE_ID);
		boolean status = false;
		if(obj != null){
			status = (Boolean)obj;
		}		
		if(status){
			ApplicationCache.getInstance().setAttribute(PUSH_SERVICE_ID, false);
		}
		response.getOutputStream().print(status);
		return null;
	}

	public ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SmitPage pager = new SmitPage(WebUtil.getIntByRequestParament(request,
				SmitPage.pageNumberParameterName, 1));
		pager.setUrl("clock.do?");
		List<Clock> clockList = clockService.listAll(pager);
		for (Clock c : clockList) {
			c.getWeekofDayBooleanArray();
		}
		List<PushService2User> roomList = pushService2UserDao.listRoomNum();

		request.setAttribute("clockList", clockList);
		request.setAttribute("roomList", roomList);
		request.setAttribute("pb", pager);
		
		return new ActionForward("/hotel_clock_home.do");
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmitPage pager = new SmitPage(WebUtil.getIntByRequestParament(request,
				SmitPage.pageNumberParameterName, 1));
		pager.setUrl("clock_find.do?");
		
		String roomNum = request.getParameter("roomNum");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		List<Clock> clockList = null;
		if (roomNum != null) {
			clockList = clockService.queryByName(pager,
					new String[] { "roomnum" }, new String[] { roomNum });
		} else if (startTime != null && endTime != null) {
			clockList = clockService.queryByTime(pager,
					Integer.valueOf(startTime), Integer.valueOf(endTime));
		} else {
			response.getOutputStream().print("input parameter error");
			return null;
		}

		for (Clock c : clockList) {
			c.getWeekofDayBooleanArray();
		}
		request.setAttribute("pb", pager);
		request.setAttribute("clockList", clockList);
		return mapping.findForward("result");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String[] ids = request.getParameterValues("id");	
		String roomNum = request.getParameter("roomNum");
		
		String adminName = (String) request.getSession().getAttribute(
				Constants.CURUSERNAME);
		
		String pushId = getPushId(roomNum);
		if(pushId == null){
			// TODO error
		}
				
		clockService.delete(request, ids, pushId, adminName);

		response.sendRedirect("clock.do");
		return null;
	}
	
	public ActionForward room_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		List<PushService2User> roomList = pushService2UserDao.listRoomNum();

		request.setAttribute("roomList", roomList);
		return mapping.findForward("add");
	}
	
	public ActionForward room(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		List<PushService2User> roomList = pushService2UserDao.listRoomNum();

		request.setAttribute("roomList", roomList);
		return mapping.findForward("list");
	}
	
	public ActionForward group_add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String groupName = request.getParameter("groupName");
		String[] roomNums = request.getParameterValues("rooms");
		clockService.createGroup(groupName, roomNums);

		response.getOutputStream().print("ok");
		return null;
	}
	
	public ActionForward group(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
		List<GroupRoom> listGroup = clockService.listAllGroup();

		request.setAttribute("listGroup", listGroup);
		return mapping.findForward("list");
	}

	// 添加的数据到数据库，然后发消息给客户端，写当前的状态
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		saveorupdate(request,form);

		response.sendRedirect("clock.do");
		return null;
	}
	
	private void saveorupdate(HttpServletRequest request,ActionForm form){
		ClockForm clockForm = (ClockForm) form;
		Clock c = new Clock();
		if(clockForm.getId() != null){
			c.setId(clockForm.getId());
		}
		c.setLabel(Clock.TYPE_ADMIN);
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());
		c.setMinutes(clockForm.getMinutes());
		c.setNextTime(clockForm.getNext_time());
		c.setRepeatTime(clockForm.getRepeat_time());
		c.setLastLong(clockForm.getLast_time());
		
		c.setDayofweek(clockForm.getDayofWeek());
		c.setEnable(clockForm.getEnable());
		c.setVibrate(clockForm.getVibrate());
		c.setId_local(clockForm.getLocal_id());

		if (clockForm.getMusic().equals("default") == false) {
			Rings rings = clockService.getByIdRings(Integer.valueOf(clockForm
					.getServer_music()));
			c.setMusic(rings.getLocalUrl());
			c.setRings(rings);
		} else {
			c.setRings(null);
		}

		c.getWeekofDayBooleanArray();
		
		if(clockForm.isGroup() == false){
			c.setRoomnum(clockForm.getRoomnum());
			String pushid = getPushId(clockForm.getRoomnum());
			if(pushid == null){
				// TOO error]
				System.out.print("big error..... what to do ???");
			}
			clockService.saveorUpdate(request, c, pushid);
		}else{
			List<GroupRoom> listGroupRoom = clockService.findGroupByName(clockForm.getGroupName());
			for(GroupRoom g : listGroupRoom){	
				c.setId(0);
				c.setRoomnum(g.getRoomNum());
				String pushid = getPushId(g.getRoomNum());
				if(pushid == null){
					// TOO error
					System.out.print("big error.....");
				}
				clockService.saveorUpdate(request, c, pushid);
			}
		}
		
	}
	

	public ActionForward editForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		if(id != null){
			Clock c = clockService.getById(Integer.valueOf(id));
			c.getWeekofDayBooleanArray();
			
			request.setAttribute("clock", c);
		}else{
			List<PushService2User> roomList = pushService2UserDao.listRoomNum();
			request.setAttribute("roomList", roomList);
			List<GroupRoom> listGroup = clockService.listAllGroup();
			request.setAttribute("listGroup", listGroup);
		}

		return new ActionForward("/hotel_clock_saveorupdate.do");
	}

	public ActionForward webservice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String roomNum = (String) request.getParameter("roomNum");
		String data = (String) request.getParameter("data");
		System.out.println("clock webservice " + roomNum);
		System.out.println("clock webservice " + data);

		AlarmXmlParse axp = new AlarmXmlParse(data);
		List<Alarm> la = axp.parse();
		if (la == null) {
			return mapping.findForward("status");
		}
		for (Alarm a : la) {
			switch (AlarmXmlParse.Operation.mapString(a.operation)) {
			case AlarmXmlParse.Operation.ADD:
			case AlarmXmlParse.Operation.UPDATE:
				Clock clock = clockService.getByIdLocal(a.id);
				if (clock == null) {
					clock = new Clock();
					clock.setLabel(Clock.TYPE_ADMIN);
				}
				mapAlarm2Clock(a, clock);
				clock.setRoomnum(roomNum);
				if (clock.getId() == 0) {
					clockService.saveorUpdate(request, clock, null);
				} else {
					clockService.saveorUpdate(request, clock, null);
				}
				break;
			case AlarmXmlParse.Operation.DELETE:
				Clock clock2 = clockService.getByIdLocal(a.id);
				if (clock2 != null) {
					clockService.deleteTrue(clock2);
				}
				break;
			case AlarmXmlParse.Operation.LIST:
				break;
			default:
				break;
			}
		}

		return mapping.findForward("status");
	}

	public void mapAlarm2Clock(Alarm a, Clock clock) {
		clock.setId_local(a.id);
		clock.setHour(a.hour);
		clock.setMinutes(a.minutes);
		clock.setDayofweek(a.daysOfWeek.getCoded());
		// clock.setAlarmtime(String.valueOf(a.time));
		clock.setAlert(a.alert);
		clock.setEnable(a.enabled ? 1 : 0);
		clock.setLabel(a.label);
		clock.setMusic(a.musicPath);
		if(a.lastTime != null){
			clock.setLastLong(Integer.valueOf(a.lastTime));
		}
		if(a.nextTime != null){
			clock.setNextTime(Integer.valueOf(a.nextTime));
		}
		if(a.repeatTime != null){
			clock.setRepeatTime(Integer.valueOf(a.repeatTime));
		}
	}

	private void mapClock2Alarm(HttpServletRequest request, Clock clock, Alarm a) {
		if (a == null || clock == null) {
			return;
		}
		if (clock.getId_local() == null) {
			a.id = -1;
		} else {
			a.id = clock.getId_local();
		}

		a.hour = clock.getHour();
		a.minutes = clock.getMinutes();
		a.daysOfWeek = new Alarm.DaysOfWeek(clock.getDayofweek());
		// a.time = Integer.valueOf(clock.getAlarmtime());
		a.alert = clock.getAlert();
		a.enabled = clock.getEnable() == 1;
		a.label = clock.getLabel();
		a.musicPath = WebUtil.getServerAppIPwithPath(request) + File.separator
				+ clock.getMusic();
		a.operation = clock.getOperation();
		a.nextTime = String.valueOf(clock.getNextTime());
		a.lastTime = String.valueOf(clock.getLastLong());
		a.repeatTime = String.valueOf(clock.getRepeatTime());
	}
	
	private String getPushId(String roomNum){
		List<PushService2User> listPush2User = pushService2UserDao.listAll(
				null, new String[] { "serviceId", "roomNum" },
				new String[] { PUSH_SERVICE_ID, roomNum });

		if (listPush2User == null || listPush2User.size() < 1) {
			return null;
		}
		
		return listPush2User.get(0).getPushId();
	}

}
