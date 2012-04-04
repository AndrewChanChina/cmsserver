package com.smit.service.clock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.smit.dao.clock.ClockDao;
import com.smit.dao.clock.GroupRoomDao;
import com.smit.dao.clock.LogListDao;
import com.smit.dao.clock.RingsDao;
import com.smit.service.push.IPushDataService;
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
import com.smit.web.clock.Alarm;
import com.smit.web.clock.AlarmXmlParse;
import com.smit.web.clock.ClockForm;

public class ClockServiceImpl implements ClockService {

	private ClockDao clockDao;
	private RingsDao ringsDao;
	private GroupRoomDao groupRoomDao;
	private LogListDao logListDao;

	public static final String PUSH_SERVICE_ID = "GVTO6mcPcNGm3556786E8KL48M9L87rr";
	public static final String OP_LIST = "list";
	public static final String OP_SET = "set"; // 读message的格式进行操作

	public LogListDao getLogListDao() {
		return logListDao;
	}

	public void setLogListDao(LogListDao logListDao) {
		this.logListDao = logListDao;
	}

	public GroupRoomDao getGroupRoomDao() {
		return groupRoomDao;
	}

	public void setGroupRoomDao(GroupRoomDao groupRoomDao) {
		this.groupRoomDao = groupRoomDao;
	}

	public ClockDao getClockDao() {
		return clockDao;
	}

	public RingsDao getRingsDao() {
		return ringsDao;
	}

	public void setRingsDao(RingsDao ringsDao) {
		this.ringsDao = ringsDao;
	}

	public void setClockDao(ClockDao clockDao) {
		this.clockDao = clockDao;
	}

	private void save(Clock clock) {
		clock.setOperation(ParamsString.OP_CREATE);
		clock.setStatus(ParamsString.STATUS_SEND);
		clockDao.save(clock);
	}

	public void updateStatus(Clock clock) {
		clockDao.update(clock);
		saveClockLog(clock, LogList.OP_UPDATE);
	}

	private void update(Clock clock) {
		clock.setOperation(ParamsString.OP_UPDATE);
		clock.setStatus(ParamsString.STATUS_SEND);
		clockDao.update(clock);

	}

	public void delete(HttpServletRequest request, String[] ids, String pushId,
			String admin) {

		List<Clock> listClock = new ArrayList<Clock>();
		List<Alarm> la = new ArrayList<Alarm>();
		// read database
		for (String id : ids) {

			Clock clock = getById(Integer.valueOf(id));
			if (clock != null) {
				clock.setOperation(AlarmXmlParse.Operation.s_del);
				listClock.add(clock);

				Alarm alarm = new Alarm();
				mapClock2Alarm(request, clock, alarm);
				alarm.commitUrl = generateCommitUrl(request, clock.getId(), -1);
				System.out.println(alarm.commitUrl);
				la.add(alarm);
			}

		}

		// send data
		pushData(request, la, pushId);

		for (Clock c : listClock) {
			// delete
			delFake(c);
			// set time out
			Timer timer = new Timer();
			TimeOutTask timeoutTask = new TimeOutTask(LogList.TYPE_CLOCK,
					c.getId(), PUSH_SERVICE_ID, this, null);
			timer.schedule(timeoutTask, ParamsString.TIME_OUT);

			saveClockLog(c, admin);
		}
	}

	private void delFake(Clock c) {
		c.setOperation(ParamsString.OP_DELETE);
		c.setStatus(ParamsString.STATUS_SEND);
		updateStatus(c);
	}

	private boolean pushData(HttpServletRequest request, List<Alarm> la,
			String pushId) {

		HttpSession session = request.getSession();
		IPushDataService ps = (IPushDataService) session
				.getAttribute(Constants.PUSH_CONNECTION);
		if (ps == null || !ps.isConnected()) {
			return false;
		}

		List<String> pushIdList = new ArrayList<String>();

		pushIdList.add(pushId);

		String message = AlarmXmlParse.alarmsToXmlItems(la);

		// send out
		ps.sendPushDataFromDev(PUSH_SERVICE_ID, pushIdList, false,
				RandomStringUtils.randomNumeric(4), "clock",
				RandomStringUtils.randomNumeric(4), OP_SET, message);
		System.out.println("成功发送消息---" + message);
		return true;
	}

	private String generateCommitUrl(HttpServletRequest request, int id,
			int backupId) {
		return String.format(WebUtil.getServerAppIPwithPath(request)
				+ "/clock_postStatus.do?%s=%s&%s=%s&%s=", ParamsString.UUID,
				id, ParamsString.UUID_BACKUP, backupId, ParamsString.STATUS);
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
		if (clock.getMusic() != null) {
			a.musicPath = WebUtil.getServerAppIPwithPath(request)
					+ File.separator + clock.getMusic();
		} else {
			a.musicPath = "";
		}
		a.operation = clock.getOperation();
		a.nextTime = String.valueOf(clock.getNextTime());
		a.lastTime = String.valueOf(clock.getLastLong());
		a.repeatTime = String.valueOf(clock.getRepeatTime());
	}

	public void deleteTrue(Clock clock) {
		clockDao.delete(clock);
	}

