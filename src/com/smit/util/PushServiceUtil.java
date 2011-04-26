package com.smit.util;

import javax.servlet.http.HttpServletRequest;

import com.smit.service.push.IPushDataService;
import com.smit.service.push.PushDataServiceImpl;

public class PushServiceUtil {

	private static String ADMIN_NAME = "admin";
	private static String ADMIN_PASSWORD = "123456";
	
	// save connection in application
	public static IPushDataService getConnecation(HttpServletRequest request){
		//if(request.getSession().get)
		IPushDataService pd = new PushDataServiceImpl();
		pd.login(Constants.PUSH_HOST, ADMIN_NAME, ADMIN_PASSWORD);
		return pd;
	}
}
