package com.smit.dao;

import java.util.List;

import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public interface LogDao {

	public boolean insertBaseLog(BaseLog baseLog);
	public boolean insertDetailLog(DetailLog detailLog);
	public List<BaseLog> getBaseLog(int id,int begin,int num);
	public List<DetailLog> getDetailLog(int id,int begin,int num);
}
