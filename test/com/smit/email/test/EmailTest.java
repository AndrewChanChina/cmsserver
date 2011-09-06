package com.smit.email.test;

import org.apache.commons.mail.SimpleEmail;

public class EmailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.126.com");
		email.setAuthentication("luocheng8888@126.com", "492513");
		email.setCharset("utf-8");
		email.addTo("lc-no1@163.com");
		email.setFrom("luocheng8888@126.com", "lc");
		email.setSubject("测试邮件");
		email.setMsg("邮件测试服务！");
		email.send();
		System.out.println("send success!");
	}

}
