package com.smit.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

public class SmitLog {
	private final Log log = LogFactory.getLog(getClass());
	
	public void before(JoinPoint point)
	{
		String classAndMethod = point.getTarget().getClass().getName() + 
								"类的" +
								point.getSignature().getName();
		System.out.println(classAndMethod);
		System.out.println("被拦截方法调用之前调用此方法，输出此语句");
	}
	
	public void after(JoinPoint point)
	{
		String classAndMethod = point.getTarget().getClass().getName() + 
								"类的" +
								point.getSignature().getName();
		System.out.println(classAndMethod);
		System.out.println("被拦截方法调用之后调用此方法，输出此语句");
	}
}
