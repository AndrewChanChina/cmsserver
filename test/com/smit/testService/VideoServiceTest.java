package com.smit.testService;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.collection.VideoService;
import com.smit.util.SmitPage;
import com.smit.vo.Video;

public class VideoServiceTest extends TestCase {

	BeanFactory beanFactory;
	VideoService vs;
	
	@Override
	protected void setUp() throws Exception {	
	
		beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		vs = (VideoService)beanFactory.getBean("videoService");
		super.setUp();
	}
	
	public void testAdd(){
		
		Video v = new Video();
		v.setTitle("important news5");
		v.setAuthor("andrew5");
		v.setEnclosure_url("www.baidu.com3");
		v.setPartId(1);
		
		vs.save(v);
	}
	
	public void testQuery(){
		SmitPage sp = new SmitPage(1,4);
		List<Video> list = vs.listAll(sp);
		
		for(Video v : list){
			System.out.println("title:" + v.getTitle());
		}
	}
	
	public void testQueryPartId(){
		SmitPage sp = new SmitPage(1,4);
		List<Video> list = vs.findByPartId(sp, 1);
		
		for(Video v : list){
			System.out.println("title:" + v.getTitle());
		}
	}
}
