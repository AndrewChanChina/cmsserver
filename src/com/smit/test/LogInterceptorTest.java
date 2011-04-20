package com.smit.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.dao.SysInfoDao;
import com.smit.util.SmitLog;

public class LogInterceptorTest {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SysInfoDao sysInfoDao = (SysInfoDao) ac.getBean("sysInfoDao");
		sysInfoDao.addSysInfo("111222333", "4444444444");
	}
}
