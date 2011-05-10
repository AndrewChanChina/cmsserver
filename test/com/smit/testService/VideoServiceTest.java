package com.smit.testService;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.ColumnService;
import com.smit.service.collection.CollectVideoTask;
import com.smit.service.collection.VideoService;
import com.smit.service.webService.ColumnImport;
import com.smit.service.webService.XmlWrap;
import com.smit.util.SmitPage;
import com.smit.vo.Part;
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
	
	public void insertVideoColumn(){
		//TODO 根据具体的情况，初始化数据库关于视频分类的表
		ColumnService cs = (ColumnService)beanFactory.getBean("columnService");
		try {
			XmlWrap xp = cs.queryRootChildren();
			System.out.println(xp.toXml());
			xp = cs.queryNextChildren(5);
			//System.out.println(xp.toXml());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 导入优酷的视频菜单
	 */
	public void testImportYoukuColumn() {
		ColumnService cs = (ColumnService)beanFactory.getBean("columnService");
		
		try {
			ColumnImport ci = new ColumnImport();
			ci.setTopColumn("youku", 100, cs);
			ci.addFirstColumn("推荐视频", "101");
			ci.addFirstColumn("今日浏览最多视频", "102");
			ci.addFirstColumn("本周评论最多视频", "103");
			ci.addFirstColumn("本周浏览最多视频", "104");
			ci.addFirstColumn("本月评论最多视频", "105");
			ci.addFirstColumn("本月浏览最多视频", "106");
			ci.addFirstColumn("本月浏览最多视频", "107");
			ci.addFirstColumn("资讯", "108");
			ci.addFirstColumn("原创", "109");
			ci.addFirstColumn("电视剧", "110");
			ci.addFirstColumn("娱乐", "111");
			ci.addFirstColumn("电影", "112");
			ci.addFirstColumn("体育", "113");
			ci.addFirstColumn("音乐", "114");
			ci.addFirstColumn("游戏", "115");
			ci.addFirstColumn("动漫", "116");
			ci.addFirstColumn("时尚", "117");
			ci.addFirstColumn("母婴", "118");
			ci.addFirstColumn("汽车", "119");
			ci.addFirstColumn("旅游", "120");
			ci.addFirstColumn("科技", "121");
			ci.addFirstColumn("教育", "122");
			ci.addFirstColumn("生活", "123");
			ci.addFirstColumn("搞笑", "124");
			ci.addFirstColumn("广告", "125");
			ci.addFirstColumn("其他", "126");
			
			ci.importColumn();		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 导入土豆的视频菜单
	 */
	public void testImportTudouColumn() {
		ColumnService cs = (ColumnService)beanFactory.getBean("columnService");
		
		try {
			ColumnImport ci = new ColumnImport();
			ci.setTopColumn("土豆", 200, cs);
			ci.addFirstColumn("最新视频", "201");
			ci.addFirstColumn("高清视频", "202");
			ci.addFirstColumn("今日人气最旺视频", "203");
			ci.addFirstColumn("今日打分最高的视频", "204");
			ci.addFirstColumn("今日评论最狠视频", "205");
			ci.addFirstColumn("今日收藏最多视频", "206");
			ci.addFirstColumn("所有推荐视频", "207");
			ci.addFirstColumn("所有人气最旺视频", "208");
			ci.addFirstColumn("所有打分最高的视频", "209");
			ci.addFirstColumn("所有评论最狠的视频", "210");
			ci.addFirstColumn("所有收藏最多的视频", "211");
			ci.addFirstColumn("娱乐", "212");
			ci.addFirstColumn("乐活", "213");
			ci.addFirstColumn("动画", "214");
			ci.addFirstColumn("游戏", "215");
			ci.addFirstColumn("音乐", "216");
			ci.addFirstColumn("体育", "217");
			ci.addFirstColumn("科技", "218");
			ci.addFirstColumn("影视", "219");
			ci.addFirstColumn("财富", "220");
			ci.addFirstColumn("教育", "221");
			ci.addFirstColumn("汽车", "222");
			ci.addFirstColumn("女性", "223");
			ci.addFirstColumn("热点", "224");
			ci.addFirstColumn("搞笑", "225");
			
			ci.importColumn();		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 导入优酷的RSS视频
	 */
	public void testImportYoukuVideo(){
		CollectVideoTask ct = (CollectVideoTask)beanFactory.getBean("collectVideoTask");
		ct.youku();
	}
	/**
	 * 导入土豆的RSS视频
	 */
	public void testImportTudouVideo(){
		CollectVideoTask ct = (CollectVideoTask)beanFactory.getBean("collectVideoTask");
		ct.tudou();
	}
	
	
	
	 
}
