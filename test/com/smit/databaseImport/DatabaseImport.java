package com.smit.databaseImport;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.IGroupManagerService;
import com.smit.service.IPurviewService;
import com.smit.service.IUserService;
import com.smit.service.push.IPushManageService;
import com.smit.util.Constants;
import com.smit.vo.Group;
import com.smit.vo.Purview;
import com.smit.vo.PushService;
import com.smit.vo.User;

public class DatabaseImport extends TestCase {
	
	private BeanFactory beanFactory;
	protected void setUp() throws Exception {
		super.setUp();
		
		beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");		
		
	}
	/**
	 * 导入管理员相关的数据库
	 */
	public void testImportAdmin(){
		

		Purview purview = new Purview();
		
		IPurviewService ps = (IPurviewService)beanFactory.getBean("purviewService");
		purview.setPurviewName("everything");
		purview.setPurviewInfo("你可以做所有的事情");
		ps.save(purview);
		
		IGroupManagerService gs = (IGroupManagerService)beanFactory.getBean("groupManagerService");
		Group g = new Group();
		g.setGroupName("Admin");
		g.setSortRank(Constants.LEVEL_ADMIN);
		g.getPurviews().add(purview);		
		gs.save(g);	
		
		IUserService us = (IUserService)beanFactory.getBean("userService");	
		User user = new User();
		user.setUserName("admin");
		user.setPassword("123456");
		user.setEmail("chenyz@smit.com.cn");		
		user.setGroup(g);		
		us.save(user);
		
		User user2 = new User();
		user2.setUserName("mtv");
		user2.setPassword("123456");
		user2.setEmail("chenyz@smit.com.cn");		
		user2.setGroup(g);		
		us.save(user2);
		
		
		
		IPushManageService pms = (IPushManageService)beanFactory.getBean("pushManageService");
		
		PushService pushservice = new PushService();
		pushservice.setServiceName("push alarm clock");
		pushservice.setUserId(user2.getId());
		pms.save(pushservice);
		pushservice.setServiceId("GVTO6mcPcNGm3556786E8KL48M9L87rr");
		pms.update(pushservice);
		
		PushService pushservice2 = new PushService();
		pushservice2.setServiceName("push apk install");
		pushservice2.setUserId(user2.getId());
		pms.save(pushservice2);
		pushservice2.setServiceId("tmticb0yfyRl4O71gXTxpbiTC92DvWFf");
		pms.update(pushservice2);
	}
	/**
	 * 导入 push 用户的分组信息
	 */
	public void testImportSmackUser(){
		

		Purview purview = new Purview();
		
		IPurviewService ps = (IPurviewService)beanFactory.getBean("purviewService");
		purview.setPurviewName("user");
		purview.setPurviewInfo("进行推送服务的用户");
		ps.save(purview);
		
		IGroupManagerService gs = (IGroupManagerService)beanFactory.getBean("groupManagerService");
		Group g = new Group();
		g.setGroupName(Constants.PUSH_GROUPNAME);
		g.setSortRank(Constants.LEVEL_USER);
		g.getPurviews().add(purview);		
		gs.save(g);		
		
	}

	public static void main(String[] args) {
	    junit.textui.TestRunner.run(DatabaseImport.class);
	}
}
