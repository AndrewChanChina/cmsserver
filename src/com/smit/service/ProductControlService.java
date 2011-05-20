package com.smit.service;

import java.util.List;

import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;

public interface ProductControlService {
	//testOption
	public boolean insertOption(TestOption option);
	public boolean updateOption(TestOption option);
	public boolean deleteOption(TestOption option);
	public TestOption loadOption(int id);
	public List<TestOption> getOptions();
	public TestOption getOption(String name);
	
	//device
	public boolean addDevice(Device device);
	public boolean updateDevice(Device device);
	public boolean deleteDevice(Device device);
	public Device findById(int id);
	public List<Device> getDevice(String checkID);
	
	//order
	public boolean insertOrder(Order order);
	public boolean updateOrder(Order order);
	public Order loadOrder(int id);
	public List<Order> loadOrder(String order_code);
	public List<Order> loadOrder(String order_code,String device_type);
	public List<Order> loadOrder();
	
	//product
	public boolean insertProduct(CertifiedProduct product);
	public boolean updateProduct(CertifiedProduct product);
	public boolean deleteProduct(CertifiedProduct product);
	public List<CertifiedProduct> getProductList(Device device);
	
	//中间表
	public List<OrderAndOption> getOptionsByCode(String order_code);
	public boolean insertOrderOption(OrderAndOption orderOption);
}
