package com.smit.service;

import java.util.ArrayList;
import java.util.List;

import com.smit.dao.SysInfoDao;
import com.smit.vo.SysInfo;

public class SysInfoServiceImpl implements SysInfoService{
	
	private SysInfoDao sysInfoDao;
	
	public void setSysInfoDao(SysInfoDao sysInfoDao)
	{
		this.sysInfoDao = sysInfoDao;
	}
	
	public boolean addSysInfo(final String key, final String value)
	{
		if(sysInfoDao.addSysInfo(key, value))
		{
			return true;
		}
		return false;
	}

	public boolean deleteSysInfo(String id) {
		// TODO Auto-generated method stub
		if(sysInfoDao.deleteSysInfo(id))
		{
			return true;
		}
		return false;
	}
	
	public boolean deleteSysInfo(final ArrayList<String> idList)
	{
		if(sysInfoDao.deleteSysInfo(idList))
		{
			return true;
		}
		return false;
	}

	public boolean updateSysInfo(final ArrayList<SysInfo> sysInfoList)
	{
		if(sysInfoDao.updateSysInfo(sysInfoList))
		{
			return true;
		}
		return false;
	}
	
	public boolean enableSysInfo(final Integer id, boolean trueOrFalse)
	{
		if(sysInfoDao.enableSysInfo(id, trueOrFalse))
		{
			return true;
		}
		return false;
	}
	
	public List<SysInfo> queryAllSysInfo() throws Exception
	{
		return sysInfoDao.queryAllSysInfo();
	}
}
