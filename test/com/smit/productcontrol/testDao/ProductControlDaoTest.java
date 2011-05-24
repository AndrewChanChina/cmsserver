package com.smit.productcontrol.testDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;

import com.smit.vo.BaseLog;
import com.smit.vo.Device;
import com.smit.vo.TestOption;

import junit.framework.TestCase;

public class ProductControlDaoTest extends TestCase{

	private Session session;
	@Override
	protected void setUp() throws Exception {
		try{
			Configuration cfg = new Configuration().configure();
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
		}catch (Exception e){
			e.printStackTrace();
		}
		super.setUp();
	}
	
	public void testInsertOption(){
		TestOption option = new TestOption();
		//session.beginTransaction().begin();
		option.setName("wifi");
		option.setTest_id("01");
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
		option.setCreate_time(formater.format(new Date()));
		session.save(option);
		//session.beginTransaction().commit();
	}
	
	public void testQueryOption(){
		TestOption option = (TestOption) session.load(TestOption.class, 1);
//		String hql = "from TestOption t where t.id=1";
//		TestOption option = (TestOption) session.createQuery(hql).list().get(0);
		if(option != null){
			System.out.println(option.getName()+"=====>"+option.getCreate_time());
		}
	}
	public void testUpdateOption(){
		
		String hql = "from TestOption t where t.id=1";
		TestOption option = (TestOption) session.createQuery(hql).list().get(0);
		option.setName("02");
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		option.setCreate_time(format.format(new Date()));
		session.saveOrUpdate(option);
		session.beginTransaction().commit();
		//DetachedCriteria 
	}
	
	public void testdid(){
		Device device = new Device();
		device.setId(1);
		String hql = "from BaseLog bl where bl.device.id =1";
		List<BaseLog> list = session.createQuery(hql).list();
		for(BaseLog log : list){
			System.out.println(log.getMachineID());
		}
	}
	
}
