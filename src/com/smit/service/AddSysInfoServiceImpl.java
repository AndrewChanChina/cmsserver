package com.smit.service;

import com.smit.dao.AddSysInfoDao;

public class AddSysInfoServiceImpl implements AddSysInfoService{
	
	private AddSysInfoDao addSysInfoDao;
	
	public void setAddSysInfoDao(AddSysInfoDao dao)
	{
		this.addSysInfoDao = dao;
	}
	
	@Override
	public boolean addSysInfo(final String key, final String value)
	{
		if(addSysInfoDao.addSysInfo(key, value))
		{
			return true;
		}
		return false;
	}
	
	
}
