package come.smit.testPush;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smit.service.push.IPushManage;
import com.smit.vo.PushService;

public class PushServiceTest extends TestCase {

	private BeanFactory beanFactory;
	private IPushManage pm;
	protected void setUp() throws Exception {
		try {
			beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
			pm = (IPushManage)beanFactory.getBean("pushManage");
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		super.setUp();
	}
	
	public void testAdd(){
		PushService ps = new PushService();
		ps.setServiceName("88");
		pm.save(ps);
	}
	
	public void testDel(){
		PushService ps = new PushService();
		ps.setId(1);
		pm.delete(ps);
	}
	public void testUpdate(){
		PushService ps = new PushService();
		ps.setId(3);
		ps.setServiceName("kiss");
		pm.update(ps);
	}
	
	public void testQuery(){
		List<PushService> list = pm.listAll(null);
		for(PushService ps : list){
			System.out.println(ps.getServiceName());
			System.out.println(ps.getId());
		}
	}
}
