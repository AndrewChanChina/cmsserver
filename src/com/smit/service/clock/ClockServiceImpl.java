package com.smit.service.clock;

import java.util.List;

import com.smit.dao.clock.ClockDao;
import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Clock;

public class ClockServiceImpl implements ClockService {
	
	private ClockDao clockDao;
	
	

	public void setClockDao(ClockDao clockDao) {
		this.clockDao = clockDao;
	}

	public void save(Clock clock) {
		clockDao.save(clock);
	}

	public void update(Clock clock) {
		clockDao.update(clock);

	}

	public void delete(Clock clock) {
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

	public List<Object[]> getLatestItems() {
		return clockDao.getLatestItems();
	}

}
