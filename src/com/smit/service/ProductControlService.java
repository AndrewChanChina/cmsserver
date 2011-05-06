package com.smit.service;

import java.util.List;

import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.TestOption;

public interface ProductControlService {
	//testOption
	public boolean insertOption(TestOption option);
	public boolean updateOption(TestOption option);
	public boolean deleteOption(TestOption option);
	public TestOption loadOption(int id);
	
	//device
	public boolean addDevice(Device device);
	public boolean updateDevice(Device device);
	public boolean deleteDevice(Device device);
	public Device findById(int id);
	
	//order
	public boolean insertOrder(Order order);
	public boolean updateOrder(Order order);
	public Order loadOrder(int id);
	public List<Order> loadOrder(String order_code);
	//product
	public boolean insertProduct(CertifiedProduct product);
	public boolean updateProduct(CertifiedProduct product);
	public boolean deleteProduct(CertifiedProduct product);
	public List<CertifiedProduct> getProductList(Device device);
}
