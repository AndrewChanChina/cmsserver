package com.smit.service;

import java.util.List;

import com.smit.dao.ProductControlDao;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;

public class ProductControlServiceImpl implements ProductControlService{

	private ProductControlDao productDao;
	
	public ProductControlDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductControlDao productDao) {
		this.productDao = productDao;
	}
	
	//method of testOption ===================================
	@Override
	public boolean insertOption(TestOption option) {
		if(productDao.insertOption(option)){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateOption(TestOption option) {
		if(productDao.updateOption(option)){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteOption(TestOption option) {
		if(productDao.deleteOption(option)){
			return true;
		}
		return false;
	}

	@Override
	public TestOption loadOption(int id) {
		return productDao.loadOption(id);
	}

	@Override
	public List<TestOption> getOptions() {
		List<TestOption> list = productDao.getOptionsList();
		return list;
	}

	@Override
	public TestOption getOption(String name) {
		return productDao.getOption(name);
	}

	//method of device =========================================
	@Override
	public boolean addDevice(Device device) {
		if(productDao.addDevice(device)){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateDevice(Device device) {
		if(productDao.updateDevice(device)){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDevice(Device device) {
		if(productDao.deleteDevice(device)){
			return true;
		}
		return false;
	}

	@Override
	public Device findById(int id) {
		
		return productDao.findById(id);
	}

	//method of order ========================================
	@Override
	public boolean insertOrder(Order order) {
		if(productDao.insertOrder(order)){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateOrder(Order order) {
		if(productDao.updateOrder(order)){
			return true;
		}
		return false;
	}

	@Override
	public Order loadOrder(int id) {
		
		return productDao.loadOrder(id);
	}

	
	@Override
	public List<Order> loadOrder(String order_code) {
		return productDao.loadOrder(order_code);
	}

	@Override
	public List<Order> loadOrder(String order_code,String device_type) {
		return productDao.loadOrder(order_code,device_type);
	}

	
	@Override
	public List<Order> loadOrder() {
		
		return productDao.loadOrder();
	}

	@Override
	public List<Device> getDevice(String checkID) {
		List<Device> list = productDao.getDevice(checkID);
		return list;
	}

	//method of product ==========================================
	@Override
	public boolean insertProduct(CertifiedProduct product) {
		if(productDao.insertProduct(product)){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProduct(CertifiedProduct product) {
		if(productDao.updateProduct(product)){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProduct(CertifiedProduct product) {
		if(productDao.deleteProduct(product)){
			return true;
		}
		return false;
	}

	@Override
	public List<CertifiedProduct> getProductList(Device device) {
		
		return productDao.getProductList(device);
	}

	//中间表
	@Override
	public List<OrderAndOption> getOptionsByCode(String order_code) {
		
		return productDao.getOptionByCode(order_code);
	}
	
	public boolean insertOrderOption(OrderAndOption orderOption){
		if(productDao.insertOrderOption(orderOption)){
			return true;
		}
		return false;
	}

	@Override
	public List<Device> findByMidCode(String machineID, String order_code) {
		return productDao.findByMchIdCode(machineID, order_code);
	}

	@Override
	public List<Object[]> findMaxSn(String order_code) {
		return productDao.findMaxSn(order_code);
	}
	

}
