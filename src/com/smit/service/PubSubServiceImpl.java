package com.smit.service;

import java.util.List;

import com.smit.dao.PubSubDao;
import com.smit.vo.PubSubUser;
import com.smit.vo.Publisher;

public class PubSubServiceImpl implements PubSubService{

	private PubSubDao dao;
	public void addUser(PubSubUser user) {
		dao.addUser(user);
	}

	public List<PubSubUser> getByName(String username) {
		return dao.getByName(username);
	}

	public List<PubSubUser> getByEmail(String email) {
		return dao.getByEmail(email);
	}

	public void updateUser(PubSubUser user) {
		dao.updateUser(user);
	}

	public void addPublisher(Publisher p) {
		dao.addPublisher(p);
	}

	public void updatePublisher(Publisher p) {
		dao.updatePublisher(p);
	}

	public PubSubDao getDao() {
		return dao;
	}

	public void setDao(PubSubDao dao) {
		this.dao = dao;
	}

	public List<Publisher> findByName(String username) {
		return dao.findByName(username);
	}

}
