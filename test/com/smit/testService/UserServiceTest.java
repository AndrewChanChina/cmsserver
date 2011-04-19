package com.smit.testService;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.IUserService;
import com.smit.vo.Group;
import com.smit.vo.User;

public class UserServiceTest extends TestCase {

	public void testAddUser(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService us = (IUserService)beanFactory.getBean("userService");
		
		User user = new User();
		user.setUserName("chen1");
		user.setEmail("cheny@smit");
		Group g = new Group();
		g.setGroupName("ee");
		user.setGroup(g);
		
		us.save(user);
	}
	
	public void testQueryUser(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService us = (IUserService)beanFactory.getBean("userService");
		
		User user = us.findUserByName("yzchen4");
		Group g = user.getGroup();
		System.out.println(user.getUserName());
		System.out.println(g.getGroupName());
	}
	public void testString(){
		String[] array = {"a1a","bb","cc"};
		isContain(array,"aa");
	}
	public boolean isContain(String[] strs,String s){
		for(int i=0; i<strs.length; i++){
			if(strs[i].indexOf(s) != -1)
				return true;
		}
		return false;
	}
}
