package com.smit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smit.util.DaoException;
import com.smit.util.SmitPage;
import com.smit.vo.CertifiedProduct;
import com.smit.vo.Device;
import com.smit.vo.Menu;
import com.smit.vo.Order;
import com.smit.vo.OrderAndOption;
import com.smit.vo.TestOption;
import com.smit.web.control.action.Page;

public class ProductControlImpl extends HibernateDaoSupport implements ProductControlDao{

	private SessionFactory sessionFactory;
	
	public void sessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	//method of testOption =====================================
	public boolean insertOption(TestOption option) {
		this.getHibernateTemplate().save(option);
		return true;
	}

	public boolean updateOption(TestOption option) {
		this.getHibernateTemplate().saveOrUpdate(option);
		return true;
	}

	public boolean deleteOption(TestOption option) {
		this.getHibernateTemplate().delete(option);
		return true;
	}

	public TestOption loadOption(int id) {
		TestOption testOption = null;
		try{
			
			testOption = this.getHibernateTemplate().get(TestOption.class, id);
		}catch (HibernateException e){
			throw new DaoException("根据ID查找测试项失败！"+e.getMessage());
		}
		return testOption;
	}

	
	public List<TestOption> getOptionsList() {
		String hql = "from TestOption to";
		List<TestOption> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	
	public TestOption getOption(String name) {
		String hql = "from TestOption t where t.name='"+name+"'";
		List<TestOption> list = this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//method of device =================================
	public boolean addDevice(Device device) {
		this.getHibernateTemplate().save(device);
		return true;
	}

	public boolean updateDevice(Device device) {
		this.getHibernateTemplate().saveOrUpdate(device);
		//String hql = "update Device d set";
		return true;
	}

	public boolean deleteDevice(Device device) {
		this.getHibernateTemplate().delete(device);
		return true;
	}

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
	public boolean insertOrder(Order order) {
		this.getHibernateTemplate().merge(order);
		//this.getHibernateTemplate().save(order);
		return true;
	}

	public boolean updateOrder(Order order) {
		this.getHibernateTemplate().saveOrUpdate(order);
		return true;
	}

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

	public List<Order> loadOrder(String order_code) {
		String hql = "from Order o where o.order_code='"+order_code+"'";
		List<Order> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public List<Order> loadOrder(String order_code,String production_code) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Order o ");
		if(!("".equals(order_code))&& null != order_code){
			sb.append("where o.order_code='"+order_code.trim()+"'");
			if(!("".equals(production_code))&& null!= production_code){
				sb.append(" and o.production_code='"+production_code.trim()+"'");
			}
		}else{
			if(!("".equals(production_code))&& null!= production_code){
				sb.append(" where o.production_code='"+production_code.trim()+"'");
			}
		}
		//String hql = "from Order o where o.order_code='"+order_code+"'";
		List<Order> list = this.getHibernateTemplate().find(sb.toString());
		return list;
	}

	
	public List<Order> loadOrder() {
		String hql = "from Order";
		return this.getHibernateTemplate().find(hql);
	}

	public List<Device> getDevice(String checkID) {
		String hql = "from Device d where d.check_id='"+checkID+"'";
		return this.getHibernateTemplate().find(hql);
	}

	//method of product =============================================
	public boolean insertProduct(CertifiedProduct product) {
		this.getHibernateTemplate().save(product);
		return true;
	}

	public boolean updateProduct(CertifiedProduct product) {
		this.getHibernateTemplate().saveOrUpdate(product);
		return true;
	}

	public boolean deleteProduct(CertifiedProduct product) {
		this.getHibernateTemplate().delete(product);
		return true;
	}

	public List<CertifiedProduct> getProductList(Device device) {
		
		String hql = "from CertifiedProduct cp";
		//Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		//Query query = session.createQuery(hql);
		List<CertifiedProduct> list = this.getHibernateTemplate().find(hql);
		//query.set
		return list;
	}

	//中间表
	public List<OrderAndOption> getOptionByCode(String order_code) {
		String hql = "from OrderAndOption oo where oo.order_code='"+order_code+"'";
		List<OrderAndOption> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public boolean insertOrderOption(OrderAndOption oo) {
		this.getHibernateTemplate().save(oo);
		return true;
	}

	public List<Device> findByMchIdCode(String machineCode, String order_code) {
		String hql = "from Device d where d.order_code='"+order_code+"'"+" and d.machineID='"+machineCode+"'";
		return this.getHibernateTemplate().find(hql);
	}

	public List<Object[]> findMaxSn(String order_code) {
		String hql = "select order_code,max(sn),max(mac) from Device d where d.order_code='"+order_code+"' group by order_code";
		return this.getHibernateTemplate().find(hql);
	}

	public List<Device> findFailCode(String order_code, int auth_status) {
		String hql = "from Device d where d.order_code='"+order_code+"' and d.auth_status="+auth_status;
		return this.getHibernateTemplate().find(hql);
	}

	public List<Device> findDevice(String orderCode, String productCode,
			String manuCode) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Device.class);
		if(!"".equals(orderCode)&&orderCode!= null){
			criteria.add(Restrictions.eq("order_code", orderCode.trim()));
		}
		if(!"".equals(productCode)&&productCode!=null){
			criteria.add(Restrictions.eq("machineID", productCode.trim()));
		}
		criteria.add(Restrictions.eq("auth_status", 0));
		List<Device> list = criteria.list();
		return list;
	}

	public List<Device> findPageDevice(String orderCode, String productCode,
			String manuCode, int begin, int num) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Device.class);
		if(!"".equals(orderCode)&&orderCode!= null){
			criteria.add(Restrictions.eq("order_code", orderCode));
		}
		if(!"".equals(productCode)&&productCode!=null){
			criteria.add(Restrictions.eq("machineID", productCode));
		}
		criteria.add(Restrictions.eq("auth_status", 0));
		criteria.setFirstResult(begin);
		criteria.setMaxResults(num);
		List<Device> list = criteria.list();
		return list;
	}

	public void updateDeviceAuth(Device device) {
		String hql = "update Device d set d.auth_code="+device.getAuth_code()
		+",d.auth_status="+device.getAuth_status()
		+",d.create_time="+device.getCreate_time()+" where d.id="+device.getId();
		//this.getHibernateTemplate().e
	}

	public List<Device> queryDevice(String emmcID) {
		String hql = "from Device d where (d.emmcID='"+emmcID+"' or d.machineID like '"+emmcID+"%') and d.auth_status=0";
		return this.getHibernateTemplate().find(hql);
	}

	public List<Menu> findByType(int type) {
		String hql = "from Menu m where m.menu_type="+type;
		return this.getHibernateTemplate().find(hql);
	}

		
}
