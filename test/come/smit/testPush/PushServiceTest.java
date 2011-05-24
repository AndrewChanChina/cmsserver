package come.smit.testPush;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.push.IPushManageService;
import com.smit.vo.PushService;
import com.smit.vo.UserAccountResource;

public class PushServiceTest extends TestCase {

	private BeanFactory beanFactory;
	private IPushManageService pm;
	protected void setUp() throws Exception {
		try {
			beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
			pm = (IPushManageService)beanFactory.getBean("pushManage");
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		super.setUp();
	}
	
	public void testAdd(){
		PushService ps = new PushService();
		ps.setServiceName("88");
		pm.save(ps);
		
		UserAccountResource us = new UserAccountResource();
		us.setDeviceId("12354");
		us.setDeviceName("1781541");
		us.setUserAccount("chenyz");
		
		pm.saveOrUpdate(us);
	}
	
	public void testDel(){
		PushService ps = new PushService();
		ps.setId(1);
		//pm.delete(ps);
		
		UserAccountResource us = new UserAccountResource();
		us.setId(1);
		//pm.delete(us);
		pm.updateUserAccountAllRes(null);
	}
	public void testUpdate(){
		PushService ps = new PushService();
		ps.setId(3);
		ps.setServiceName("kiss");
		pm.update(ps);
		
		UserAccountResource us = new UserAccountResource();
		us.setId(1);
		us.setDeviceId("23333334");
		pm.saveOrUpdate(us);
	}
	
	public void testQuery(){
		List<PushService> list = pm.listAll(null);
		for(PushService ps : list){
			System.out.println(ps.getServiceName());
			System.out.println(ps.getId());
		}
	}
	
	public void testQueryResource(){
		/*List<UserAccountResource> list = pm.listAllResource();
		for(UserAccountResource ps : list){
			System.out.println(ps.getDeviceName());
			System.out.println(ps.getId());
		}*/
	}
}
