package com.smit.service;

import com.smit.dao.LogDao;
import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public class LogServiceImpl implements LogService{

	private LogDao logDao;
	
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	@Override
	public boolean insertBaseLog(BaseLog baseLog) {
		if(logDao.insertBaseLog(baseLog)){
			return true;
		}
		return false;
	}

	@Override
	public boolean insertDetailLog(DetailLog detailLog) {
		if(logDao.insertDetailLog(detailLog)){
			return true;
		}
		return false;
	}

}
