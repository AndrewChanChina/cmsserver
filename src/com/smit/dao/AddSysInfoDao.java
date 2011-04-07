package com.smit.dao;

import java.util.List;

import com.smit.vo.SysInfo;

public interface AddSysInfoDao {
	public boolean addSysInfo(final String key, final String value);
	public List<SysInfo> queryAllSysInfo();
	public boolean deleteSysInfo(final String key);
}
