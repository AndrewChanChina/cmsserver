package com.smit.service.clock;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Clock;
import com.smit.vo.alarmclock.GroupRoom;
import com.smit.vo.alarmclock.LogList;
import com.smit.vo.alarmclock.Rings;

public interface ClockService {
	
	/*public void save(Clock clock);
	public void update(Clock clock);*/
	public void deleteTrue(Clock clock);
	
	public void updateStatus(Clock clock);	
	
	public void saveorUpdate(HttpServletRequest request, Clock c,
			String pushid);
	public void delete(HttpServletRequest request, String[] ids, String pushId,
			String admin);
	
	public List<Clock> findByPartId(SmitPage page, Integer partId);
	public Clock getById(Integer id);
	public List<Clock> listAll(SmitPage page);
	
	public List<Clock> queryByName(SmitPage page,String []names, String []values);
	public List<Clock> queryByTime(SmitPage page,Integer startTime, Integer endTime);
	public List<Clock> getAllItems();
	public List<Object[]> getLatestItems();

	public Clock getByIdLocal(Integer localId);
	
	public void save(Rings rings);
	public void update(Rings rings);
	public void delete(Rings rings);
	public Rings getByIdRings(Integer id);
	public List<Rings> findByPartIdRings(SmitPage page, Integer partId);
	
	
	public void createGroup(String groupName,String[] roomNums);
	public List<GroupRoom> findGroupByName(String groupName);
	public List<GroupRoom> listAllGroup();
	
	public void saveClockLog(Clock clock, String operator);
	public void saveLogList(LogList logList);
	public List<LogList> listLatestLog(SmitPage page, String []names, String []values);

	
	
}