	public List<Clock> findByPartId(SmitPage page, Integer partId) {
		return clockDao.findByPartId(page, partId);
	}

	public Clock getById(Integer id) {
		return clockDao.getById(id);
	}

	public List<Clock> listAll(SmitPage page) {
		return clockDao.listAll(page);
	}

	public List<Clock> getAllItems() {
		return clockDao.getAllItems();
	}

	public List<Clock> queryByName(SmitPage page, String[] names,
			String[] values) {
		return clockDao.queryByName(page, names, values);
	}

	public List<Clock> queryByTime(SmitPage page, Integer startTime,
			Integer endTime) {
		return clockDao.queryByTime(page, startTime, endTime);
	}

	public List<Object[]> getLatestItems() {
		return clockDao.getLatestItems();
	}

	public Clock getByIdLocal(Integer localId) {
		return clockDao.getByIdLocal(localId);
	}

	public void save(Rings rings) {
		ringsDao.save(rings);
	}

	public void update(Rings rings) {
		ringsDao.update(rings);

	}

	public void delete(Rings rings) {
		ringsDao.delete(rings);
	}

	public Rings getByIdRings(Integer id) {
		return ringsDao.getById(id);
	}

	public List<Rings> findByPartIdRings(SmitPage page, Integer partId) {
		return ringsDao.listAll(page);
	}

	public void createGroup(String groupName, String[] roomNums) {
		for (String rm : roomNums) {
			GroupRoom gr = new GroupRoom();
			gr.setGroupName(groupName);
			gr.setRoomNum(rm);
			groupRoomDao.save(gr);
		}
	}

	public List<GroupRoom> listAllGroup() {
		List<GroupRoom> myList = groupRoomDao.listAllByGroup();
		for (GroupRoom g : myList) {
			List<GroupRoom> list = groupRoomDao.findGroupByName(g
					.getGroupName());
			if (list == null) {
				continue;
			}
			for (GroupRoom gr : list) {
				g.getListRoomNums().add(gr.getRoomNum());
			}
		}

		return myList;
	}

	public List<GroupRoom> findGroupByName(String groupName) {
		return groupRoomDao.findGroupByName(groupName);
	}

	/**
	 * for log system
	 */
	public void saveClockLog(Clock clock, String operator) {
		LogList logList = new LogList();
		logList.initTime();
		logList.setType(LogList.TYPE_CLOCK);
		logList.setRoomNum(clock.getRoomnum());
		logList.setOperation(operator);
		logList.setKeyData(clock.toString());
		logList.setOriginData(clock.toString());
		logListDao.save(logList);
	}

	public void saveLogList(LogList logList) {
		logListDao.save(logList);
	}

	public List<LogList> listLatestLog(SmitPage page, String[] names,
			String[] values) {
		return logListDao.listLatestLog(page,names,values);
	}

	// for a single clock
	public void saveorUpdate(HttpServletRequest request, Clock c, String pushid) {

		Clock backupClock = null;
		int backupid = -1;
		// modify database
		if (c.getId() == 0) {
			save(c);
		} else {
			// copy
			backupClock = new Clock();
			backupClock.copy(getById(c.getId()));
			backupClock.setOperation(ParamsString.OP_UPDATE);
			backupClock.setStatus(ParamsString.STATUS_HIDE);
			clockDao.save(backupClock);
			backupid = backupClock.getId();
			update(c);
		}

		// send to client

		Alarm alarm = new Alarm();
		alarm.operation = AlarmXmlParse.Operation.s_add;
		alarm.commitUrl = generateCommitUrl(request, c.getId(), backupid);
		mapClock2Alarm(request, c, alarm);
		pushData(request, alarm, pushid);
		System.out.println(alarm.commitUrl);

		// set time out
		setTimeOut(c.getId(), backupClock);

		// set log
		String adminName = (String) request.getSession().getAttribute(
				Constants.CURUSERNAME);
		saveClockLog(c, adminName);

	}

	private void setTimeOut(int id, Clock backupClock) {

		Timer timer = new Timer();
		TimeOutTask timeoutTask = new TimeOutTask(LogList.TYPE_CLOCK, id,
				PUSH_SERVICE_ID, this, backupClock);
		timer.schedule(timeoutTask, ParamsString.TIME_OUT);
	}

	private boolean pushData(HttpServletRequest request, Alarm alarm,
			String pushId) {

		if (pushId == null) { // don't need to push
			return true;
		}

		List<Alarm> la = new ArrayList<Alarm>();
		la.add(alarm);
		List<String> pushIdList = new ArrayList<String>();
		pushIdList.add(pushId);

		HttpSession session = request.getSession();
		IPushDataService ps = (IPushDataService) session
				.getAttribute(Constants.PUSH_CONNECTION);
		if (ps == null || !ps.isConnected()) {
			return false;
		}

		String message = AlarmXmlParse.alarmsToXmlItems(la);

		// send out
		ps.sendPushDataFromDev(PUSH_SERVICE_ID, pushIdList, false,
				RandomStringUtils.randomNumeric(4), "clock",
				RandomStringUtils.randomNumeric(4), OP_SET, message);
		System.out.println("成功发送消息---" + message);
		return true;
	}
}
