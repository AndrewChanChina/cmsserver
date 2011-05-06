package com.smit.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.DaoException;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Order;
import com.smit.vo.TestOption;

public class ProductControlImpl extends HibernateDaoSupport implements ProductControlDao{

	private SessionFactory sessionFactory;
	
	public void sessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	//method of testOption =====================================
	@Override
	public boolean insertOption(TestOption option) {
		this.getHibernateTemplate().save(option);
		return true;
	}

	@Override
	public boolean updateOption(TestOption option) {
		this.getHibernateTemplate().saveOrUpdate(option);
		return true;
	}

	@Override
	public boolean deleteOption(TestOption option) {
		this.getHibernateTemplate().delete(option);
		return true;
	}

	@Override
	public TestOption loadOption(int id) {
		TestOption testOption = null;
		try{
			testOption = this.getHibernateTemplate().get(TestOption.class, id);
		}catch (HibernateException e){
			throw new DaoException("根据ID查找测试项失败！"+e.getMessage());
		}
		return testOption;
	}

	//method of device =================================
	@Override
	public boolean addDevice(Device device) {
		this.getHibernateTemplate().save(device);
		return true;
	}

	@Override
	public boolean updateDevice(Device device) {
		this.getHibernateTemplate().saveOrUpdate(device);
		return true;
	}

	@Override
	public boolean deleteDevice(Device device) {
		this.getHibernateTemplate().delete(device);
		return true;
	}

	@Override
	public Device findById(int id) {
		Device device = null;
		try{
			device = this.getHibernateTemplate().get(Device.class, id);
		}catch (HibernateException e){
			throw new DaoException("根据ID查找设备失败："+e.getMessage());
		}
		
		return device;
	}

	//method of order =========================================
	@Override
	public boolean insertOrder(Order order) {
		this.getHibernateTemplate().save(order);
		return true;
	}

	@Override
	public boolean updateOrder(Order order) {
		this.getHibernateTemplate().saveOrUpdate(order);
		return true;
	}

	@Override
	public Order loadOrder(int id) {
		//String hql = "from Order where Order.id="+id;
		Order order = null;
		try{
			order = this.getHibernateTemplate().get(Order.class,id);
		}catch (HibernateException e){
			throw new DaoException("根据id查找订单失败："+e.getMessage());
		}
		
		return order;
	}

	@Override
	public List<Order> loadOrder(String order_code) {
		String hql = "from Order o where o.order_code='"+order_code+"'";
		List<Order> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	//method of product =============================================
	@Override
	public boolean insertProduct(CertifiedProduct product) {
		this.getHibernateTemplate().save(product);
		return true;
	}

	@Override
	public boolean updateProduct(CertifiedProduct product) {
		this.getHibernateTemplate().saveOrUpdate(product);
		return true;
	}

	@Override
	public boolean deleteProduct(CertifiedProduct product) {
		this.getHibernateTemplate().delete(product);
		return true;
	}

	@Override
	public List<CertifiedProduct> getProductList(Device device) {
		
		String hql = "from CertifiedProduct cp";
		//Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		//Query query = session.createQuery(hql);
		List<CertifiedProduct> list = this.getHibernateTemplate().find(hql);
		//query.set
		return list;
	}

}
