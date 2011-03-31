import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.smit.vo.User;


public class UserTest extends TestCase {
	private Session session;
	@Override
	protected void setUp() throws Exception {
		try {
			Configuration config = new Configuration().configure();
			SessionFactory factory = config.buildSessionFactory();
		    session = factory.openSession();
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
	
		super.setUp();
	}

	public void testAddUser() {
		User user = new User();
		user.setUserName("ligm");
		user.setPassword("123456");
		user.setEmail("ligm@szmg.com.cn");
		user.setExplain("supper admin");
		user.setTel("254122");
		user.setState(0);
		try {
			session.save(user);
			session.beginTransaction().commit();
			session.close();
		}catch(HibernateException e){
			e.printStackTrace();
			
		}
	
		
	
	}

}
