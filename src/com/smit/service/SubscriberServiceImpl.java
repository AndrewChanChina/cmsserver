package com.smit.service;

import java.util.List;

import com.smit.dao.SubscriberDao;
import com.smit.vo.Subscriber;

public class SubscriberServiceImpl implements SubscriberService {

	private SubscriberDao subDao;
	
	@Override
	public void addSubscriber(Subscriber sub) {
		subDao.addSubscriber(sub);
	}

	@Override
	public List<Subscriber> findByCallUrl(String url) {
		return subDao.findByCallUrl(url);
	}

	@Override
	public void delSubscriber(Subscriber sub) {
		subDao.deleteSubscriber(sub);
	}

	public SubscriberDao getSubDao() {
		return subDao;
	}

	public void setSubDao(SubscriberDao subDao) {
		this.subDao = subDao;
	}

	@Override
	public List<Subscriber> findByTopicCall(String feed, String callback) {
		return subDao.findByTopicCallback(feed, callback);
	}

}
