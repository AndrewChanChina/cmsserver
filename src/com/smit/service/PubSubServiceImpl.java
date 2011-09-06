package com.smit.service;

import java.util.List;

import com.smit.dao.PubSubDao;
import com.smit.vo.PubSubUser;
import com.smit.vo.Publisher;

public class PubSubServiceImpl implements PubSubService{

	private PubSubDao dao;
	@Override
	public void addUser(PubSubUser user) {
		dao.addUser(user);
	}

	@Override
	public List<PubSubUser> getByName(String username) {
		return dao.getByName(username);
	}

	@Override
	public List<PubSubUser> getByEmail(String email) {
		return dao.getByEmail(email);
	}

	@Override
	public void updateUser(PubSubUser user) {
		dao.updateUser(user);
	}

	@Override
	public void addPublisher(Publisher p) {
		dao.addPublisher(p);
	}

	@Override
	public void updatePublisher(Publisher p) {
		dao.updatePublisher(p);
	}

	public PubSubDao getDao() {
		return dao;
	}

	public void setDao(PubSubDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Publisher> findByName(String username) {
		return dao.findByName(username);
	}

}
