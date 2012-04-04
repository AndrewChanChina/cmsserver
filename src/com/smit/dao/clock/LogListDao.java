package com.smit.dao.clock;

import java.util.List;

import com.smit.dao.GenericDao;
import com.smit.util.SmitPage;
import com.smit.vo.alarmclock.GroupRoom;
import com.smit.vo.alarmclock.LogList;

public interface LogListDao extends GenericDao <LogList,Long> {
	
	public List<LogList> listLatestLog(SmitPage page, String[] names,
			String[] values);

}
