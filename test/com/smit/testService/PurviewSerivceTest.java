package com.smit.testService;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.IPurviewService;
import com.smit.vo.Purview;

public class PurviewSerivceTest extends TestCase {
	
	public void testAdd(){
	
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPurviewService us = (IPurviewService)beanFactory.getBean("purviewService");
		
		Purview purview = new Purview();
		purview.setPurviewInfo("you can edit");
		purview.setPurviewName("edit");
		
		us.save(purview);
		
	}
	public void testDelelte(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPurviewService us = (IPurviewService)beanFactory.getBean("purviewService");
		
		Purview purview = new Purview();
		purview.setId(3);
		
		us.delete(purview);
	}
	
	public void testModify(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPurviewService us = (IPurviewService)beanFactory.getBean("purviewService");
		
		Purview purview = new Purview();
		purview.setId(4);
		purview.setPurviewName("supur editor");
		
		us.update(purview);
	}
	
	public void testQuery(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPurviewService us = (IPurviewService)beanFactory.getBean("purviewService");
		
		Purview purview = us.getPurview(4);
		System.out.println(purview.getPurviewName());
		
		List<Purview> list = us.listAll(null);
		
		for(Purview p:list){
			System.out.println(p.getPurviewName());
		}
	}

}
