package com.smit.productcontrol.testServcie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.ProductControlService;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.TestOption;

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
		
		List<Order> list = ps.loadOrder("456");
		System.out.println(list.size());
	}
}
