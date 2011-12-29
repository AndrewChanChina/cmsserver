package com.smit.service;

import java.util.List;

import com.smit.dao.ProductControlDao;
import com.smit.util.SmitPage;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Menu;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;
import com.smit.web.control.action.Page;

public class ProductControlServiceImpl implements ProductControlService{

	private ProductControlDao productDao;
	
	public ProductControlDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductControlDao productDao) {
		this.productDao = productDao;
	}
	
	//method of testOption ===================================
	public boolean insertOption(TestOption option) {
		if(productDao.insertOption(option)){
			return true;
		}
		return false;
	}

	public boolean updateOption(TestOption option) {
		if(productDao.updateOption(option)){
			return true;
		}
		return false;
	}

	public boolean deleteOption(TestOption option) {
		if(productDao.deleteOption(option)){
			return true;
		}
		return false;
	}

	public TestOption loadOption(int id) {
		return productDao.loadOption(id);
	}

	public List<TestOption> getOptions() {
		List<TestOption> list = productDao.getOptionsList();
		return list;
	}

	public TestOption getOption(String name) {
		return productDao.getOption(name);
	}

	//method of device =========================================
	public boolean addDevice(Device device) {
		if(productDao.addDevice(device)){
			return true;
		}
		return false;
	}

	public boolean updateDevice(Device device) {
		if(productDao.updateDevice(device)){
			return true;
		}
		return false;
	}

	public boolean deleteDevice(Device device) {
		if(productDao.deleteDevice(device)){
			return true;
		}
		return false;
	}

	public Device findById(int id) {
		
		return productDao.findById(id);
	}

	//method of order ========================================
	public boolean insertOrder(Order order) {
		if(productDao.insertOrder(order)){
			return true;
		}
		return false;
	}

	public boolean updateOrder(Order order) {
		if(productDao.updateOrder(order)){
			return true;
		}
		return false;
	}

	public Order loadOrder(int id) {
		
		return productDao.loadOrder(id);
	}

	
	public List<Order> loadOrder(String order_code) {
		return productDao.loadOrder(order_code);
	}

	public List<Order> loadOrder(String order_code,String device_type) {
		return productDao.loadOrder(order_code,device_type);
	}

	
	public List<Order> loadOrder() {
		
		return productDao.loadOrder();
	}

	public List<Device> getDevice(String checkID) {
		List<Device> list = productDao.getDevice(checkID);
		return list;
	}

	//method of product ==========================================
	public boolean insertProduct(CertifiedProduct product) {
		if(productDao.insertProduct(product)){
			return true;
		}
		return false;
	}

	public boolean updateProduct(CertifiedProduct product) {
		if(productDao.updateProduct(product)){
			return true;
		}
		return false;
	}

	public boolean deleteProduct(CertifiedProduct product) {
		if(productDao.deleteProduct(product)){
			return true;
		}
		return false;
	}

	public List<CertifiedProduct> getProductList(Device device) {
		
		return productDao.getProductList(device);
	}

	//中间表
	public List<OrderAndOption> getOptionsByCode(String order_code) {
		
		return productDao.getOptionByCode(order_code);
	}
	
	public boolean insertOrderOption(OrderAndOption orderOption){
		if(productDao.insertOrderOption(orderOption)){
			return true;
		}
		return false;
	}

	public List<Device> findByMidCode(String machineID, String order_code) {
		return productDao.findByMchIdCode(machineID, order_code);
	}

	public List<Object[]> findMaxSn(String order_code) {
		return productDao.findMaxSn(order_code);
	}

	public List<Device> getFailCode(String order_code, int auth_status) {
		return productDao.findFailCode(order_code, auth_status);
	}

	public List<Device> queryDevice(String orderCode, String productCode,
			String manuCode) {
		return productDao.findDevice(orderCode, productCode, manuCode);
	}

	public List<Device> queryPageDevice(String orderCode, String productCode,
			String manuCode, int begin, int num) {
		return productDao.findPageDevice(orderCode, productCode, manuCode, begin, num);
	}

	public List<Device> queryDevice(String emmc) {
		return productDao.queryDevice(emmc);
	}

	public List<Menu> findByType(int type) {
		
		return productDao.findByType(type);
	}
	

}
