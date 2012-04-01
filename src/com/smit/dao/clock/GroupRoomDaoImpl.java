package com.smit.dao.clock;

import java.util.List;

import com.smit.dao.GenericDaoImpl;
import com.smit.vo.alarmclock.GroupRoom;

public class GroupRoomDaoImpl extends GenericDaoImpl<GroupRoom, Long> implements
		GroupRoomDao {

	public List<GroupRoom> findGroupByName(String groupName) {
		String sql = "SELECT g FROM com.smit.vo.alarmclock.GroupRoom g WHERE groupName = '"
				+ groupName + "'";
		return find(sql);
	}

}
