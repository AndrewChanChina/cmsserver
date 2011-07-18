package com.smit.analysis.testService;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.ProductControlService;
import com.smit.service.UserAnalysisService;
import com.smit.vo.UserAnalysis;

import junit.framework.TestCase;

public class AnalysisServiceTest extends TestCase{
	
	public void testAddAnalysis(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAnalysisService us =  (UserAnalysisService) beanFactory.getBean("userAnalysisService");
		
		UserAnalysis  u = new UserAnalysis(); 
		u.setApp_name("mp3");
		u.setP_name("test");
		
		us.insert(u);
	}
	

}
