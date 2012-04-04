package com.smit.dao.clock;

import java.util.List;

import com.smit.dao.GenericDao;
import com.smit.vo.alarmclock.GroupRoom;

public interface GroupRoomDao extends GenericDao <GroupRoom,Long> {

	public List<GroupRoom> findGroupByName(String groupName);
	
	public List<GroupRoom> listAllByGroup();
}
