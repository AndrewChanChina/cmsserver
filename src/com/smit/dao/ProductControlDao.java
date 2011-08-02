package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;
import com.smit.web.control.action.Page;

public interface ProductControlDao {

	//testOption
	public boolean insertOption(TestOption option);
	public boolean updateOption(TestOption option);
	public boolean deleteOption(TestOption option);
	public TestOption loadOption(int id);
	public List<TestOption> getOptionsList();
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
	
	//order option中间表
	public List<OrderAndOption> getOptionByCode(String order_code);
	public boolean insertOrderOption(OrderAndOption oo);
	
	//wince auth
	public List<Device> findByMchIdCode(String machineID,String order_code);
	//取出当前生产批次的最大mac地址和sn序列号
	public List<Object[]> findMaxSn(String order_code);
	//取出当前生产批次中授权失败的授权码
	public List<Device> findFailCode(String order_code,int auth_status);
	
	//根据生产代号，生产商，产品型号，查询激活的设备
	public List<Device> findDevice(String orderCode,String productCode,String manuCode);
	//分页查询，只取指定的几条
	public List<Device> findPageDevice(String orderCode,String productCode,String manuCode,int begin,int num);
}
