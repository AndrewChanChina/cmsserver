package com.smit.service;

import java.util.List;

import com.smit.dao.LogDao;
import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public class LogServiceImpl implements LogService{

	private LogDao logDao;
	
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public boolean insertBaseLog(BaseLog baseLog) {
		if(logDao.insertBaseLog(baseLog)){
			return true;
		}
		return false;
	}

	public boolean insertDetailLog(DetailLog detailLog) {
		if(logDao.insertDetailLog(detailLog)){
			return true;
		}
		return false;
	}

	public List<BaseLog> getBaseLogs(int id, int start, int num) {
		
		return logDao.getBaseLog(id, start, num);
	}

	public List<DetailLog> getDetailLogs(int id, int start, int num) {
		// TODO Auto-generated method stub
		return logDao.getDetailLog(id, start, num);
	}

}
