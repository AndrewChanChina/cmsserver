package com.smit.dao;

import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public interface LogDao {

	public boolean insertBaseLog(BaseLog baseLog);
	public boolean insertDetailLog(DetailLog detailLog);
	
}
