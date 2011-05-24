package com.smit.service;

import java.util.List;

import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public interface LogService {
	public boolean insertBaseLog(BaseLog baseLog);
	public boolean insertDetailLog(DetailLog detailLog);
	public List<BaseLog> getBaseLogs(int id,int start,int num);
	public List<DetailLog> getDetailLogs(int id,int start,int num);
}
