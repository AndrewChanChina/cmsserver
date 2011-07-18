package com.smit.service;

import java.util.List;

import com.smit.vo.Subscriber;

public interface SubscriberService {
	public void addSubscriber(Subscriber sub);
	public List<Subscriber> findByCallUrl(String url);
	public void delSubscriber(Subscriber sub);
	public List<Subscriber> findByTopicCall(String feed,String callback);
}
