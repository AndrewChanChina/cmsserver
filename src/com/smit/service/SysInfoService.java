package com.smit.service;

import java.util.ArrayList;
import java.util.List;

import com.smit.vo.SysInfo;

public interface SysInfoService {
	public boolean addSysInfo(final String key, final String value);
	public boolean deleteSysInfo(final String id);
	public boolean deleteSysInfo(final ArrayList<String> idList);
	public boolean updateSysInfo(final ArrayList<SysInfo> sysInfoList);
	public boolean enableSysInfo(final Integer id, boolean trueOrFalse);
	public List<SysInfo> queryAllSysInfo() throws Exception;
}
