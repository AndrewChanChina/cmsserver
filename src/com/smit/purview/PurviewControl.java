package com.smit.purview;

import org.aspectj.lang.JoinPoint;

public class PurviewControl {
	
	public void login(JoinPoint point){
		String classAndMethod = point.getTarget().getClass().getName() + 
		"类的" +
		point.getSignature().getName();
		System.out.println(classAndMethod);
		System.out.println("被拦截方法调用之前调用此方法，输出此语句");
	}

}
