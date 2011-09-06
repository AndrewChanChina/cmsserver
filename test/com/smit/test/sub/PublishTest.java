package com.smit.test.sub;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.smit.web.pubsubhubbub.HubConstraint;

public class PublishTest {
	public static void main(String[] args) throws Exception{
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod("http://localhost:8080/pring/publish.do");
		postMethod.addParameter(HubConstraint.HUB_MODE_PARAM,"publish");
		//postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://xinple.org/?feed=rss2&p=385");
		//postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://www.jiucool.com/feed/");
		//postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://www.cnblogs.com/honeyxing/rss");
		//postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://www.cnblogs.com/yyq-quan/rss");
		//postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://www.jiucool.com/feed/atom/");
		//postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://rss.sina.com.cn/news/allnews/sports.xml");
		//www.cnblogs.com/honeyxing/rss
		postMethod.addParameter(HubConstraint.HUB_URL_PARAM,"http://rss.sina.com.cn/news/china/focus15.xml");
		client.executeMethod(postMethod);
		//System.out.println(postMethod.getStatusCode());
	}
}
