package com.smit.service;

import com.smit.vo.BaseLog;
import com.smit.vo.DetailLog;

public interface LogService {
	public boolean insertBaseLog(BaseLog baseLog);
	public boolean insertDetailLog(DetailLog detailLog);
}
