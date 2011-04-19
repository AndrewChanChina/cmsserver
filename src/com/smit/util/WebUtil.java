package com.smit.util;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtil {

	private final static String HIDE_ID = "hideId";
	
	/**
	 * get url request parameter for pagination usually
	 * @param request
	 * @param param
	 * @param defaultvalue
	 * @return
	 */
	public static int getIntByRequestParament(HttpServletRequest request, String param, int defaultvalue){
		try {
			return Integer.parseInt(request.getParameter(param));
		} catch (Exception e) {
			return defaultvalue;
		}
	}
	
	public static String setHideId2Session(HttpSession session){
		Random random = new Random();
		random.setSeed(new java.util.Date().getTime());		
		String hideId = String.valueOf(random.nextInt());		
		session.setAttribute(HIDE_ID, hideId);
		return hideId;
	}
	
	public static boolean isHideIdEqual(HttpSession session,String hideId){
		String id = (String)session.getAttribute(HIDE_ID);
		if(id == null){
			return false;
		}
		if(id.equals(hideId)){
			return true;
		}
		return false;
	}
	
	

}
