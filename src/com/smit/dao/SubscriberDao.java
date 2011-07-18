package com.smit.dao;

import java.util.List;

import com.smit.vo.Subscriber;

public interface SubscriberDao {
	
	public void addSubscriber(Subscriber sub);
	public List<Subscriber> findByCallUrl(String url);
	public void deleteSubscriber(Subscriber sub);
	public List<Subscriber> findByTopicCallback(String feed,String callback);
}
