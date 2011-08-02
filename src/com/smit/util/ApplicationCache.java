package com.smit.util;

import java.util.HashMap;
import java.util.Map;

public class ApplicationCache {

	private static  ApplicationCache appCache = new ApplicationCache();;
	private static Map<String,Object> map = new HashMap<String, Object>();;
	private ApplicationCache(){
		
	}
	public static ApplicationCache getInstance(){
		return appCache;
	}
	public Object  getAttribute(String key) {
		return map.get(key);
	}
	public  void setAttribute(String key,Object value) {
		map.put(key, value);
	}
	
}
