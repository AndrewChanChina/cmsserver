package com.smit.dao;

import java.util.List;

import com.smit.vo.PubSubUser;
import com.smit.vo.Publisher;

public interface PubSubDao {

	public void addUser(PubSubUser user);
	public List<PubSubUser> getByName(String username);
	public List<PubSubUser> getByEmail(String email);
	public void updateUser(PubSubUser user);
	
	public void addPublisher(Publisher p);
	public void updatePublisher(Publisher p);
	public List<Publisher> findByName(String username);
}
