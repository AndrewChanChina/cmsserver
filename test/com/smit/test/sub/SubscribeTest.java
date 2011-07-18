package com.smit.test.sub;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.smit.web.pubsubhubbub.HubConstraint;

public class SubscribeTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod("http://localhost:8080/pring/subscribe.do");
		postMethod.addParameter(HubConstraint.HUB_MODE_PARAM,"subscribe");
		postMethod.addParameter(HubConstraint.HUB_TOPIC_PARAM,"http://xinple.org/?feed=rss2&p=385");
		postMethod.addParameter(HubConstraint.HUB_VERIFY,"async");
		postMethod.addParameter(HubConstraint.HUB_CALLBACK_PARAM,"http://localhost:8080/pring/subcallback.do");
		client.executeMethod(postMethod);
		System.out.println(postMethod.getStatusCode());
	}

}
