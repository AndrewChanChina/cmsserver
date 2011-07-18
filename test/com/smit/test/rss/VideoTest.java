package com.smit.test.rss;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.IPurviewService;
import com.smit.service.collection.CollectVideoTask;

import junit.framework.TestCase;

public class VideoTest extends TestCase{
	
	public void testVideo(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPurviewService us = (IPurviewService)beanFactory.getBean("purviewService");
		
		CollectVideoTask cv = new CollectVideoTask();
	//	cv.youku();
	}
}
