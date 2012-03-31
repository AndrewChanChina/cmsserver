package com.smit.service.clock;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Clock;
import com.smit.vo.alarmclock.Rings;

public interface ClockService {
	
	public void save(Clock clock);
	public void update(Clock clock);
	public void delete(Clock clock);
	
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
	
	
}
