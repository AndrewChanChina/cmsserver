package com.smit.dao;

import java.util.List;

import com.smit.util.SmitPage;
import com.smit.vo.PushService;
import com.smit.vo.UserAccountResource;

public interface IPushServiceDao {

	public void save(PushService ps);
	public void update(PushService ps);
	public void delete(PushService ps);
	public PushService getById(Integer id);
	// for pagination
	// if page == null return all objects
	public List<PushService> listAll(SmitPage page);
	
	
	public void saveOrUpdate(UserAccountResource us);
	public void updateUserPresence(String user, String resource, boolean presence);
	public void delete(UserAccountResource us);
	public void deleteByAccount(String account);
	public List<UserAccountResource> listAllResource(String userName);
}
