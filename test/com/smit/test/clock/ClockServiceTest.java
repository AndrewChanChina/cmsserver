package com.smit.test.clock;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.clock.ClockService;
import com.smit.vo.Purview;
import com.smit.vo.alarmclock.Clock;

public class ClockServiceTest extends TestCase {

	public void testAdd() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ClockService cs =(ClockService)beanFactory.getBean("clockService");
		
		Clock clock = new Clock();
		clock.setAlarmtime(123456);
		clock.setName("kill you");
		clock.setHour(52);
		//cs.save(clock);
	}
	
	public void testList() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ClockService cs =(ClockService)beanFactory.getBean("clockService");
		
		List<Clock> list = cs.getAllItems();
		for(Clock c:list){
			System.out.println(c.getName() + c.getHour());
		}
	}

}
