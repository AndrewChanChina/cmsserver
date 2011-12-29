package com.smit.productcontrol.testServcie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.ProductControlService;
import com.smit.service.PushService;
import com.smit.service.collection.VideoService;
import com.smit.service.push.IPushManageService;
import com.smit.service.push.PushManageServiceImpl;
import com.smit.util.Constants;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.PushContent;
import com.smit.vo.TestOption;
import com.smit.vo.UserAccountResource;

public class ProductControlServiceTest extends TestCase{
	
	public void testAddOption(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		TestOption to = new TestOption();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		to.setName("camera");
		to.setCreate_time(format.format(new  Date()));
		ps.insertOption(to);
		
	}
	
	public void testUpdateOption(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		TestOption to = ps.loadOption(1);
		System.out.println(to.getName());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		to.setCreate_time(format.format(new Date()));
		to.setTest_id("12");
		ps.updateOption(to);
	}
	
	public void testDeleteOption(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		TestOption to = ps.loadOption(1);
		ps.deleteOption(to);
	}
	
	//method of order
	public void testAddOrder(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		Order order = new Order();
		order.setInf_code("35355");
		order.setManufacturer_code("6565");
		order.setStart_time("20110504154423");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		order.setEnd_time(format.format(new Date()));
		ps.insertOrder(order);
	}
	
	public void testUpdateOrder(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		Order order = ps.loadOrder(1);
		order.setDevice_type("123");
		order.setName("test_order");
		ps.updateOrder(order);
		
	}
	
	//method of device ===============
	public void testAddDevice(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		Device device = new Device();
		device.setName("wifi");
		device.setOrder_code("567");
		device.setCheck_id("123456789");
		device.setCreate_time(new Date().toString());
		ps.addDevice(device);
		
	}
	
	public void testUpdateDevice(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		Device device = ps.findById(1);
		device.setUsername("test123");
		device.setPassword("test121");
		ps.updateDevice(device);
		
	}
	
	public void testAddProduct(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		CertifiedProduct cp = new CertifiedProduct();
		cp.setNew_check_id("test_new8888");
		cp.setOld_check_id("test_old777");
		cp.setCreate_time(new Date().toString());
		cp.setStatus("success");
		ps.insertProduct(cp);
	}
	
	public void updateProduct(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<CertifiedProduct> list = ps.getProductList(new Device());
		CertifiedProduct cp = list.get(0);
		cp.setStatus("failure");
		ps.updateProduct(cp);
		
	}
	
	public void testLoadOrder(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<Order> list = ps.loadOrder("456","");
		System.out.println(list.size());
	}
	
	public void testLoadOptions(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<TestOption> options = ps.getOptions();
		System.out.println(options.size());
		System.out.println(options.get(0).getId());
	}
	
	public void testOrderOption(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		Order order = new Order();
		order.setName("trfr");
		
	//	TestOption op = ps.loadOption(1);
		//TestOption op2 = ps.loadOption(2);
		TestOption op2 = new TestOption();
		op2.setId(2);
		op2.setName("546");
		//order.getOptions().add(op);
	//	order.getOptions().add(op2);
		
		//op.setOrders(orders)
		
		ps.insertOrder(order);
	}
	
	public void testDetailLog(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<Device> list = ps.getDevice("11051813490577599789879870000000");
		Device device = list.get(0);
		System.out.println(device.getLogs().iterator().next().getTestOption().getName());
	}
	
	public void testContent(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		PushService ps = (PushService) beanFactory.getBean("pushService");
		
		List<PushContent> contents = ps.getContent("2342");
		for(PushContent pc : contents){
			System.out.println(pc.getCreate_time());
		}
	}
	
	public void testpresence(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPushManageService ps =  (IPushManageService) beanFactory.getBean("pushManageService");
		
		List<UserAccountResource> list = ps.listAllResource("test888");
			
		System.out.println(list.size());
//		for(UserAccountResource u:list){
//			System.out.println(u.isPresence());
//			
//		}
		ps.updateUserPresence("test888@smit/smack", true);
	}
	
	public void testWince(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<Object[]> list = ps.findMaxSn("20110726152805");
		System.out.println(list.size());
		System.out.println(list.get(0)[0]);
	}
	
	public void testVideo(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		VideoService vs = (VideoService) beanFactory.getBean("videoService");
		
		List<Object[]> objs = vs.getLatestVideos();
		System.out.println(objs.size());
		for(Object[] o: objs){
			System.out.println(o[0]);
		}
	}
	
	public void testGetDevice(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<Device> list = ps.getDevice("110913154028917834561a2b3c4d2345");
		//Device device = list.get(0);
		//System.out.println(device.getCheck_id());
	}
	
	public void testQuery(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<OrderAndOption> list = ps.getOptionsByCode("NV98520111018141359");
		System.out.println(list.size());
	}
	public void testQueryDevice(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductControlService ps = (ProductControlService) beanFactory.getBean("productControlService");
		
		List<Device> list = ps.queryDevice("0000000");
		System.out.println(list.size());
		System.out.println(list.get(0).getAuth_code());
	}
}
