package com.smit.dao.clock;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.Clock;
import com.smit.vo.alarmclock.Rings;

public interface RingsDao {
	
	public void save(Rings clock);
	public void update(Rings clock);
	public void delete(Rings clock);
	public Rings getById(Integer id);
	
	public List<Rings> listAll(SmitPage page);
	

}
