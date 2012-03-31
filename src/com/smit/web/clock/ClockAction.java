package com.smit.web.clock;

import java.io.File;
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

import com.smit.dao.PushService2UserDao;
import com.smit.service.clock.ClockService;
import com.smit.service.push.IPushDataService;
import com.smit.service.push.IPushManageService;
import com.smit.util.Constants;
import com.smit.util.ParamsString;
import com.smit.util.WebUtil;
import com.smit.vo.PushContent;
import com.smit.vo.PushService2User;
import com.smit.vo.alarmclock.Clock;
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
		List<Clock> clockList = clockService.getAllItems();
		for (Clock c : clockList) {
			c.getWeekofDayBooleanArray();
		}
		List<PushService2User> roomList = pushService2UserDao.listRoomNum();

		request.setAttribute("clockList", clockList);
		request.setAttribute("roomList", roomList);
		return mapping.findForward("home");
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String roomNum = request.getParameter("roomNum");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		List<Clock> clockList = null;
		if (roomNum != null) {
			clockList = clockService.queryByName(null,
					new String[] { "roomnum" }, new String[] { roomNum });
		} else if (startTime != null && endTime != null) {
			clockList = clockService.queryByTime(null,
					Integer.valueOf(startTime), Integer.valueOf(endTime));
		} else {
			response.getOutputStream().print("input parameter error");
			return null;
		}

		for (Clock c : clockList) {
			c.getWeekofDayBooleanArray();
		}

		request.setAttribute("clockList", clockList);
		return mapping.findForward("result");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String[] ids = request.getParameterValues("id");

		List<Clock> listClock = new ArrayList<Clock>();

		for (String id : ids) {

			Clock clock = clockService.getById(Integer.valueOf(id));
			if (clock != null) {
				clock.setOperation(AlarmXmlParse.Operation.s_del);
				listClock.add(clock);
			}
		}

		pushData(request, listClock);

		try {
			for (Clock clock : listClock) {
				clockService.delete(clock);
			}
			request.setAttribute("status", Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}

		return mapping.findForward("status");
	}

	// 添加的数据到数据库，然后发消息给客户端，写当前的状态
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ClockForm clockForm = (ClockForm) form;
		Clock c = new Clock();
		c.setLabel(Clock.TYPE_ADMIN);
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());
		c.setMinutes(clockForm.getMinutes());
		c.setNextTime(clockForm.getNext_time());
		c.setRepeatTime(clockForm.getRepeat_time());
		c.setLastLong(clockForm.getLast_time());

		c.setRoomnum(clockForm.getRoomnum());
		c.setDayofweek(clockForm.getDayofWeek());
		c.setEnable(clockForm.getEnable());
		c.setVibrate(clockForm.getVibrate());
		c.setOperation(AlarmXmlParse.Operation.s_add);

		if (clockForm.getMusic().equals("default") == false) {
			Rings rings = clockService.getByIdRings(Integer.valueOf(clockForm
					.getServer_music()));
			c.setMusic(rings.getLocalUrl());
			c.setRings(rings);
		} else {
			c.setRings(null);
		}

		boolean barray[] = c.getWeekofDayBooleanArray();
		String status = Constants.SUCCESS;

		try {
			clockService.save(c);

		} catch (Exception e) {
			e.printStackTrace();
			status = Constants.FAIL;
		}
		// TODO send to client
		pushData(request, c, new String[] { clockForm.getRoomnum() });

		// write to application
		ServletContext application = request.getSession().getServletContext();
		String uuid = "123456";
		application.setAttribute(uuid, status);
		response.getOutputStream().print(uuid + "=" + status);
		response.sendRedirect("clock.do");
		return null;
	}

	private boolean pushData(HttpServletRequest request, Clock clock,
			String[] roomNums) throws Exception {

		HttpSession session = request.getSession();
		IPushDataService ps = (IPushDataService) session
				.getAttribute(Constants.PUSH_CONNECTION);
		if (ps == null || !ps.isConnected()) {
			return false;
		}

		List<Alarm> la = new ArrayList<Alarm>();
		Alarm alarm = new Alarm();
		mapClock2Alarm(request, clock, alarm);
		la.add(alarm);
		List<String> pushIdList = new ArrayList<String>();

		// find push id
		for (String roomNum : roomNums) {

			List<PushService2User> listPush2User = pushService2UserDao.listAll(
					null, new String[] { "serviceId", "roomNum" },
					new String[] { PUSH_SERVICE_ID, roomNum });

			if (listPush2User == null) {
				continue;
			}
			for (PushService2User ps2user : listPush2User) {
				pushIdList.add(ps2user.getPushId());
			}
		}

		String message = AlarmXmlParse.alarmsToXmlItems(la);

		// send out
		ps.sendPushDataFromDev(PUSH_SERVICE_ID, pushIdList, false,
				RandomStringUtils.randomNumeric(4), "clock",
				RandomStringUtils.randomNumeric(4), OP_SET, message);

		return true;
	}

	private boolean pushData(HttpServletRequest request, List<Clock> listClock)
			throws Exception {

		HttpSession session = request.getSession();
		IPushDataService ps = (IPushDataService) session
				.getAttribute(Constants.PUSH_CONNECTION);
		if (ps == null || !ps.isConnected()) {
			return false;
		}

		List<Alarm> la = new ArrayList<Alarm>();
		for (Clock clock : listClock) {
			Alarm alarm = new Alarm();
			mapClock2Alarm(request, clock, alarm);
			la.add(alarm);
		}

		List<String> pushIdList = new ArrayList<String>();
		// find push id
		List<PushService2User> listPush2User = pushService2UserDao.listAll(
				null, new String[] { "serviceId", "roomNum" }, new String[] {
						PUSH_SERVICE_ID, listClock.get(0).getRoomnum() });

		if (listPush2User == null) {
			return false;
		}
		for (PushService2User ps2user : listPush2User) {
			pushIdList.add(ps2user.getPushId());
		}

		String message = AlarmXmlParse.alarmsToXmlItems(la);

		// send out
		ps.sendPushDataFromDev(PUSH_SERVICE_ID, pushIdList, false,
				RandomStringUtils.randomNumeric(4), "clock",
				RandomStringUtils.randomNumeric(4), OP_SET, message);

		return true;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ClockForm clockForm = (ClockForm) form;
		Clock c = new Clock();
		c.setId(clockForm.getId());
		c.setName(clockForm.getName());
		c.setHour(clockForm.getHour());

		try {
			clockService.update(c);
			request.setAttribute("status", Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", Constants.FAIL);
		}

		return mapping.findForward("status");
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
					clockService.save(clock);
				} else {
					clockService.update(clock);
				}
				break;
			case AlarmXmlParse.Operation.DELETE:
				Clock clock2 = clockService.getByIdLocal(a.id);
				if (clock2 != null) {
					clockService.delete(clock2);
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
		clock.setLastLong(Integer.valueOf(a.lastTime));
		clock.setNextTime(Integer.valueOf(a.nextTime));
		clock.setRepeatTime(Integer.valueOf(a.repeatTime));
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

}
