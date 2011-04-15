package com.smit.testLogin;

import java.sql.Timestamp;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.IGroupManagerService;
import com.smit.util.SmitPage;
import com.smit.vo.Group;

public class GroupDaoTest extends TestCase {
	
	private String groupName = "group3";
	public void testPagination(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IGroupManagerService groupManager = (IGroupManagerService)beanFactory.
		getBean("groupManagerService");
		
		SmitPage pager = new SmitPage(0);	
		int pageSize = 15; 		
		pager.setPageSize(pageSize);

	   try { 	 
		   List groupList = groupManager.listAllGroups(pager);
		   for(Object g : groupList){
			   Group gg = (Group)g;
			   System.out.println("groupName " + gg.getGroupName());
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	}
	public void testDelete(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IGroupManagerService gm = (IGroupManagerService)beanFactory.
		getBean("groupManagerService");
		
		Group group = new Group();
		group.setId(15);
		
		gm.delete(group);
			
	}
	public void testInsert(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IGroupManagerService gm = (IGroupManagerService)beanFactory.
		getBean("groupManagerService");
		
		Group group = new Group();
		group.setGroupName(groupName);
		Timestamp ts = new Timestamp(11225485);
		group.setCreatetime(ts);
				
		gm.save(group);
	}
	public void testQuery(){
		
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IGroupManagerService gm = (IGroupManagerService)beanFactory.
		getBean("groupManagerService");
		
		Group g = gm.findGroupByName(groupName);
		System.out.println(g.getGroupName());
		
		Group g2 = gm.getGroup(1);
		System.out.println("group name " + g2.getGroupName());	
		
	}
	public void testUpdate(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IGroupManagerService gm = (IGroupManagerService)beanFactory.
		getBean("groupManagerService");
		
		Group group = new Group();
		group.setId(2);
		group.setGroupName(groupName);
		Timestamp ts = new Timestamp(458);
		group.setCreatetime(ts);
		
		gm.update(group);
		
	}
	

}
