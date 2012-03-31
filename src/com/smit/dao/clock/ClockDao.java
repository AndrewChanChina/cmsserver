package com.smit.dao.clock;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Clock;

public interface ClockDao {
	
	public void save(Clock clock);
	public void update(Clock clock);
	public void delete(Clock clock);
	
	public List<Clock> findByPartId(SmitPage page, Integer partId);
	public Clock getById(Integer id);
	public List<Clock> listAll(SmitPage page);
	
	public List<Clock> getAllItems();
	public List<Object[]> getLatestItems();
	public List<Clock> queryByName(SmitPage page,String []names, String []values);
	public List<Clock> queryByTime(SmitPage page,Integer startTime, Integer endTime);
	public Clock getByIdLocal(Integer localId);
	

}
